package com.rbanerjee.SmartSabzi.Service;

import com.rbanerjee.SmartSabzi.DTO.CreateSaleItemRequest;
import com.rbanerjee.SmartSabzi.DTO.CreateSaleRequest;
import com.rbanerjee.SmartSabzi.Entity.Sale;
import com.rbanerjee.SmartSabzi.Entity.SaleItem;
import com.rbanerjee.SmartSabzi.Entity.Vendor;
import com.rbanerjee.SmartSabzi.Entity.VendorVegetable;
import com.rbanerjee.SmartSabzi.Repository.SaleRepository;
import com.rbanerjee.SmartSabzi.Repository.VendorRepository;
import com.rbanerjee.SmartSabzi.Repository.VendorVegetableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final VendorRepository vendorRepository;
    private final VendorVegetableRepository vendorVegetableRepository;

    SaleService(SaleRepository saleRepository,
                VendorRepository vendorRepository,
                VendorVegetableRepository vendorVegetableRepository){
        this.saleRepository = saleRepository;
        this.vendorRepository = vendorRepository;
        this.vendorVegetableRepository = vendorVegetableRepository;
    }

    @Transactional
    public UUID createSale(CreateSaleRequest createSaleRequest, String vendorUsername){

        Vendor vendor = vendorRepository.findByEmail(vendorUsername)
                .orElseThrow(() -> new RuntimeException("Vendor Account Not Found"));

        Sale newSale = new Sale();
        newSale.setVendor(vendor);

        List<SaleItem> saleItemList = new ArrayList<>();
        BigDecimal grandTotal = BigDecimal.ZERO;

        for (CreateSaleItemRequest itemRequest : createSaleRequest.items()){
            VendorVegetable vendorVegetable = vendorVegetableRepository.findById(itemRequest.vendorVegetableId())
                    .orElseThrow(() -> new RuntimeException("Catalog Item Not Found"));

            if (!vendorVegetable.getVendor().getVendorId().equals(vendor.getVendorId())){
                throw new RuntimeException("Unauthorized Catalog Access");
            }

            BigDecimal weight = BigDecimal.valueOf(itemRequest.weight());

            BigDecimal itemTotal = itemRequest.unitPrice().multiply(weight);

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(newSale);
            saleItem.setVendorVegetable(vendorVegetable);
            saleItem.setWeight(weight);
            saleItem.setUnitPrice(itemRequest.unitPrice());
            saleItem.setItemTotal(itemTotal);

            saleItemList.add(saleItem);
            grandTotal = grandTotal.add(itemTotal);
        }

        newSale.setSaleItemList(saleItemList);
        newSale.setTotalAmount(grandTotal);

        return saleRepository.save(newSale).getSaleId();
    }
}
