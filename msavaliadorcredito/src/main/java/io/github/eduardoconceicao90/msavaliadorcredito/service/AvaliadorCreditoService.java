package io.github.eduardoconceicao90.msavaliadorcredito.service;

import feign.FeignException;
import io.github.eduardoconceicao90.msavaliadorcredito.domain.CartaoCliente;
import io.github.eduardoconceicao90.msavaliadorcredito.domain.DadosCliente;
import io.github.eduardoconceicao90.msavaliadorcredito.domain.SitucaoCliente;
import io.github.eduardoconceicao90.msavaliadorcredito.exception.DadosClienteNotFoundException;
import io.github.eduardoconceicao90.msavaliadorcredito.exception.ErroComunicacaoMicroservicesException;
import io.github.eduardoconceicao90.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.eduardoconceicao90.msavaliadorcredito.infra.clients.ClientesResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    @Autowired
    private ClientesResourceClient clientesClient;

    @Autowired
    private CartoesResourceClient cartoesClient;

    public SitucaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {

        try {
            // obterDadosCliente - MSCLIENTES
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);

            // obterCartoesCliente - MSCARTOES
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return  SitucaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}
