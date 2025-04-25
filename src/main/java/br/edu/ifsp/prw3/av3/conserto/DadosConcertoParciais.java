package br.edu.ifsp.prw3.av3.conserto;

import br.edu.ifsp.prw3.av3.mecanico.DadosMecanicoParciais;
import br.edu.ifsp.prw3.av3.veiculo.DadosVeiculoParciais;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosConcertoParciais(

    @NotNull
    Long id,

    @Pattern(regexp = "\\d{2}[-/]\\d{2}[-/]\\d{4}")
    String dataEntrada,

    @Pattern(regexp = "\\d{2}[-/]\\d{2}[-/]\\d{4}")
    String dataSaida,

    DadosMecanicoParciais mecanico,
    DadosVeiculoParciais veiculo
) {
        public DadosConcertoParciais(Conserto conserto) {
            this(conserto.getId(),
                 conserto.getDataEntrada(),
                 conserto.getDataSaida(),
                 new DadosMecanicoParciais(conserto.getMecanico()),
                 new DadosVeiculoParciais(conserto.getVeiculo()));
        }
}
