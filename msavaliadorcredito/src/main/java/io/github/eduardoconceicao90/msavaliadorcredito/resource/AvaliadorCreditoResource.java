package io.github.eduardoconceicao90.msavaliadorcredito.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoResource {

    @GetMapping
    public String status(){
        return "OK";
    }
}
