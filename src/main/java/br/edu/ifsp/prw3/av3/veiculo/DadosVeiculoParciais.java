package br.edu.ifsp.prw3.av3.veiculo;

import jakarta.validation.constraints.NotNull;

public record DadosVeiculoParciais(

        @NotNull
        String marca,

        @NotNull
        String modelo
) {
        public DadosVeiculoParciais(Veiculo veiculo) {
                this(veiculo.getMarca(), veiculo.getModelo());
        }
}
