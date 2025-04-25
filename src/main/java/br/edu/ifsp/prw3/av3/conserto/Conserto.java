package br.edu.ifsp.prw3.av3.conserto;

import br.edu.ifsp.prw3.av3.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.av3.mecanico.Mecanico;
import br.edu.ifsp.prw3.av3.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="consertos")
@Entity(name="Conserto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conserto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_entrada")
    private String dataEntrada;

    @Column(name = "data_saida")
    private String dataSaida;

    //isso ficou MUITO feio, mas funcionou. tive problemas com nomes no db.
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "nome", column = @Column(name = "mecanico_nome")),
            @AttributeOverride(name = "anos", column = @Column(name = "mecanico_anos"))
    })
    private Mecanico mecanico;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "marca", column = @Column(name = "veiculo_marca")),
            @AttributeOverride(name = "modelo", column = @Column(name = "veiculo_modelo")),
            @AttributeOverride(name = "ano", column = @Column(name = "veiculo_ano")),
            @AttributeOverride(name = "cor", column = @Column(name = "veiculo_cor"))
    })
    private Veiculo veiculo;

    private Boolean ativo;

    public Conserto(DadosConserto dados) {
        this.dataEntrada = dados.dataEntrada();
        this.dataSaida = dados.dataSaida();
        this.mecanico = new Mecanico(dados.mecanico());
        this.veiculo = new Veiculo(dados.veiculo());
        this.ativo = true;
    }

    public void atualizarInfo(DadosConsertoAlter dados) {
        if (dados.dataSaida() != null) {
            this.dataSaida = dados.dataSaida();
        }
        if (dados.mecanico() != null && dados.mecanico().getNome() != null) {
            this.mecanico.atualizarInformacoes(new DadosMecanico(dados.mecanico().getNome(), dados.mecanico().getAnos()));
        }
    }

    public void delete() {
        this.ativo = false;
    }
}
