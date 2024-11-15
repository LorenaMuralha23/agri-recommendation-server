package com.trabalho_ia.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
*/
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfo {

    private boolean tipoEspecie;
    private float area;
    private float smp;
    private float ctcPH7;
    private float argila;
    private float p;
    private float k;

    public UserInfo(boolean tipoEspecie, float area, float SMP, float CTCph7, float argila, float P, float K) {
        this.tipoEspecie = tipoEspecie;
        this.area = area;
        this.smp = SMP;
        this.ctcPH7 = CTCph7;
        this.argila = argila;
        this.p = P;
        this.k = K;
    }

}
