package br.edu.ifsp.prw3.av3.mecanico;

import jakarta.validation.constraints.NotNull;

public record DadosMecanico(
        @NotNull
        String nome,
        int anos) {

        public DadosMecanico(Mecanico mecanico) {
                this (mecanico.getNome(), mecanico.getAnos());
        }
}
