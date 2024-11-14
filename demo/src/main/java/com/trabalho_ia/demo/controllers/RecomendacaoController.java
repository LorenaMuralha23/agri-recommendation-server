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
        float SMPmacieira = service.definePRNT(4.61f, 5.5f);
        float SMPconsorciacao = service.definePRNT(5.34f, 6.0f);
        float NCmac = service.calculaNC(0.65f, 82.42f, 19.52f);
        float NCcons = service.calculaNC(0.75f, 82.42f, 19.52f);
        //NC = [(0.65-82.42)/100] x 19.;
        //float v1, float bases, float CTC
        System.out.println("NC macieira: " + NCmac);
        System.out.println("NC consorciacao: " + NCcons);

        System.out.println("Dose de calcário macieira: " + service.calculaDoseCalcario(true, (float) 154.2, (float) 20.2, (float) 6.21, (float) 82.42, (float) 19.52));
    }

}
