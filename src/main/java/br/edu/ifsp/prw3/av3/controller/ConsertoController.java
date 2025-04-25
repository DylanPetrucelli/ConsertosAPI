package br.edu.ifsp.prw3.av3.controller;

import br.edu.ifsp.prw3.av3.conserto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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

    @GetMapping
    @RequestMapping("nomes-datas-etc")
    public Page<DadosConsertoParciais> listarParcial(
            @PageableDefault
            Pageable paginacao
    ) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosConsertoParciais::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conserto> getById(@PathVariable Long id) {
        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            return ResponseEntity.ok(consertoOptional.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosConsertoAlter dadosAlter) {
        Conserto conserto = repository.getReferenceById(dadosAlter.id());
        conserto.atualizarInfo(dadosAlter);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Conserto conserto = repository.getReferenceById(id);

        conserto.delete();
    }
}
