package com.trabalho_ia.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
<<<<<<< HEAD
 * @author
=======
 * @author Lorena Muralha
>>>>>>> e2f3d8ac483db11f113738c9d1f0ef47d521ccd5
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfo {

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

}
