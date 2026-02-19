package com.rbanerjee.SmartSabzi.Controller;

import com.rbanerjee.SmartSabzi.Entity.Vegetable;
import com.rbanerjee.SmartSabzi.Service.VegetableService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vegetable")
public class VegetableController {
    private final VegetableService vegetableService;

    VegetableController(VegetableService vegetableService){
        this.vegetableService = vegetableService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Vegetable>> getAllVegetables(){
        return ResponseEntity.ok(vegetableService.allVegetables());
    }

    @PostMapping("/")
    public ResponseEntity<Vegetable> newVegetables(@RequestBody Vegetable vegetable){
        return new ResponseEntity<>(vegetableService.newVegetable(vegetable), HttpStatusCode.valueOf(201));
    }
}
