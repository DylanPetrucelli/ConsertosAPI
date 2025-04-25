package br.edu.ifsp.prw3.av3.conserto;

import br.edu.ifsp.prw3.av3.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.av3.veiculo.DadosVeiculo;
import jakarta.validation.constraints.Pattern;

public record DadosConsertoReturn(

        @Pattern(regexp = "\\d{2}[-/]\\d{2}[-/]\\d{4}")
        String dataEntrada,

        @Pattern(regexp = "\\d{2}[-/]\\d{2}[-/]\\d{4}")
        String dataSaida,

        DadosMecanico mecanico,
        DadosVeiculo veiculo) {
    public DadosConsertoReturn (Conserto conserto) {
        this (conserto.getDataEntrada(),
                conserto.getDataSaida(),
                new DadosMecanico(conserto.getMecanico()),
                new DadosVeiculo(conserto.getVeiculo())
                );
    }
}