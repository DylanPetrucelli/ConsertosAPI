package br.edu.ifsp.prw3.av3.veiculo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    private String marca;
    private String modelo;
    private int ano;
    private String cor;

    public Veiculo(DadosVeiculo dados) {
        if (dados.marca() != null) {
            this.marca = dados.marca();
        }
        if (dados.modelo() != null) {
            this.modelo = dados.modelo();
        }
        this.ano = dados.ano();
        if (dados.cor() != null) {
            this.cor = dados.cor();
        }
    }
}
