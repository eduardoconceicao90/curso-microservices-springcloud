package io.github.eduardoconceicao90.msavaliadorcredito.service;

import io.github.eduardoconceicao90.msavaliadorcredito.domain.DadosCliente;
import io.github.eduardoconceicao90.msavaliadorcredito.domain.SitucaoCliente;
import io.github.eduardoconceicao90.msavaliadorcredito.infra.clients.ClientesResourseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    @Autowired
    private ClientesResourseClient clientesClient;

    public SitucaoCliente obterSituacaoCliente(String cpf) {

        // obterDadosCliente - MSCLIENTES
        ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);

        // obterDadosCartoesCliente - MSCARTOES

        return  SitucaoCliente
                        .builder()
                        .cliente(dadosClienteResponse.getBody())
                        .build();
    }
}
