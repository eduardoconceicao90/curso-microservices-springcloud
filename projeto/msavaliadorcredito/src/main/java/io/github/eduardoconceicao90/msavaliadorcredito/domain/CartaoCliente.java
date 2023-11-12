package io.github.eduardoconceicao90.msavaliadorcredito.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoCliente {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

}
