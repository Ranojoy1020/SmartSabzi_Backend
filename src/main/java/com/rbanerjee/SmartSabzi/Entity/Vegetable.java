package com.rbanerjee.SmartSabzi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Vegetable {

    @Id
    @Column
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID vegetableId;

    @Column
    @NotNull
    private String vegetableName;
}
