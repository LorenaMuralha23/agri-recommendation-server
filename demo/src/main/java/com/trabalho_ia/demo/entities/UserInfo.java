package com.trabalho_ia.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Representa as informações fornecidas pelo usuário para o cálculo da
 * recomendação de calagem e adubação.
 *
 * Esta classe contém os dados necessários para calcular a recomendação de
 * calcário e adubação (potássio, fósforo) com base nas características do solo
 * e da espécie. Utiliza anotações do Lombok para gerar automaticamente os
 * métodos getters, setters, o construtor sem parâmetros e o método toString.
 *
 * @author Maria Lorena Muralha Lima
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfo {

    /**
     * Tipo da espécie (verdadeiro ou falso). Usado para determinar as
     * características da planta para o cálculo.
     */
    private boolean tipoEspecie;

    /**
     * Área a ser considerada para o cálculo, em hectares.
     */
    private float area;

    /**
     * SMP utilizado no cálculo da dose de calcário.
     */
    private float smp;

    /**
     * CTC do solo a pH 7. Importante para determinar a necessidade de correção
     * do solo.
     */
    private float ctcPH7;

    /**
     * Percentual de argila no solo, que influencia na retenção de nutrientes.
     */
    private float argila;

    /**
     * Quantidade de fósforo no solo, medida em mg/dm³.
     */
    private float p;

    /**
     * Quantidade de potássio no solo, medida em cmol/dm³.
     */
    private float k;

    /**
     * Construtor para inicializar a classe com valores específicos.
     *
     * @param tipoEspecie Tipo da espécie (verdadeiro ou falso).
     * @param area Área em hectares.
     * @param SMP.
     * @param CTCph7 CTC do solo a pH 7.
     * @param argila Percentual de argila no solo.
     * @param P Quantidade de fósforo no solo.
     * @param K Quantidade de potássio no solo.
     */
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
