package io.github.eduardoconceicao90.msclientes.service;

import io.github.eduardoconceicao90.msclientes.domain.Cliente;
import io.github.eduardoconceicao90.msclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente){
        return repository.save(cliente);
    }

    public Optional<Cliente> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}
