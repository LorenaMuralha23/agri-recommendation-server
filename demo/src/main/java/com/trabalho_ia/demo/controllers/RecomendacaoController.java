package com.trabalho_ia.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.trabalho_ia.demo.entities.UserInfo;
import com.trabalho_ia.demo.services.CalcarioService;
import jakarta.annotation.PostConstruct;
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
public class RecomendacaoController {

    public static ObjectMapper jsonDealler = new JsonMapper();

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/calculate")
    public void getUserInfo(@RequestBody UserInfo userInfo) {
        
       

    }

    @PostConstruct
    public void testing() {
        CalcarioService service = new CalcarioService();
        System.out.println("Dose de calcário macieira: " + service.calculaDoseCalcario(true, 6.945f));
        System.out.println("Dose de calcário consorciacao: " + service.calculaDoseCalcario(false, 5.51f));
    }

}
