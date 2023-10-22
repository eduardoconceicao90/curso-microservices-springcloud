package io.github.eduardoconceicao90.msclientes.resource;

import io.github.eduardoconceicao90.msclientes.domain.Cliente;
import io.github.eduardoconceicao90.msclientes.domain.dto.ClienteDTO;
import io.github.eduardoconceicao90.msclientes.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
@Slf4j
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de clientes");
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteDTO cliente){
        Cliente newObj = cliente.toModel();
        service.save(newObj);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                                                        .query("cpf={cpf}").buildAndExpand(newObj.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        Optional<Cliente> cliente = service.findByCpf(cpf);
        if (cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
