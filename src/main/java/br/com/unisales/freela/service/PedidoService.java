package br.com.unisales.freela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.freela.dto.PedidoCadastroDTO;
import br.com.unisales.freela.dto.PedidoDTO;
import br.com.unisales.freela.model.Pedido;
import br.com.unisales.freela.model.Servico;
import br.com.unisales.freela.model.Usuario;
import br.com.unisales.freela.repository.PedidoRepository;
import br.com.unisales.freela.repository.ServicoRepository;
import br.com.unisales.freela.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public PedidoDTO cadastrarPedido(PedidoCadastroDTO pedidoCadastroDTO) {
        Pedido pedido = new Pedido();

        Usuario cliente = usuarioRepository.findById(pedidoCadastroDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Usuario prestador = usuarioRepository.findById(pedidoCadastroDTO.getIdPrestador())
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));
        Servico servico = servicoRepository.findById(pedidoCadastroDTO.getIdServico())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        pedido.setCliente(cliente);
        pedido.setPrestador(prestador);
        pedido.setServico(servico);
        pedido.setDataAgendada(pedidoCadastroDTO.getDataAgendada());
        pedido.setDescricao(pedidoCadastroDTO.getDescricao());
        pedido.setStatus(Pedido.StatusPedido.PENDENTE);

        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido); 
    }

    public PedidoDTO buscarPedidoPorId(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            return new PedidoDTO(pedidoOptional.get()); 
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public PedidoDTO atualizarPedido(Long id, PedidoCadastroDTO pedidoCadastroDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();

            pedido.setDataAgendada(pedidoCadastroDTO.getDataAgendada());
            pedido.setDescricao(pedidoCadastroDTO.getDescricao());

            pedido = pedidoRepository.save(pedido);
            return new PedidoDTO(pedido); 
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public void deletarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoDTO::new) 
                .collect(Collectors.toList());
    }
}
