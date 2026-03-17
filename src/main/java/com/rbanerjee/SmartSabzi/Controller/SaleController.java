package com.rbanerjee.SmartSabzi.Controller;

import com.rbanerjee.SmartSabzi.DTO.CreateSaleRequest;
import com.rbanerjee.SmartSabzi.Entity.Sale;
import com.rbanerjee.SmartSabzi.Service.SaleService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @PostMapping("")
    public ResponseEntity<UUID> createSale(@RequestBody CreateSaleRequest newSale, Authentication authentication){
        return new ResponseEntity<>(saleService.createSale(newSale, authentication.getName()), HttpStatusCode.valueOf(201));
    }
}
