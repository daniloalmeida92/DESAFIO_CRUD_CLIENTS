package com.devsuperior.desafiocruddeclientes.dto;

import com.devsuperior.desafiocruddeclientes.entities.Client;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @Size(min = 3, max = 50, message = "O Nome Precisa ser preenchido")
    @NotBlank(message = "Campo nome não pode vazio")
    private String name;

    @Size(min = 11, max = 11, message = "CPF precisa ter 11 caracteres")
    @NotBlank(message = "CPF não pode ficar em branco")
    private String cpf;

    @PositiveOrZero(message = "O valor da renda não pode ser negativo")
    private Double income;

    @PastOrPresent(message = "Não pode datas futuras")
    private LocalDate birthDate;

    @PositiveOrZero(message = "Não pode colocar valores negativos")
    private Integer children;

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
