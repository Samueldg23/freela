package br.com.unisales.freela.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull(message = "Cliente obrigatorio")
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    @NotNull(message = "prestador obrigatorio")
    @ManyToOne
    @JoinColumn(name = "id_prestador", nullable = false)
    private Usuario prestador;

    @NotNull(message = "servico obrigatorio")
    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;

    @NotNull(message = "A data agendada é obrigatória")
    @Future(message = "A data agendada deve ser no futuro")
    @Column(name = "dataAgendada", nullable = false)
    private LocalDateTime dataAgendada;

    @NotNull(message = "O status do pedido é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedido status;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @CreationTimestamp
    @Column(name = "dataCriacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "dataAtualizacao")
    private LocalDateTime dataAtualizacao;

    public enum StatusPedido {
        PENDENTE,
        ACEITO,
        EM_ANDAMENTO,
        CONCLUIDO,
        REJEITADO,
        CANCELADO
    }
}