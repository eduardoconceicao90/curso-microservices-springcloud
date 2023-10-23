package io.github.eduardoconceicao90.msavaliadorcredito.domain;

import lombok.Data;

import java.util.List;

@Data
public class SitucaoCliente {

    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;

}
