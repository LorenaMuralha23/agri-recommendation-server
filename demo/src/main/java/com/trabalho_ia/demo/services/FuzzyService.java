package com.trabalho_ia.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Serviço responsável por se comunicar com o microsserviço de fuzzy e realizar cálculos relacionados
 * ao potássio e fósforo para áreas agrícolas.
 * 
 * A principal função dessa classe é enviar dados para o microsserviço de fuzzy, que processa esses dados
 * e retorna os valores necessários para o cálculo da recomendação de potássio e fósforo. Ela também fornece
 * métodos para calcular a quantidade total de fósforo e potássio necessária para uma área.
 * 
 * @author Maria Lorena Muralha Lima
 */
@Service
public class FuzzyService {
    
    @Autowired
    private RestTemplate restTemplate; // RestTemplate para realizar requisições HTTP
    
    // Instância do ObjectMapper para conversão de objetos Java para JSON e vice-versa
    public static ObjectMapper jsonDealler = new JsonMapper();
    
    // URL do microsserviço Python que processa a requisição fuzzy
    private final String pythonServiceUrl = "http://localhost:8000/dashboard/process_request/";
    
     /**
     * Realiza o cálculo fuzzy chamando o microsserviço Python.
     * 
     * A função prepara um objeto JSON com os parâmetros necessários, faz a requisição POST para o 
     * microsserviço e retorna a resposta recebida. O microsserviço processa os dados e retorna a recomendação
     * de potássio e fósforo para o solo.
     * 
     * @param ctc Valor de CTC (Capacidade de Troca Catiônica) no pH 7.
     * @param argila Teor de argila do solo.
     * @param P Quantidade de fósforo no solo.
     * @param K Quantidade de potássio no solo.
     * @return A resposta do microsserviço de fuzzy como uma string JSON.
     */
    public String calculaFuzzy(float ctc, float argila, float P, float K){
        ObjectNode jsonNode = jsonDealler.createObjectNode();

        jsonNode.put("CTC_ph7", ctc);
        jsonNode.put("argila", argila);
        jsonNode.put("P", P);
        jsonNode.put("K", K);

        String jsonToSend = jsonNode.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(jsonToSend, headers);
        ResponseEntity<String> response = restTemplate.exchange(pythonServiceUrl, HttpMethod.POST, request, String.class);
        
        return response.getBody();
    }
    
     /**
     * Calcula o total de fósforo necessário para uma área com base no valor por hectare.
     * 
     * @param pPorHectare Quantidade de fósforo por hectare, retornada pelo microsserviço fuzzy.
     * @param areaTotal A área total em hectares que necessita de fósforo.
     * @return A quantidade total de fósforo necessária para a área.
     */
    public float calculaTotalFosforoparaArea(float pPorHectare, float areaTotal){
        return pPorHectare * areaTotal;
    }
    
    /**
     * Calcula o total de potássio necessário para uma área com base no valor por hectare.
     * 
     * @param kPorHectare Quantidade de potássio por hectare, retornada pelo microsserviço fuzzy.
     * @param areaTotal A área total em hectares que necessita de potássio.
     * @return A quantidade total de potássio necessária para a área.
     */
    public float calculaTotalPotassioparaArea(float kPorHectare, float areaTotal){
        return kPorHectare * areaTotal;
    }
}
