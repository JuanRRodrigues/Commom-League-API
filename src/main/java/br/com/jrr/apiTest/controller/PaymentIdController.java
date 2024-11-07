package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.domain.API.DataAccountPaymentAPI;
import br.com.jrr.apiTest.service.MercadoPago.MercadoPagoWebService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentIdController {

@Autowired
private MercadoPagoWebService service;

    @Autowired
    private MatchLolRiotRepository Repository;
    @PostMapping("/post")
    public String postByAPI(@RequestBody @Valid DataAccountPaymentAPI data) throws Exception {
        return service.Payment(data);
    }


}
