package br.edu.ifsp.prw3.av3.veiculo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosVeiculo(
        @NotNull
        String marca,

        @NotNull
        String modelo,

        @NotNull
        @Pattern(regexp = "\\d{4}")
        int ano,

        String cor
) {
        public DadosVeiculo(Veiculo veiculo) {
                this (veiculo.getMarca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor());
        }
}
