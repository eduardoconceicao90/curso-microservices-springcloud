package io.github.eduardoconceicao90.msavaliadorcredito.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovado {

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;

}
