package br.com.unisales.freela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisales.freela.model.Servico;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    List<Servico> findByCategoria(Servico.CategoriaServico categoria);

    List<Servico> findByNomeContainingIgnoreCase(String nome);
}
