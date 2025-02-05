package io.github.eduardoconceicao90.msclientes.resource;

import io.github.eduardoconceicao90.msclientes.domain.Cliente;
import io.github.eduardoconceicao90.msclientes.domain.dto.ClienteSaveRequest;
import io.github.eduardoconceicao90.msclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteResource {

    private final ClienteService service;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de clientes");
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
        var cliente = request.toModel();
        service.save(cliente);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = service.findByCpf(cpf);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

}
