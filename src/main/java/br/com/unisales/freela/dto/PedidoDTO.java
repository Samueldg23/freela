package br.com.unisales.freela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.unisales.freela.model.Pedido;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Long idCliente;
    private Long idPrestador;
    private Long idServico;
    private LocalDateTime dataAgendada;
    private String status;
    private String descricao;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.idCliente = pedido.getCliente().getId();
        this.idPrestador = pedido.getPrestador().getId();
        this.idServico = pedido.getServico().getId();
        this.dataAgendada = pedido.getDataAgendada();
        this.status = pedido.getStatus().toString();
        this.descricao = pedido.getDescricao();
    }
}