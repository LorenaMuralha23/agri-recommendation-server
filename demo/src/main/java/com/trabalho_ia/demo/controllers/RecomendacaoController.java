package com.trabalho_ia.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trabalho_ia.demo.entities.FuzzyResponseInfo;
import com.trabalho_ia.demo.entities.UserInfo;
import com.trabalho_ia.demo.services.CalcarioService;
import com.trabalho_ia.demo.services.FuzzyService;
import static com.trabalho_ia.demo.services.FuzzyService.jsonDealler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private CalcarioService calcarioService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/calculate")
    public ResponseEntity<String> getUserRequest(@RequestBody UserInfo userInfo) {
        try {
            //{"status": "success", "valor_potassio_hectare": 47.22, "valor_fosforo_hectare": 133.56}
            String status = "failed";
            String fuzzyResponse = fuzzyService.calculaFuzzy(userInfo.getCtcPH7(), userInfo.getArgila(), userInfo.getP(), userInfo.getK());
            FuzzyResponseInfo fuzzyInfo = FuzzyService.jsonDealler.readValue(fuzzyResponse, FuzzyResponseInfo.class);

            float doseCalcario = calcarioService.calculaDoseCalcario(userInfo.isTipoEspecie(), userInfo.getSmp());
            float doseCalcarioTotal = calcarioService.calculaTotalCalcarioArea(doseCalcario, userInfo.getArea());

            float totalPotassioArea = fuzzyService.calculaTotalPotassioparaArea(fuzzyInfo.getValorPotassioHectare(), userInfo.getArea());
            float totalFosforoArea = fuzzyService.calculaTotalFosforoparaArea(fuzzyInfo.getValorFosforoHectare(), userInfo.getArea());

            if (
                    fuzzyInfo.getValorFosforoHectare() != 0
                    && fuzzyInfo.getValorPotassioHectare() != 0
                    && totalFosforoArea != 0
                    && totalPotassioArea != 0
                    && doseCalcario != 0
                    && doseCalcarioTotal != 0) {
                status = "success";
            }

            String response = criaJsonResponse(status, doseCalcario, doseCalcarioTotal, fuzzyInfo.getValorPotassioHectare(), totalPotassioArea, fuzzyInfo.getValorFosforoHectare(), totalFosforoArea);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RecomendacaoController.class.getName()).log(Level.SEVERE, null, ex);
            
            String response = "{\"status\": \"failed\"}";
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String criaJsonResponse(String status, float doseCalcario, float doseCalcarioTotal,
            float potassioHectare, float totalPotassioArea, float fosforoHectare, float totalFosforoArea) {

        ObjectNode jsonNode = jsonDealler.createObjectNode();

        jsonNode.put("status", status);
        jsonNode.put("dose_calcario_hec", doseCalcario);
        jsonNode.put("dose_calcario_total", doseCalcarioTotal);
        jsonNode.put("valor_potassio_hectare", potassioHectare);
        jsonNode.put("valor_potassio_total", totalPotassioArea);
        jsonNode.put("valor_fosforo_hectare", fosforoHectare);
        jsonNode.put("valor_fosforo_total", totalFosforoArea);

        return jsonNode.toString();
    }

}
