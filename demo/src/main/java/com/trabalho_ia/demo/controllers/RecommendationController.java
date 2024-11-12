package com.trabalho_ia.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.trabalho_ia.demo.entities.UserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lorena Muralha
 */
@RestController
@RequestMapping("/agri-server/recommendation")
public class RecommendationController {

    public static ObjectMapper jsonDealler = new JsonMapper();

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/calculate")
    public void getUserData(@RequestBody UserInfo userInfo) {
        System.out.println("Dados recebidos:");
        System.out.println("Tipo da Especie: " + userInfo.getTipoEspecie());
        System.out.println("Largura da Faixa: " + userInfo.getLarguraFaixa());
        System.out.println("Distancia de linhas: " + userInfo.getDistanciaLinhas());
        System.out.println("Base: " + userInfo.getBases());
        System.out.println("SMP: " + userInfo.getSmp());
        System.out.println("CTC ph 7: " + userInfo.getCtcPH7());
        System.out.println("Argila: " + userInfo.getArgila());
        System.out.println("P: : " + userInfo.getP());
        System.out.println("K: " + userInfo.getK());
    }

    
}
