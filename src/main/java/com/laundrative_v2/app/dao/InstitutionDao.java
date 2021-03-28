package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.Institution.InstitutionCategoryDb;
import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
import com.laundrative_v2.app.beans.db.Institution.InstitutionServiceDb;
import com.laundrative_v2.app.beans.json.InstitutionRequestJson;
import com.laundrative_v2.app.beans.json.InstitutionResponseJson;
import com.laundrative_v2.app.repository.*;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionDao
{
    private static final Logger logger = LoggerFactory.getLogger(InstitutionDao.class);

    @Autowired
    private InstitutionRepo institutionRepo;

    @Autowired
    private InstitutionCategoryRepo institutionCategoryRepo;

    @Autowired
    private InstitutionKindRepo institutionKindRepo;

    @Autowired
    private InstitutionServiceRepo institutionServiceRepo;

    @Autowired
    private InstitutionWorkingRepo institutionWorkingRepo;

    @Autowired
    private NeighborhoodRepo neighborhoodRepo;

    public List<InstitutionResponseJson> readByObject(InstitutionRequestJson object)
    {
        // May need optimization

        try
        {
            // Some questions about the filtering by date

            // Not in use at this moment
            // Date date = object.getDate();

            ArrayList<Long> listOfCategories = object.getListOfCategories();

            List<Long> filteredInstitutionId = new ArrayList<>();

            List<InstitutionServiceDb> unFilteredListOfInstitutions = institutionServiceRepo
                    .findByNeighborhoodId(object.getNeighborhoodId());

            for (InstitutionServiceDb institutionServiceDbElement : unFilteredListOfInstitutions)
            {
                for (InstitutionCategoryDb institutionCategoryDbElement : institutionCategoryRepo
                        .findByInstitutionId(institutionServiceDbElement.getInstitution().getId()))
                {
                    for (Long categoryId : listOfCategories)
                    {
                        if(institutionCategoryDbElement.getCategoryId() == categoryId)
                        {
                            Long idHolder = institutionCategoryDbElement.getInstitutionId();

                            if(!filteredInstitutionId.contains(idHolder))
                            {
                                filteredInstitutionId.add(idHolder);
                            }
                        }
                    }
                }
            }


            Long neighborhoodId = object.getNeighborhoodId();
            String neighborhoodName = neighborhoodRepo.findById(neighborhoodId).get().getNeighborhoodName();

            List<InstitutionResponseJson> responseList = new ArrayList<>();

            for (Long id : filteredInstitutionId)
            {
                InstitutionResponseJson responseEntity = new InstitutionResponseJson();

                String institutionName = institutionRepo.findById(id).get().getInstitutionName();

                // Some questions about min service cost and minimum order cost and maximum order cost is missing in the database

                InstitutionServiceDb institutionService = institutionServiceRepo.findByInstitutionId(id);

                responseEntity.setNeighborhoodId(neighborhoodId);
                responseEntity.setNeighborhoodName(neighborhoodName);
                responseEntity.setStartingHour(null); // For now
                responseEntity.setClosingHour(null);  // For now
                responseEntity.setInstitutionName(institutionName);
                responseEntity.setInstitutionId(id);
                responseEntity.setMinimumOrderPrice(institutionService.getMinOrderAmount());
                responseEntity.setMaximumServicePrice(null);
                responseEntity.setFreeServicePrice(institutionService.getMinServiceAmount()); // Free Service and Min service ???
                responseEntity.setIsFavorite(false); // For now

                responseList.add(responseEntity);
            }

            return responseList;
        }
        catch (Exception e)
        {
            logger.warn("Error cause : \n " + e.getCause());
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn("Error stack trace : \n " + e.getStackTrace());

            return null;
        }
    }

    public InstitutionDb read(Long institutionId)
    {
        try
        {
            return institutionRepo.findById(institutionId).get();
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
