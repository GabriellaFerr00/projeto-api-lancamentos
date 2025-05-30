package com.example.projeto_api_lancamentos.model;

import com.example.projeto_api_lancamentos.handler.Messages;
import com.example.projeto_api_lancamentos.handler.exception.RegrasDadosDeEntradaException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTA")
public class ContaClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLNT")
    private Long id;

    @Column(name = "SLD_CNTA")
    private BigDecimal saldo;

    public void transacaoDeposito(BigDecimal valorDeposito) {
        if (valorDeposito.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valorDeposito);
        } else {
            throw new RegrasDadosDeEntradaException(Messages.VALOR_DEPOSITO_INCORRETO);
        }
    }

    public Boolean transacaoRetirada(BigDecimal valorRetirada) {
        if (valorRetirada.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (this.saldo.compareTo(valorRetirada) >= 0) {
            this.saldo = this.saldo.subtract(valorRetirada);
            return true;
        } else {
            return false;
        }
    }
}