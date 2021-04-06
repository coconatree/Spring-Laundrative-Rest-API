package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.institutionDb.*;
import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.beans.json.institution.WorkingHoursRes;
import com.laundrative_v2.app.beans.json.institution.response.*;

import com.laundrative_v2.app.beans.json.institution.NeighborhoodInfo;


import com.laundrative_v2.app.repository.*;
import com.laundrative_v2.app.repository.addressRepo.NeighborhoodRepo;
import com.laundrative_v2.app.repository.institutionRepo.*;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class InstitutionDao
{
    private static final Logger logger = LoggerFactory.getLogger(InstitutionDao.class);

    @Autowired
    InstitutionRepo repository;

    @Autowired
    InstitutionWorkingRepo workingRepository;

    @Autowired
    InstitutionCategoryRepo categoryRepo;

    @Autowired
    NeighborhoodRepo neighborhoodRepo;

    @Autowired
    private InstitutionKindRepo kindRepository;

    public List<Long> getAll_The_Institution_By_Neighborhood_Id_And_Time(Long neighborhoodId, Time time)
    {
        return repository.getAllInstitutionFromNeighborhoodIdAndDate(neighborhoodId, time);

    }

    public List<InstitutionDb> getAll_Institutions_By_List_Of_Id(List<Long> idList)
    {
        return repository.findAllByIdIn(idList);
    }

    public List<List<Long>> getProducts(Long neighborhoodId)
    {
        return repository.getProductListAsKindCategoryByNeighborhoodId(neighborhoodId);
    }

    public Boolean detailedSearch(Long instId, Long categoryId, Long kindId)
    {
        return kindRepository.existsByInstitutionIdAndKindCategoryIdAndKindId(instId, categoryId, kindId);
    }

    public List<InstitutionWorkingDb> isWorkingAndOpen(List<Long> instIdList, Integer day)
    {
        return workingRepository.findByInstitutionIdInAndDay(instIdList, day);
    }

    public InstitutionDb findById(Long id)
    {
        return repository.findById(id).get();
    }

    public List<Long> findAllByNeighborhoodId(Long idN)
    {
        return repository.findAllByNeighborhoodId(idN);
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

  /**
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

   List<InstitutionDb> institutionDbList = repository.findAllByIdIn(initialIds)
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

   return repository.findAllByIdIn(resultSet)
   .stream()
   .map(i -> InstDetailedRes.from(i, neighborhoodInfo))
   .collect(Collectors.toList());
   }
   * */

    public List<WorkingHoursRes> getWorkingHours(Long id)
    {
        return workingRepository.findAllByIdCustom(id)
                .stream()
                .map(element -> WorkingHoursRes.from(element))
                .collect(Collectors.toList());
    }
}
