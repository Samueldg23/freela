package br.com.unisales.freela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.freela.dto.AvaliacaoCadastroDTO;
import br.com.unisales.freela.dto.AvaliacaoDTO;
import br.com.unisales.freela.model.Avaliacao;
import br.com.unisales.freela.model.Pedido;
import br.com.unisales.freela.repository.AvaliacaoRepository;
import br.com.unisales.freela.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public AvaliacaoDTO cadastrarAvaliacao(AvaliacaoCadastroDTO avaliacaoCadastroDTO) {
        Avaliacao avaliacao = new Avaliacao();

        Pedido pedido = pedidoRepository.findById(avaliacaoCadastroDTO.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        avaliacao.setPedido(pedido);
        avaliacao.setNota(avaliacaoCadastroDTO.getNota());
        avaliacao.setComentario(avaliacaoCadastroDTO.getComentario());

        avaliacao = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoDTO(avaliacao); 
    }

    public AvaliacaoDTO buscarAvaliacaoPorId(Long id) {
        Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(id);
        if (avaliacaoOptional.isPresent()) {
            return new AvaliacaoDTO(avaliacaoOptional.get()); 
        } else {
            throw new RuntimeException("Avaliação não encontrada");
        }
    }

    public List<AvaliacaoDTO> listarAvaliacoesPorPedido(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return avaliacaoRepository.findByPedido(pedido).stream()
                .map(AvaliacaoDTO::new) 
                .collect(Collectors.toList());
    }

    public List<AvaliacaoDTO> listarAvaliacoesPorPrestador(Long idPrestador) {
        return avaliacaoRepository.findByPedidoPrestadorId(idPrestador).stream()
                .map(AvaliacaoDTO::new) 
                .collect(Collectors.toList());
    }
}