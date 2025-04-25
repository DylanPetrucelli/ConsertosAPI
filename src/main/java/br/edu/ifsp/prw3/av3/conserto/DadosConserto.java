package br.edu.ifsp.prw3.av3.conserto;

import br.edu.ifsp.prw3.av3.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.av3.veiculo.DadosVeiculo;
import jakarta.validation.constraints.Pattern;

public record DadosConserto(

        @Pattern(regexp = "\\d{2}[-/]\\d{2}[-/]\\d{4}")
        String dataEntrada,

        @Pattern(regexp = "\\d{2}[-/]\\d{2}[-/]\\d{4}")
        String dataSaida,

        DadosMecanico mecanico,
        DadosVeiculo veiculo) {
}
