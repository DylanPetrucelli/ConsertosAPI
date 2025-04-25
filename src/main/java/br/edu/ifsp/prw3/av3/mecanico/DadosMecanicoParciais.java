package br.edu.ifsp.prw3.av3.mecanico;

import jakarta.validation.constraints.NotNull;

public record DadosMecanicoParciais(

        @NotNull
        String nome
) {
        public DadosMecanicoParciais(Mecanico mecanico) {
                this(mecanico.getNome());
        }
}
