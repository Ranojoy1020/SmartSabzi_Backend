package com.rbanerjee.SmartSabzi.Controller;

import com.rbanerjee.SmartSabzi.DTO.CurrentPriceResponse;
import com.rbanerjee.SmartSabzi.DTO.PriceResponse;
import com.rbanerjee.SmartSabzi.DTO.UpdatePriceRequest;
import com.rbanerjee.SmartSabzi.Entity.Price;
import com.rbanerjee.SmartSabzi.Service.PriceService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceService priceService;

    PriceController(PriceService priceService){
        this.priceService = priceService;
    }

    @GetMapping("/current")
    public ResponseEntity<List<CurrentPriceResponse>> getCurrentPrices(){
        return new ResponseEntity<>(priceService.allPrices(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    public ResponseEntity<PriceResponse> updatePrice(@RequestBody UpdatePriceRequest updatePriceRequest){
        return new ResponseEntity<>(priceService.updatePrice(updatePriceRequest), HttpStatusCode.valueOf(201));
    }
}
