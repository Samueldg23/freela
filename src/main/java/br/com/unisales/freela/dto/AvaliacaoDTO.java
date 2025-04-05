package br.com.unisales.freela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.unisales.freela.model.Avaliacao;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private Long idPedido;
    private Integer nota;
    private String comentario;
    private LocalDateTime dataCriacao;

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.idPedido = avaliacao.getPedido().getId();
        this.nota = avaliacao.getNota();
        this.comentario = avaliacao.getComentario();
        this.dataCriacao = avaliacao.getDataCriacao();
    }
}