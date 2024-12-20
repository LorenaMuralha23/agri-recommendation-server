package com.trabalho_ia.demo.services;

import org.springframework.stereotype.Service;

/**
 * Serviço responsável pelos cálculos relacionados à aplicação de calcário no
 * solo.
 *
 * Este serviço contém a lógica para calcular a dose de calcário necessária com
 * base no tipo de espécie e no SMP do solo. Ele também oferece um método para
 * calcular a quantidade total de calcário necessária para uma área específica.
 *
 * @author Maria Lorena Muralha Lima
 */
@Service
public class CalcarioService {

    /**
     * Valores de pH para macieira em diferentes faixas de SMP (Soma da Matéria
     * Primitiva).
     */
    private final float[] phMacieira = {15.0f, 12.5f, 10.9f, 9.6f, 6.5f, 7.7f, 6.6f, 6.0f, 5.3f, 4.8f, 4.2f,
        3.7f, 3.2f, 2.8f, 2.3f, 2.0f, 1.6f, 1.3f, 1.0f, 0.8f, 0.6f, 0.4f, 0.2f, 0, 0, 0, 0, 0};

    /**
     * Valores de pH para consorciação de gramíneas e leguminosas de estação
     * fria em diferentes faixas de SMP.
     */
    private final float[] phConsorciacao = {21.0f, 17.3f, 15.1f, 13.3f, 11.9f, 10.7f, 9.9f, 9.1f, 8.3f,
        7.5f, 6.8f, 6.1f, 5.4f, 4.8f, 4.2f, 3.7f, 3.2f, 2.7f, 2.2f, 1.8f, 1.4f, 1.1f, 0.8f, 0.5f, 0.3f, 0.2f, 0, 0};

    /**
     * Calcula a dose de calcário necessária por hectare com base no tipo de
     * espécie e no SMP.
     *
     * Para cada tipo de espécie (macieira ou consorciação de gramíneas e
     * leguminosas), a lógica de cálculo é diferente. A dose de calcário é
     * determinada por tabelas específicas de pH e SMP.
     *
     * @param tipoEspecie Tipo da espécie (false para consorciação de gramíneas
     * e leguminosas, true para macieira).
     * @param SMP, um valor importante para calcular a quantidade de calcário.
     * @return A dose de calcário recomendada por hectare.
     */
    public float calculaDoseCalcario(boolean tipoEspecie, float SMP) {
        float start = 4.4f;
        float phReferencia = 6.0f;
        int parteInteira = (int) SMP;
        float parteDecimal = SMP - parteInteira;

        if (tipoEspecie) {
            phReferencia = 5.5f;
        }

        if (phReferencia == 5.5f) {
            //logica da macieira
            if (SMP <= 4.4f) {
                return 15.0f;
            }

            for (int i = 0; i <= 28; i++) {
                if (start <= SMP && SMP <= start + 0.1) {
                    if (parteDecimal <= 0.5f) {
                        return phMacieira[i];
                    }
                    return phMacieira[i + 1];
                }
                start += 0.1;
            }

        }

        //logica da consorciacao
        if (SMP <= 4.4f) {
            return 21.0f;
        }

        for (int i = 0; i <= 28; i++) {
            if (start <= SMP && SMP <= start + 0.1) {
                if (parteDecimal <= 0.5f) {
                    return phConsorciacao[i];
                }
                return phConsorciacao[i + 1];
            }
            start += 0.1;
        }

        return 0;
    }

    /**
     * Calcula a quantidade total de calcário necessária para uma determinada
     * área.
     *
     * Multiplica a dose de calcário por hectare pela área total para determinar
     * a quantidade total de calcário necessária.
     *
     * @param quantidadeCalcPorHectare A dose de calcário recomendada por
     * hectare.
     * @param area A área total a ser corrigida, em hectares.
     * @return A quantidade total de calcário necessária para a área.
     */
    public float calculaTotalCalcarioArea(float quantidadeCalcPorHectare, float area) {
        return quantidadeCalcPorHectare * area;
    }

}
