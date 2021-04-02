package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.institutionDb.*;
import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.beans.json.institution.request.CategoryKind;
import com.laundrative_v2.app.beans.json.institution.response.*;

import com.laundrative_v2.app.beans.json.institution.NeighborhoodInfo;


import com.laundrative_v2.app.repository.*;
import com.laundrative_v2.app.repository.addressRepo.NeighborhoodRepo;
import com.laundrative_v2.app.repository.institutionRepo.*;
import com.laundrative_v2.app.util.Utility;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class InstitutionDao
{
    private static final Logger logger = LoggerFactory.getLogger(InstitutionDao.class);

    @Autowired
    InstitutionRepo institutionRepo;

    @Autowired
    InstitutionServiceRepo institutionServiceRepo;

    @Autowired
    InstitutionWorkingRepo institutionWorkingRepo;

    @Autowired
    InstitutionCategoryRepo institutionCategoryRepo;

    @Autowired
    NeighborhoodRepo neighborhoodRepo;

    public void test(Long neighborhoodID, Date clientDate, Long [] categories)
    {

    }

    public List<InstListQueryRes> readQueryList(Long neighborhoodID, Date clientDate, Long [] categories)
    {
        try
        {
            // Result Sets

            List<Long> serviceResult;
            List<Long> workingResult = new ArrayList<>();

            // NeighborhoodInfo

            NeighborhoodInfo neighborhoodInfo = NeighborhoodInfo.from(neighborhoodRepo.findById(neighborhoodID).get());

            // Getting all the institutions in the given neighborhood

            serviceResult = institutionServiceRepo.searchBy(neighborhoodID);

            // Setting up the day and time data

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(clientDate);

            // Getting all the institutions which are open at given date and they operate in the given neighborhood

            institutionWorkingRepo.findAllByInstitutionIdInAndDayAndStartingTimeLessThanEqualAndEndingTimeGreaterThanEqual(
                    serviceResult,
                    calendar.get(Calendar.DAY_OF_WEEK),
                    new Time(clientDate.getTime()),
                    new Time(clientDate.getTime())
            ).forEach(p -> workingResult.add(p.getInstitutionId()));

            // Filtering for the categories and mapping to the response object and returning it

            return institutionRepo.findAllByIdIn(workingResult).stream()
                    .filter(e -> e.containsCategories(categories))
                    .map(i -> InstListQueryRes.from(i, neighborhoodInfo, clientDate))
                    .collect(Collectors.toList()
                    );
        }
        catch (Exception e)
         {
            logger.warn(e.getMessage());
         }
        return null;
    }

    @Autowired
    private InstitutionKindRepo institutionKindRepo;

    @Autowired
    private KindRepo kindRepo;

    public List<InstInfoQueryRes> read(Long institutionId)
    {
        try
        {
            List<InstitutionKindDb> institutionKindDbList;
            List<InstInfoQueryRes>  response = new ArrayList<>();

            // Getting all the necessary information about the institution

            institutionKindDbList = institutionKindRepo.findAllByInstitutionId(institutionId);

            // Getting all the necessary information about the related categories and kinds
            // And building the response object

            try
            {
                InstitutionDb institutionDb = null;
                ArrayList<KindDb> kindDbArray = null;

                // Getting all the necessary information about the institution

                institutionDb = institutionRepo.findById(institutionId).get();

                ArrayList<InstitutionKindDb> institutionKindDbArr = new ArrayList<>(
                        institutionKindRepo.findAllByInstitutionId(institutionId)
                );

                // Getting all the necessary information about the related categories and kinds
                // And building the response object

                InstInfoQueryRes res = null;
                KindPrice kindPrice = null;

                response = new ArrayList<>();

                for (InstitutionKindDb element : institutionKindDbArr)
                {
                    Long category_Id  = element.getKindCategoryId();

                    //TODO
                    //Old One
                    //- findAllByCategoryAndId

                    kindDbArray = new ArrayList<>(kindRepo.findAllByInstitutionId(0L));

                    res = new InstInfoQueryRes();
                    res.setCategoryId(category_Id);

                    for (KindDb kindElement : kindDbArray)
                    {
                        kindPrice = new KindPrice();

                        kindPrice.setKindId(kindElement.getId());
                        kindPrice.setKindName(kindElement.getName());

                        Utility utility = Utility.getInstance();

                        kindPrice.setKindImage(utility.imageToBase64(kindElement.getImage()));
                        kindPrice.setType(null);
                        kindPrice.setPrice(new BigDecimal(0));

                        res.add(kindPrice);
                    }
                    response.add(res);
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
        catch (Exception e)
        {
            logger.warn("Error cause : \n " + e.getCause());
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn("Error stack trace : \n " + e.getStackTrace());

            return null;
        }
    }

    public List<InstDetailedRes> detailedSearch(Long neighborhoodId, Date receivingDate, Date deliveryDate, CategoryKind [] array, boolean freeService)
    {
        try
        {
            // Getting the neighborhood info

            logger.warn("NEIG ID : " + neighborhoodId + " DATE 1 : " + receivingDate + " DATE 2 : " + deliveryDate);
            logger.warn("ARRAY : \n");
            Arrays.stream(array).forEach(e -> logger.warn("ITEM : " + e));
            logger.warn("BOOLEAN : " + freeService);

            NeighborhoodInfo neighborhoodInfo = NeighborhoodInfo.from(neighborhoodRepo.findById(neighborhoodId).get());

            logger.warn("NEIGH INFO : " + neighborhoodInfo);

            int [] days = new int[2];

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(receivingDate);
            days[0] = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.setTime(deliveryDate);
            days[1] = calendar.get(Calendar.DAY_OF_WEEK);

            List<Long> initialIds = institutionServiceRepo.searchBy(neighborhoodId);

            logger.warn("INITIAL IDS : ");
            initialIds.stream().forEach(e -> logger.warn("ITEM : " + e));

            // Will change the following

            List<Long> ids_Of_Open_And_Serving_To_The_Neighborhood = new ArrayList<>();

            int counter = 0;

            institutionRepo.findAllByIdIn(initialIds).stream().forEach(e -> logger.warn("HAS BEEN FOUND IN THE INITIAL SEARCH : " + e.getInstitutionName()));

            for (Long id : initialIds)
            {
                for (CategoryKind element : array)
                {
                    if(institutionKindRepo.existsByInstitutionIdAndKindIdAndKindCategoryId(
                            id,
                            element.getKindId(),
                            element.getCategoryId()))
                    {
                        logger.warn("FOUND : " + element);
                        counter++;
                    }
                    logger.warn("TURNING FOR " + element);
                }
                if(counter == array.length)
                {
                    logger.warn("ADDED : " + id);
                    ids_Of_Open_And_Serving_To_The_Neighborhood.add(id);
                }
                counter = 0;
            }

            // Will ask the freeServices working principle

            return institutionRepo.findAllByIdIn(
                    ids_Of_Open_And_Serving_To_The_Neighborhood
            ).stream().filter(e -> e.is_Open_In_The_Given_Day(days)).map(i -> InstDetailedRes.from(i, neighborhoodInfo)).collect(Collectors.toList());
        }
        catch (Exception e)
        {
            logger.warn(e.getMessage());
        }
        return null;
    }

    public List<WorkingHoursRes> getWorkingHours(Long id)
    {
        return institutionWorkingRepo.findAllByIdCustom(id)
                .stream()
                .map(element -> WorkingHoursRes.from(element))
                .collect(Collectors.toList());
    }
}
