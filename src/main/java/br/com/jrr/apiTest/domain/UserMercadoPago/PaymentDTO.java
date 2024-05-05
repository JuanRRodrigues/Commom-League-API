package br.com.jrr.apiTest.domain.UserMercadoPago;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.OffsetDateTime;

public record PaymentDTO(

        @JsonAlias("init_point")
        String sandbox_init_point





) {}
