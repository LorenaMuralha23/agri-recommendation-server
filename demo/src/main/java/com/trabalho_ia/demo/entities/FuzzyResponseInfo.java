package com.trabalho_ia.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author USER
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FuzzyResponseInfo {
    
    private String status;
    
    @JsonProperty("valor_potassio_hectare")
    private float valorPotassioHectare;
    
    @JsonProperty("valor_fosforo_hectare")
    private float valorFosforoHectare;

    public FuzzyResponseInfo(String status, float valorPotassioHectare, float valorFosforoHectare) {
        this.status = status;
        this.valorPotassioHectare = valorPotassioHectare;
        this.valorFosforoHectare = valorFosforoHectare;
    }
    
}
