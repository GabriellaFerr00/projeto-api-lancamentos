package com.example.projeto_api_lancamentos.model;

import com.example.projeto_api_lancamentos.model.enums.TipoTransacaoType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "LANCAMENTO")
public class LancamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LCMT")
    private Long id;

    @Column(name = "TIP_TRSC")
    @Enumerated(EnumType.STRING)
    private TipoTransacaoType tipoTransacaoType;

    @Column(name = "VLR_TRSC")
    private BigDecimal valorTransacao;

    @ManyToOne
    @JoinColumn(name = "ID_CLNT", nullable = false)
    private ContaClienteEntity contaCliente;

    @Column(name = "DTA_TRSC")
    private LocalDateTime dataTransacao;
}
