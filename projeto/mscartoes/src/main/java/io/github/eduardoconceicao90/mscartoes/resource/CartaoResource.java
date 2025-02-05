package io.github.eduardoconceicao90.mscartoes.resource;

import io.github.eduardoconceicao90.mscartoes.domain.Cartao;
import io.github.eduardoconceicao90.mscartoes.domain.ClienteCartao;
import io.github.eduardoconceicao90.mscartoes.domain.dto.CartaoSaveRequest;
import io.github.eduardoconceicao90.mscartoes.domain.dto.CartoesPorClienteResponse;
import io.github.eduardoconceicao90.mscartoes.service.CartaoService;
import io.github.eduardoconceicao90.mscartoes.service.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartaoResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CartaoSaveRequest request){
        var cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda){
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<ClienteCartao> list = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = list.stream()
                                                            .map(CartoesPorClienteResponse::fromModel)
                                                            .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
