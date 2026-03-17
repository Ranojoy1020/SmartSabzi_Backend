package com.rbanerjee.SmartSabzi.Service;

import com.rbanerjee.SmartSabzi.DTO.CurrentPriceResponse;
import com.rbanerjee.SmartSabzi.DTO.PriceResponse;
import com.rbanerjee.SmartSabzi.DTO.UpdatePriceRequest;
import com.rbanerjee.SmartSabzi.Entity.Price;
import com.rbanerjee.SmartSabzi.Entity.VendorVegetable;
import com.rbanerjee.SmartSabzi.Repository.PriceRepository;
import com.rbanerjee.SmartSabzi.Repository.VendorVegetableRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PriceService {
    private final PriceRepository priceRepository;
    private final VendorVegetableRepository vendorVegetableRepository;

    PriceService(PriceRepository priceRepository, VendorVegetableRepository vendorVegetableRepository){
        this.priceRepository = priceRepository;
        this.vendorVegetableRepository = vendorVegetableRepository;
    }

//    TODO: Currently this will fetch all prices, update function to fetch vendor-specific prices.

    public List<CurrentPriceResponse> allPrices(){
        return priceRepository.findAll().stream()
                .map(CurrentPriceResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public PriceResponse updatePrice(UpdatePriceRequest updatePriceRequest){
        VendorVegetable vendorVegetable = vendorVegetableRepository.findById(updatePriceRequest.vendorVegetableId())
                .orElseThrow(()-> new RuntimeException("Catalog Item Not Found"));

        Price newPrice = new Price();
        newPrice.setVendorVegetable(vendorVegetable);
        newPrice.setPricePerKg(updatePriceRequest.pricePerKg());

        priceRepository.save(newPrice);

        return PriceResponse.fromEntity(newPrice);
    }
}
