package io.github.eduardoconceicao90.mscartoes.resource;

import com.netflix.discovery.converters.Auto;
import io.github.eduardoconceicao90.mscartoes.domain.Cartao;
import io.github.eduardoconceicao90.mscartoes.domain.ClienteCartao;
import io.github.eduardoconceicao90.mscartoes.domain.dto.CartaoDTO;
import io.github.eduardoconceicao90.mscartoes.domain.dto.CartoesPorClienteResponseDTO;
import io.github.eduardoconceicao90.mscartoes.service.CartaoService;
import io.github.eduardoconceicao90.mscartoes.service.ClienteCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartaoResource {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CartaoDTO cartao){
        Cartao newObj = cartao.toModel();
        cartaoService.save(newObj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda){
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponseDTO>> getCartoesByCliente(
            @RequestParam("cpf") String cpf){
        List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponseDTO> resultList = lista.stream()
                .map(CartoesPorClienteResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
