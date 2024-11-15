package com.trabalho_ia.demo.controllers;

import com.trabalho_ia.demo.entities.UserInfo;
import com.trabalho_ia.demo.services.CalcarioService;
import com.trabalho_ia.demo.services.FuzzyService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private FuzzyService fuzzyService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/calculate")
    public void getUserInfo(@RequestBody UserInfo userInfo) {
        String fuzzyResponse = fuzzyService.calculaFuzzy(userInfo.getCtcPH7(), userInfo.getArgila(), userInfo.getP(), userInfo.getK());
        System.out.println(fuzzyResponse);
    }

    @PostConstruct
    public void testing() {
        CalcarioService service = new CalcarioService();
        System.out.println("Dose de calcário macieira: " + service.calculaDoseCalcario(true, 6.945f));
        System.out.println("Dose de calcário consorciacao: " + service.calculaDoseCalcario(false, 5.51f));
    }

}
