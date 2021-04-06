package com.laundrative_v2.app.service;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionDb;
import com.laundrative_v2.app.beans.json.institution.NeighborhoodInfo;
import com.laundrative_v2.app.beans.json.institution.blocks.CategoryKindBlock;
import com.laundrative_v2.app.beans.json.institution.request.InstDetSearchReq;
import com.laundrative_v2.app.beans.json.institution.request.InstProdSearchReq;
import com.laundrative_v2.app.beans.json.institution.request.InstSearchReq;
import com.laundrative_v2.app.beans.json.institution.response.InstDetailedRes;
import com.laundrative_v2.app.beans.json.institution.response.InstListQueryRes;
import com.laundrative_v2.app.beans.json.product.response.ProductRes;
import com.laundrative_v2.app.dao.InstitutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InstitutionService
{
    @Autowired
    private InstitutionDao institutionDao;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    private InstitutionDb findInstitutionById(Long id)
    {
        return institutionDao.findById(id);
    }

    public List<InstListQueryRes> searchInstitution(InstSearchReq instSearchReq, String email)
    {
        List<Long> IDS = institutionDao.getAll_The_Institution_By_Neighborhood_Id_And_Time(instSearchReq.getNeighborhoodId(), new Time(instSearchReq.getDate().getTime()));
        NeighborhoodInfo neighborhoodInfo = addressService.getNeighborhoodInfoById(instSearchReq.getNeighborhoodId());
        final List<Long> favoriteList = customerService.getFavoriteInstitutionIds(email);

        return institutionDao.getAll_Institutions_By_List_Of_Id(IDS)
                .stream()
                .filter(e -> e.containsCategories(instSearchReq.getCategories()))
                .map(x ->
                {
                    if(!favoriteList.isEmpty() && favoriteList.contains(x.getId()))
                        return InstListQueryRes.from(x, neighborhoodInfo, instSearchReq.getDate(), true);
                    else
                        return InstListQueryRes.from(x, neighborhoodInfo, instSearchReq.getDate(), false);
                })
                .collect(Collectors.toList());
    }

    @Autowired
    KindService kindService;

    public List<InstDetailedRes> searchInstitutionsDetailed(InstDetSearchReq request)
    {
        /*
            We are using the following : Sunday = 0
            Calendar API uses the following : Sunday = 1

            Therefore we need  to decease the day by one
         */

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(request.getDateR());
        Integer dayR = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.setTime(request.getDateD());
        Integer dayD = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // Getting the address information

        NeighborhoodInfo infoN = addressService.getNeighborhoodInfoById(request.getIdN());

        // Getting all of the institutions in the given neighborhood

        List<Long> isServingList = institutionDao.findAllByNeighborhoodId(request.getIdN());

        // Filtering the institutions for being open or not in the given days

        List<Long> isOpenListDayOne = institutionDao.isWorkingAndOpen(isServingList, dayR).stream().map(y -> y.getInstitutionId()).collect(Collectors.toList());
        List<Long> isOpenListDayOneAndTwo = institutionDao.isWorkingAndOpen(isOpenListDayOne, dayD).stream().map(x -> x.getInstitutionId()).collect(Collectors.toList());

        // Getting the final institutions

        List<Long> responseList = new ArrayList<>();

        CategoryKindBlock[] blocks = request.getBlocks();

        int counter;

        for (Long id : isOpenListDayOneAndTwo)
        {
            counter = 0;

            for (CategoryKindBlock block : blocks)
            {
                if(institutionDao.detailedSearch(id, block.getIdC(), block.getIdK()))
                    counter += 1;
            }
            if (counter == blocks.length)
            {
                responseList.add(id);
            }
        }

        return responseList.stream().map(id -> institutionDao.findById(id)).map(z -> InstDetailedRes.from(z, infoN)).collect(Collectors.toList());
    }

    public List<ProductRes> searchInstitutionProduct(InstProdSearchReq instProdSearchReq)
    {
        List<ProductRes> resultSet = institutionDao.getProducts(instProdSearchReq.getNeighborhoodId())
                .stream()
                .map(element -> ProductRes.from(kindService.findProductsByIdAndCategoryId(element.get(0), element.get(1), instProdSearchReq.getParam())))
                .filter(x -> !ProductRes.isNull(x))
                .collect(Collectors.toList());

        List<ProductRes> responseList = new ArrayList<>();

        resultSet.forEach(r ->
        {
            if(!responseList.contains(r))
                responseList.add(r);
        });
        return responseList;
    }
}
