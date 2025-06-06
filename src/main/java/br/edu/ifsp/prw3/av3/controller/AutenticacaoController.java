package br.edu.ifsp.prw3.av3.controller;

import br.edu.ifsp.prw3.av3.usuario.Usuario;
import br.edu.ifsp.prw3.av3.usuario.dadosAutenticacao;
import br.edu.ifsp.prw3.av3.util.security.DadosTokenJWT;
import br.edu.ifsp.prw3.av3.util.security.PW3TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PW3TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid dadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken( (Usuario) authentication.getPrincipal() );

        return ResponseEntity.ok( new DadosTokenJWT(tokenJWT) );
    }
}
