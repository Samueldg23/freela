package br.com.unisales.freela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisales.freela.model.Avaliacao;
import br.com.unisales.freela.model.Pedido;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByPedido(Pedido pedido);

    List<Avaliacao> findByNota(Integer nota);

    List<Avaliacao> findByPedidoPrestadorId(Long idPrestador);
}
