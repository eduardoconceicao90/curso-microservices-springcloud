package io.github.eduardoconceicao90.mscartoes.repository;

import io.github.eduardoconceicao90.mscartoes.domain.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
