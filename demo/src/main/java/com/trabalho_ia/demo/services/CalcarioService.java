package com.trabalho_ia.demo.services;

import org.springframework.stereotype.Service;

/**
 *
 * @author USER
 */
@Service
public class CalcarioService {

    private final float[] phMacieira = {15.0f, 12.5f, 10.9f, 9.6f, 6.5f, 7.7f, 6.6f, 6.0f, 5.3f, 4.8f, 4.2f,
        3.7f, 3.2f, 2.8f, 2.3f, 2.0f, 1.6f, 1.3f, 1.0f, 0.8f, 0.6f, 0.4f, 0.2f, 0};

    private final float[] phConsorciacao = {21.0f, 17.3f, 15.1f, 13.3f, 11.9f, 10.7f, 9.9f, 9.1f, 8.3f,
        7.5f, 6.8f, 6.1f, 5.4f, 4.8f, 4.2f, 3.7f, 3.2f, 2.7f, 2.2f, 1.8f, 1.4f, 1.1f, 0.8f, 0.5f, 0.3f, 0.2f, 0};

    //DC (t/ha) = NC x (LFA/DLP) x (100/PRNT)
    //tipoEspecie = false = 0 = consorciação de gramíneas e leguminosas de estação fria
    //tipoEspecie = true = 1 = macieira
    /*Manual se assume uma provável correspondência entre o valor do pH
    de referência das culturas com o valor V%, sendo: pH 5,5 = V 65%; pH
    6,0 = V 75% e pH 6,5 = V 85%.*/
    public float calculaDoseCalcario(boolean tipoEspecie, float larguraFaixa, float distanciaLinha, float SMP, float bases, float CTC) {
        float phReferencia = 6.0f;
        float v1 = 0.75f;

        if (true) {
            phReferencia = 5.5f;
            v1 = 0.65f;
        }

        float NC = calculaNC(v1, bases, CTC);
        float PRNT = definePRNT(SMP, phReferencia);

        float doseCalcario = NC * (larguraFaixa / distanciaLinha) * (100 / PRNT);

        return doseCalcario;
    }

    //NC = [(V1-bases)/100] x CTCpH 7,0
    public float calculaNC(float v1, float bases, float CTC) {
        float NC = ((v1 - bases) / 100) * CTC;
        return NC;
    }

    public float definePRNT(float SMP, float phReferencia) {
        float start = 4.4f;
        if (phReferencia == 5.5) {
            //logica da macieira
            if (SMP <= 4.4) {
                return 15.0f;
            }

            if (SMP > 6.6) {
                return 0;
            }
            
            for (int i = 1; i <= 28; i++) {
                if (start < SMP && SMP <= start + 0.1) {
                    return phMacieira[i];
                }
                start += 0.1;
            }

        }

        //logica da consorciacao
        if (SMP <= 4.4) {
            return 21.0f;
        }

        if (SMP >= 7.0) {
            return 0;
        }

        for (int i = 1; i <= 28; i++) {
            if (start < SMP && SMP <= start + 0.1) {
                return phConsorciacao[i];
            }
            start += 0.1;
        }

        return 0;
    }
}
