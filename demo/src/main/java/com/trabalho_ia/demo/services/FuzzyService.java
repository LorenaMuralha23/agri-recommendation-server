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
 *
 * @author USER
 */
@Service
public class FuzzyService {
    
    @Autowired
    private RestTemplate restTemplate;
    public static ObjectMapper jsonDealler = new JsonMapper();
    private final String pythonServiceUrl = "http://localhost:8000/dashboard/process_request/";
    
    //conecta com o microsserviço de fuzzy
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
}
