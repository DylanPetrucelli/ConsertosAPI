package br.edu.ifsp.prw3.av3.controller;

import br.edu.ifsp.prw3.av3.conserto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@RestController
@RequestMapping("consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarConserto(@RequestBody @Valid DadosConserto dados, UriComponentsBuilder uriBuilder) {
        var conserto = new Conserto(dados);
        repository.save(conserto);
        var uri = uriBuilder.path("/consertos/{id}").buildAndExpand(conserto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosConsertoReturn(conserto));
    }

    @GetMapping
    public ResponseEntity listar(Pageable paginacao) {
        return ResponseEntity.ok(repository.findAll(paginacao));
    }

    @GetMapping
    @RequestMapping("nomes-datas-etc")
    public ResponseEntity listarParcial(Pageable paginacao) {

        var pagina = repository.findAllByAtivoTrue(paginacao).map(DadosConsertoParciais::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            return ResponseEntity.ok(new DadosConsertoReturn(consertoOptional.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosConsertoAlter dadosAlter) {
        Conserto conserto = repository.getReferenceById(dadosAlter.id());
        conserto.atualizarInfo(dadosAlter);
        return ResponseEntity.ok(new DadosConsertoReturn(conserto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Conserto conserto = repository.getReferenceById(id);

        conserto.delete();

        return ResponseEntity.noContent().build();
    }
}
