package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.Institution.*;
import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.beans.json.Response.InstitutionInfoQueryRes;
import com.laundrative_v2.app.beans.json.Response.InstListQueryRes;

import com.laundrative_v2.app.beans.json.Response.NeighborhoodInfo;
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
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<InstListQueryRes> readQueryList(Long neighborhoodID, Date clientDate, Long [] categories)
    {
        try
        {
            // Result Sets

            List<Long> serviceResult;
            List<Long> workingResult;
            List<Long> categoryResult;

            // This is the filtered ID list

            List<Long> filteredIDList;

            // Final Result Set

            List<InstitutionDb> finalResultSet;

            // Getting all the institutions in the given neighborhood

            serviceResult = institutionServiceRepo.searchBy(neighborhoodID);

            // Setting up the day and time data

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(clientDate);

            // Getting all the institutions which are open at given date and they operate in the given neighborhood

            workingResult = institutionWorkingRepo.searchByDayTime(
                    serviceResult,
                    calendar.get(Calendar.DAY_OF_WEEK),
                    new Time(clientDate.getTime())
            );

            // Getting all the institutions which are open at given date and they operate in the given neighborhood
            // and they serve to at least one of the given categories

            categoryResult = institutionCategoryRepo.searchByList(
                    workingResult,
                    categories
            );

            // Initializing the filteredIDList and a validator boolean value for filtering

            filteredIDList = new ArrayList<>();
            boolean validator = false;

            // First for iterates over the categoryResult sets ids

            for (Long id : categoryResult)
            {
                for (Long category : categories)
                {
                    // And the inner loops check if the given institution is serving all of the categories or just one
                    validator = institutionCategoryRepo.existsByInstitutionIdAndCategoryId(id, category);
                }
                if(validator && !filteredIDList.contains(id))
                    // If the id doesn't exist in the loop and the institution serves to all of the
                    // desired categories it is added to the filteredIDList
                    filteredIDList.add(id);
            }

            // Getting the final result set and the neighborhood

            finalResultSet = institutionRepo.findAllByIdIn(filteredIDList);
            NeighborhoodInfo neighborhoodInfo = NeighborhoodInfo.from(neighborhoodRepo.findById(neighborhoodID).get());

            // Results are mapped and returned

            //TODO
            // This will be converted to a stream
            //FIXME
            // convert me to a stream

            List<InstListQueryRes> responseList = new ArrayList<>();

            for(InstitutionDb db : finalResultSet)
            {
                responseList.add(InstListQueryRes.from(db, neighborhoodInfo));
            }

            return responseList;
        }
        catch (Exception e)
        {
            logger.warn(e.getMessage());
        }
        return null;
    }

    private void validate(){};


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
