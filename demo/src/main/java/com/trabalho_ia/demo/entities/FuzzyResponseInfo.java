package com.trabalho_ia.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Representa as informações de resposta do serviço fuzzy, contendo os valores
 * de potássio e fósforo por hectare, além do status da operação.
 *
 * Esta classe é utilizada para mapear os dados recebidos da resposta do serviço
 * fuzzy, com a ajuda de anotações do Lombok para gerar getters, setters,
 * construtores e o método toString automaticamente.
 *
 * @author Maria Lorena Muralha Lima
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FuzzyResponseInfo {

    /**
     * Status da resposta, indicando o sucesso ou falha da operação.
     */
    private String status;

    /**
     * Valor de potássio recomendado por hectare, calculado pelo sistema fuzzy.
     */
    @JsonProperty("valor_potassio_hectare")
    private float valorPotassioHectare;

    /**
     * Valor de fósforo recomendado por hectare, calculado pelo sistema fuzzy.
     */
    @JsonProperty("valor_fosforo_hectare")
    private float valorFosforoHectare;
    
    /**
     * Construtor com parâmetros para inicializar a classe com valores específicos.
     * 
     * @param status Status da resposta (ex: "success", "failed").
     * @param valorPotassioHectare Valor de potássio recomendado por hectare.
     * @param valorFosforoHectare Valor de fósforo recomendado por hectare.
     */
    public FuzzyResponseInfo(String status, float valorPotassioHectare, float valorFosforoHectare) {
        this.status = status;
        this.valorPotassioHectare = valorPotassioHectare;
        this.valorFosforoHectare = valorFosforoHectare;
    }

}
