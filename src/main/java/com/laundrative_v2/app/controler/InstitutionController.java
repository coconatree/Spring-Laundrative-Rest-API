package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.json.Request.CategoryKind;
import com.laundrative_v2.app.beans.json.Request.InstDetailedReq;
import com.laundrative_v2.app.beans.json.Request.InstInfoQueryRes;
import com.laundrative_v2.app.beans.json.Response.InstDetailedRes;
import com.laundrative_v2.app.beans.json.Response.InstListQueryRes;
import com.laundrative_v2.app.dao.InstitutionDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/institution")
public class InstitutionController
{
    @Autowired
    InstitutionDao institutionDao;

    @GetMapping("/")
    public ResponseEntity<Object> getByRequestBodyObject(
            @RequestParam("neighborhoodId") Long neighborhoodId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date date,
            @RequestParam("categories") Long [] categories
    )
    {
        List<InstListQueryRes> response =  institutionDao.readQueryList(neighborhoodId, date, categories);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{institutionId}")
    public ResponseEntity<Object> getById(@PathVariable(value = "institutionId") Long institutionId)
    {
        List<InstInfoQueryRes> response = institutionDao.read(institutionId);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * Detailed search
     *
     * request:
     * {mahalle_id,teslim_alma_zaman,teslim_etme_zaman,
     * [kategori, cins],
     * ucretsiz_teslimat} // isaretli ise 'true' degil ise 'false'
     *
     * response: [{mahalle_id, mahalle_adi,
     * calisma_saatleri:[{gun, baslangic_saati, bitis_saati}] // gün bazında
     * isletme_adi, isletme_id,
     * min_siparis_tutari,
     * min_siparis_tutari_ucretsiz_servis,
     * favori // favori ise 1 degilse 0
     * }]
     * */


    @PostMapping("detailed/")
    public ResponseEntity<Object> detailedSearch(@RequestBody InstDetailedReq request)
    {
        List<InstDetailedRes> response = institutionDao.detailedSearch(
                request.getNeighborhoodId(),
                request.getReceivingDate(),
                request.getDeliveryDate(),
                request.getArray(),
                request.getFreeService()
        );

        if(response == null)
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("", response, HttpStatus.OK);
    }
}
