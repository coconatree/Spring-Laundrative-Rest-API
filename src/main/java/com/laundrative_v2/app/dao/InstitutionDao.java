package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.Institution.*;
import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.beans.json.Request.InstitutionListQueryReq;
import com.laundrative_v2.app.beans.json.Response.InstitutionInfoQueryRes;
import com.laundrative_v2.app.beans.json.Response.InstitutionListQueryRes;

import com.laundrative_v2.app.beans.pojo.KindAndTypeJson;
import com.laundrative_v2.app.beans.pojo.KindPriceJson;

import com.laundrative_v2.app.repository.*;
import com.laundrative_v2.app.repository.institutionRepository.*;
import com.laundrative_v2.app.util.Utility;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;

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

    public List<InstitutionListQueryRes> queryList(Long neighborhoodID, Date clientDate, Long [] categories)
    {
        List<InstitutionServiceDb> institutionServiceDbList = null;

        NeighborhoodDb neighborhoodDb;

        // Response Init Variables

        List<Long> idList;


        // Getting the neighborhood

        neighborhoodDb = neighborhoodRepo.findById(neighborhoodID).get();

        institutionServiceDbList = institutionServiceRepo.findAllByNeighborhoodDbId(neighborhoodDb.getId());

        idList = new ArrayList<>();

        Calendar calendar = Utility.getCalendar(clientDate);

        for (InstitutionServiceDb serviceElement : institutionServiceDbList)
        {
            // Will get the date query output

            Time time  = new Time(clientDate.getTime());

            System.out.println("TIME TIME TIME : " + time);

            List<InstitutionWorkingDb> workingDbList = new ArrayList<>(
                    institutionWorkingRepo.findByTimeBetween(calendar.get(Calendar.DAY_OF_WEEK) - 1, time)
            );

            System.out.println("EXIST : " + institutionWorkingRepo.existsByInstitutionIdAndDayAndEndingTimeIsGreaterThanEqualAndAndStartingTimeIsLessThan(
                    serviceElement.getInstitutionId(),
                    calendar.get(Calendar.DAY_OF_WEEK) - 1,
                    time,
                    time
            ));

            Long serviceElementID = serviceElement.getInstitutionId();

            for (Long category : categories)
            {
                if(institutionCategoryRepo.existsByInstitutionIdAndAndCategoryId(serviceElementID, category))
                    if(!idList.contains(serviceElementID))
                    {
                        idList.add(serviceElementID);
                    }
                else
                    break;
            }
        }

        // Creating the response

        if(idList != null && 0 < idList.size())
        {
            List<InstitutionDb> resultSet = institutionRepo.findAllByIdIn(idList);
            List<InstitutionListQueryRes> responseList = new ArrayList<>();

            InstitutionListQueryRes responseJson = null;

            for (InstitutionDb responseEntity : resultSet)
            {
                responseJson = new InstitutionListQueryRes();

                //TODO
                //-Set up the isFavorite using the JWT
                //-Should edit the database so it contains the maximum service price as well

                InstitutionServiceDb institutionServiceDb = institutionServiceRepo.findAllByNeighborhoodDbIdAndAndInstitutionId(
                        neighborhoodDb.getId(),
                        responseEntity.getId()
                );

                responseJson.setNeighborhoodId(neighborhoodDb.getId());
                responseJson.setNeighborhoodName(neighborhoodDb.getNeighborhoodName());

                responseJson.setInstitutionName(responseEntity.getInstitutionName());
                responseJson.setInstitutionId(responseEntity.getId());
                responseJson.setMinimumOrderPrice(institutionServiceDb.getMinOrderAmount());
                responseJson.setMaximumServicePrice(new BigDecimal(1000));
                responseJson.setMaximumServicePrice(institutionServiceDb.getMinServiceAmount());
                responseJson.setIsFavorite(false);
                responseJson.init(clientDate);

                responseList.add(responseJson);
            }
            return responseList;
        }
        return null;
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
