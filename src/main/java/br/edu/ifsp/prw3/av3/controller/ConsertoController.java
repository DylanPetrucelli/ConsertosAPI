package br.edu.ifsp.prw3.av3.controller;

import br.edu.ifsp.prw3.av3.conserto.Conserto;
import br.edu.ifsp.prw3.av3.conserto.ConsertoRepository;
import br.edu.ifsp.prw3.av3.conserto.DadosConserto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarConserto(@RequestBody DadosConserto dados) {
        repository.save(new Conserto(dados));
    }
}
