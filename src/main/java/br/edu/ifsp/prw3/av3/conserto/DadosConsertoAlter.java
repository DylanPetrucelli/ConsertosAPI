package br.edu.ifsp.prw3.av3.conserto;

import br.edu.ifsp.prw3.av3.mecanico.Mecanico;
import jakarta.validation.constraints.NotNull;

public record DadosConsertoAlter(
        @NotNull
        Long id,

        String dataSaida,

        Mecanico mecanico
) {
    public DadosConsertoAlter (Conserto conserto) {
        this(conserto.getId(), conserto.getDataSaida(), conserto.getMecanico());
    }
}
