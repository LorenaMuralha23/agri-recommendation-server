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
 * Controlador responsável por gerenciar as requisições relacionadas a 
 * recomendações agrícolas de calagem e adubação.
 * Ele utiliza serviços fuzzy e cálculos para determinar as doses de calcário 
 * e fertilizantes (fósforo e potássio) com base nos dados do usuário.
 * 
 * @author Maria Lorena Muralha Lima
 */
@RestController
@RequestMapping("/agri-server/recommendation")
public class RecomendacaoController {

    @Autowired
    private FuzzyService fuzzyService;

    @Autowired
    private CalcarioService calcarioService;

    
     /**
     * Processa a requisição do cliente com os dados fornecidos para calcular
     * as recomendações de calagem e adubação. 
     * 
     * @param userInfo Objeto contendo os dados fornecidos pelo usuário, como CTC, pH,
     *                 argila, fósforo, potássio, e outros relacionados à área e espécie.
     * @return Uma resposta HTTP contendo o resultado em formato JSON com informações
     *         como doses recomendadas de calcário, fósforo, potássio e totais para a área.
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/calculate")
    public ResponseEntity<String> getUserRequest(@RequestBody UserInfo userInfo) {
        try {
            String fuzzyResponse = fuzzyService.calculaFuzzy(userInfo.getCtcPH7(), userInfo.getArgila(), userInfo.getP(), userInfo.getK());
            FuzzyResponseInfo fuzzyInfo = FuzzyService.jsonDealler.readValue(fuzzyResponse, FuzzyResponseInfo.class);

            float doseCalcario = calcarioService.calculaDoseCalcario(userInfo.isTipoEspecie(), userInfo.getSmp());
            float doseCalcarioTotal = calcarioService.calculaTotalCalcarioArea(doseCalcario, userInfo.getArea());

            float totalPotassioArea = fuzzyService.calculaTotalPotassioparaArea(fuzzyInfo.getValorPotassioHectare(), userInfo.getArea());
            float totalFosforoArea = fuzzyService.calculaTotalFosforoparaArea(fuzzyInfo.getValorFosforoHectare(), userInfo.getArea());

            String response = criaJsonResponse("success", doseCalcario, doseCalcarioTotal, fuzzyInfo.getValorPotassioHectare(), totalPotassioArea, fuzzyInfo.getValorFosforoHectare(), totalFosforoArea);
            System.out.println("[SERVER SAYS] Operação realizada com sucesso! [SERVER SAYS]");
            System.out.println("Resposta a ser enviada: " + response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RecomendacaoController.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("[SERVER SAYS] Algo deu errado! [SERVER SAYS]");
            System.out.println(userInfo.toString());
            String response = "{\"status\": \"failed\"}";
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    /**
     * Cria uma resposta em formato JSON com as informações calculadas.
     * 
     * @param status Status do processamento, como "success" ou "failed".
     * @param doseCalcario Dose de calcário recomendada por hectare.
     * @param doseCalcarioTotal Total de calcário para toda a área.
     * @param potassioHectare Valor recomendado de potássio por hectare.
     * @param totalPotassioArea Total de potássio para toda a área.
     * @param fosforoHectare Valor recomendado de fósforo por hectare.
     * @param totalFosforoArea Total de fósforo para toda a área.
     * @return Uma string em formato JSON contendo os dados informados.
     */
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
