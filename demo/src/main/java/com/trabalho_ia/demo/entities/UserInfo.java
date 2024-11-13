package com.trabalho_ia.demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author 
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfo {

    public UserInfo(String status, boolean tipoEspecie, float larguraFaixa, float distanciaLinhas, float bases, float SMP, float CTCph7, float argila, float P, float K) {
        this.tipoEspecie = tipoEspecie;
        this.larguraFaixa = larguraFaixa;
        this.distanciaLinhas = distanciaLinhas;
        this.bases = bases;
        this.smp = SMP;
        this.ctcPH7 = CTCph7;
        this.argila = argila;
        this.p = P;
        this.k = K;
    }
    
    
    private String status;
    private boolean tipoEspecie;
    private float larguraFaixa;
    private float distanciaLinhas;
    private float bases;
    private float smp;
    private float ctcPH7;
    private float argila;
    private float p;
    private float k;
    
}
