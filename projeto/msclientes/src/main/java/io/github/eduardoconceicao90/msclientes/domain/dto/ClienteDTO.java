package io.github.eduardoconceicao90.msclientes.domain.dto;

import io.github.eduardoconceicao90.msclientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {

    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
