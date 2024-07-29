package com.company.sukhachevfinalstep.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDto {

    private long id;

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;

    private String status;

    @NotNull
    private Double balance;

}
