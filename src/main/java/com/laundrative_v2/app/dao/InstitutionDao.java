package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.institutionDb.*;
import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.beans.json.institution.WorkingHoursRes;
import com.laundrative_v2.app.beans.json.institution.request.CategoryKind;
import com.laundrative_v2.app.beans.json.institution.response.KindPrice;
import com.laundrative_v2.app.beans.json.institution.response.*;

import com.laundrative_v2.app.beans.json.institution.NeighborhoodInfo;


import com.laundrative_v2.app.repository.*;
import com.laundrative_v2.app.repository.addressRepo.NeighborhoodRepo;
import com.laundrative_v2.app.repository.institutionRepo.*;
import com.laundrative_v2.app.utility.Utility;
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
        List<Long> kindIds;
        List<InstInfoQueryRes>  responseList;

        // Finding the institution

        kindIds = institutionKindRepo.findAllByInstitutionId(institutionId).stream()
                .map(e -> e.getKindId())
                .collect(Collectors.toList()
                );

        //TODO
        // change this when there will be test date

        List<KindDb> kindList = kindRepo.findAllByInstitutionId(0L);

        return kindList.stream()
                .map(e -> InstInfoQueryRes.from(e))
                .collect(Collectors.toList());

    }

    public List<InstDetailedRes> detailedSearch(Long neighborhoodId, Date receivingDate, Date deliveryDate, CategoryKind[] array, boolean freeService)
    {

        //TODO
        // Add free service filtering

        NeighborhoodInfo neighborhoodInfo = NeighborhoodInfo.from(neighborhoodRepo.findById(neighborhoodId).get());

        int [] days = new int[2];

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(receivingDate);
        days[0] = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.setTime(deliveryDate);
        days[1] = calendar.get(Calendar.DAY_OF_WEEK);

        // All the institutions which are working at the given neighborhood

        List<Long> initialIds = institutionServiceRepo.searchBy(neighborhoodId);

        // Filtering the institutions according to the date

        //TODO
        // Should directly return the id column

        List<InstitutionDb> institutionDbList = institutionRepo.findAllByIdIn(initialIds)
                .stream()
                .filter(e -> e.isOpenInTheGivenDays(days))
                .collect(Collectors.toList());

        List<Long> IDS = institutionDbList.stream().map(e -> e.getId()).collect(Collectors.toList());

        List<Long> resultSet = new ArrayList<>();

        int counter = 0;

        for (Long id : IDS)
        {
            for (CategoryKind element : array)
            {
                if(institutionKindRepo.existsByInstitutionIdAndKindIdAndKindCategoryId(id, element.getKindId(), element.getCategoryId()))
                {
                    counter++;
                }
            }
            if(counter == array.length)
            {
                resultSet.add(id);
            }
            counter = 0;
        }

        return institutionRepo.findAllByIdIn(resultSet)
                .stream()
                .map(i -> InstDetailedRes.from(i, neighborhoodInfo))
                .collect(Collectors.toList());
    }

    public List<WorkingHoursRes> getWorkingHours(Long id)
    {
        return institutionWorkingRepo.findAllByIdCustom(id)
                .stream()
                .map(element -> WorkingHoursRes.from(element))
                .collect(Collectors.toList());
    }
}
