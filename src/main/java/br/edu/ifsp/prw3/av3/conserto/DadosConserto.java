package br.edu.ifsp.prw3.av3.conserto;

import br.edu.ifsp.prw3.av3.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.av3.veiculo.DadosVeiculo;

public record DadosConserto(String dataEntrada, String dataSaida, DadosMecanico mecanico, DadosVeiculo veiculo) {
}
