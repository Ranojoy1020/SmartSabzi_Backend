package com.rbanerjee.SmartSabzi.Service;

import com.rbanerjee.SmartSabzi.Entity.Vegetable;
import com.rbanerjee.SmartSabzi.Repository.VegetableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableService {
    private final VegetableRepository vegetableRepository;

    VegetableService(VegetableRepository vegetableRepository){
        this.vegetableRepository = vegetableRepository;
    }

    public List<Vegetable> allVegetables(){
        return vegetableRepository.findAll();
    }

    public Vegetable newVegetable(Vegetable vegetable){
        return vegetableRepository.save(vegetable);
    }
}
