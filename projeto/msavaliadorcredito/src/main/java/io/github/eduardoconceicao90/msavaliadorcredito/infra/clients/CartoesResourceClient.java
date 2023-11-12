package io.github.eduardoconceicao90.msavaliadorcredito.infra.clients;

import io.github.eduardoconceicao90.msavaliadorcredito.domain.Cartao;
import io.github.eduardoconceicao90.msavaliadorcredito.domain.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);

}
