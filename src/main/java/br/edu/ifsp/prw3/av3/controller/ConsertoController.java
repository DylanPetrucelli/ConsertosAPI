package br.edu.ifsp.prw3.av3.controller;

import br.edu.ifsp.prw3.av3.conserto.Conserto;
import br.edu.ifsp.prw3.av3.conserto.ConsertoRepository;
import br.edu.ifsp.prw3.av3.conserto.DadosConcertoParciais;
import br.edu.ifsp.prw3.av3.conserto.DadosConserto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarConserto(@RequestBody @Valid DadosConserto dados) {
        repository.save(new Conserto(dados));
    }

    @GetMapping
    public Page<Conserto> listar(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @GetMapping("nomes-datas-etc")
    public List<DadosConcertoParciais> listarParcial() {
        return repository.findAll().stream().map(DadosConcertoParciais::new).toList();
    }
}
