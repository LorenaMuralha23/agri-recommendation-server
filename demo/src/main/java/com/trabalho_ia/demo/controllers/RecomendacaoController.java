package com.trabalho_ia.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trabalho_ia.demo.entities.UserInfo;
import com.trabalho_ia.demo.services.CalcarioService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
