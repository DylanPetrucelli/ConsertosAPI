package br.edu.ifsp.prw3.av3.mecanico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mecanico {

    private String nome;
    private int anos;

    public Mecanico(DadosMecanico dados) {
        this.nome = dados.nome();
        this.anos = dados.anos();
    }
}
