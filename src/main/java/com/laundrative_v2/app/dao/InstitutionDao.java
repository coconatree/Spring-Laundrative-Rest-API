package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.Institution.*;
import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.beans.json.Request.InstitutionListQueryReq;
import com.laundrative_v2.app.beans.json.Response.InstitutionInfoQueryRes;
import com.laundrative_v2.app.beans.json.Response.InstitutionListQueryRes;

import com.laundrative_v2.app.beans.pojo.KindAndTypeJson;
import com.laundrative_v2.app.beans.pojo.KindPriceJson;
import com.laundrative_v2.app.beans.pojo.TimeDayAsNumber;
import com.laundrative_v2.app.repository.*;
import com.laundrative_v2.app.repository.institutionRepository.*;
import com.laundrative_v2.app.util.Utility;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InstitutionDao
{
    private static final Logger logger = LoggerFactory.getLogger(InstitutionDao.class);

    @Autowired
    private InstitutionRepo institutionRepo;

    @Autowired
    private InstitutionServiceRepo institutionServiceRepo;

    @Autowired
    private InstitutionWorkingRepo institutionWorkingRepo;

    @Autowired
    private InstitutionCategoryRepo institutionCategoryRepo;


    @Autowired
    private NeighborhoodRepo neighborhoodRepo;

    public List<InstitutionListQueryRes> readByObject(InstitutionListQueryReq object)
    {
        List<InstitutionServiceDb> institutionServiceDbList = null;
        List<InstitutionCategoryDb> institutionCategoryDbList = null;
        InstitutionWorkingDb institutionWorkingDb = null;

        NeighborhoodDb neighborhoodDb;


        List<Long> idList;

        ArrayList<InstitutionListQueryRes> responsesList;


            Long objectId = object.getNeighborhoodId();
            Date objectDate = object.getDate();
            List<Long> objectCategoryList = object.getListOfCategories();

            // Getting the neighborhood

            neighborhoodDb = neighborhoodRepo.findById(objectId).get();

            System.out.println("NEIGHBORHOOD ID : " + neighborhoodDb.getId());

            institutionServiceDbList = institutionServiceRepo.findAllByNeighborhoodDbId(neighborhoodDb.getId());

            System.out.println("INSTITUTION SERVICE SIZE : " + institutionServiceDbList.size());

            TimeDayAsNumber timeDayAsNumber = Utility.parseDate(objectDate);

            idList = new ArrayList<>();

            for (InstitutionServiceDb element : institutionServiceDbList)
            {
                System.out.println(" e : " + element.getInstitutionId());
                System.out.println(" a : " + timeDayAsNumber.getTime());
                System.out.println(" t : " + timeDayAsNumber.getTime());
                System.out.println(" y : " + timeDayAsNumber.getDay());

                institutionWorkingDb = institutionWorkingRepo.findByInstitutionIdAndDay(
                        element.getInstitutionId(),
                        timeDayAsNumber.getDay()
                );

                if(institutionWorkingDb == null)
                {
                    System.out.println("IS NULL");
                    continue;
                }


                System.out.println("WORKING  : " + institutionWorkingDb.getInstitutionId() + " - " + institutionWorkingDb.getStartingTime());

                for (Long category : objectCategoryList)
                {
                    if(institutionCategoryRepo.findByInstitutionIdAndAndCategoryId(element.getId(), category) != null)
                    {
                        if(institutionWorkingDb != null && !idList.contains(element.getId()))
                        {
                            idList.add(element.getId());
                        }
                    }
                    else
                        break;
                }
            }

            responsesList = new ArrayList<>();

            initResponseList(neighborhoodDb, idList, objectDate, responsesList);

            return responsesList;


    }

    private List<InstitutionListQueryRes> initResponseList(NeighborhoodDb neighborhoodDb, List<Long> idList, Date clientDate, List<InstitutionListQueryRes> responseList)
    {
        InstitutionListQueryRes responseJson = null;
        InstitutionDb element = null;

        if(idList != null)
        {
            for (Long elementId : idList)
            {
                responseJson = new InstitutionListQueryRes();

                //TODO
                //-Set up the isFavorite using the JWT
                //-Should edit the database so it contains the maximum service price as well

                element = institutionRepo.findById(elementId).get();

                InstitutionServiceDb institutionServiceDb = institutionServiceRepo.findAllByNeighborhoodDbIdAndAndInstitutionId(
                        neighborhoodDb.getId(),
                        element.getId()
                );

                responseJson.setNeighborhoodId(neighborhoodDb.getId());
                responseJson.setNeighborhoodName(neighborhoodDb.getNeighborhoodName());

                responseJson.setInstitutionName(element.getInstitutionName());
                responseJson.setInstitutionId(element.getId());
                responseJson.setMinimumOrderPrice(institutionServiceDb.getMinOrderAmount());
                responseJson.setMaximumServicePrice(new BigDecimal(1000));
                responseJson.setMaximumServicePrice(institutionServiceDb.getMinServiceAmount());
                responseJson.setIsFavorite(false);

                List<InstitutionWorkingDb> list2 = institutionWorkingRepo.findAllByInstitutionId(element.getId());

                responseJson.initWorkingHours(clientDate, institutionWorkingRepo.findByInstitutionIdAndDay(element.getId(), Utility.getDayFromADate(clientDate)));

                responseList.add(responseJson);
            }
        }

        return responseList;
    }


    @Autowired
    private InstitutionKindRepo institutionKindRepo;

    @Autowired
    private KindRepo kindRepo;

    public InstitutionInfoQueryRes read(Long institutionId)
    {
        try
        {
            InstitutionInfoQueryRes response = null;
            KindAndTypeJson kindAndTypeJson = null;
            KindPriceJson kindPriceJson = null;

            InstitutionDb institutionDb = null;
            ArrayList<KindDb> kindDbArray = null;

            // Getting all the necessary information about the institution

            institutionDb = institutionRepo.findById(institutionId).get();

            ArrayList<InstitutionKindDb> institutionKindDbArr = new ArrayList<>(
                    institutionKindRepo.findAllByInstitutionId(institutionId)
            );

            // Getting all the necessary information about the related categories and kinds
            // And building the response object

            response = new InstitutionInfoQueryRes();

            for (InstitutionKindDb element : institutionKindDbArr)
            {
                Long category_Id  = element.getKindCategoryId();

                kindDbArray = new ArrayList<>(kindRepo.findAllByCategoryAndId(category_Id, element.getKindId()));

                kindAndTypeJson = new KindAndTypeJson();
                kindAndTypeJson.setCategoryId(category_Id);

                for (KindDb kindElement : kindDbArray)
                {
                    kindPriceJson = new KindPriceJson();

                    kindPriceJson.setKindId(kindElement.getId());
                    kindPriceJson.setKindName(kindElement.getName());

                    Utility utility = Utility.getInstance();

                    kindPriceJson.setKindImage(utility.imageToBase64(kindElement.getImage()));
                    kindPriceJson.setType(null);
                    kindPriceJson.setPrice(new BigDecimal(0));

                    kindAndTypeJson.add(kindPriceJson);
                }
                response.add(kindAndTypeJson);
            }
            return response;
        }
        catch (Exception e)
        {
            logger.warn("Error cause : \n " + e.getCause());
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn("Error stack trace : \n " + e.getStackTrace());

            return null;
        }
    }
}
