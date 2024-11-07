package br.com.jrr.apiTest.domain.user.DTO;


public record UserGeneralEditDTO(
        String userName,
        String fullName,
        String country,
        String state,
        String city
)
{

}