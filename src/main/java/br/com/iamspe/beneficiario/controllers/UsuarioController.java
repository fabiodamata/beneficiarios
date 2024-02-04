package br.com.iamspe.beneficiario.controllers;

import br.com.iamspe.beneficiario.dtos.UsuarioDto;
import br.com.iamspe.beneficiario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {
        return  usuarioService.salvar(usuarioDto) ;
    }

    /* APENAS PARA TESTAR AS ROLES DOS USUARIOS */
    @GetMapping("/admin")
    private String getAdmin() {
        return "Usuário possui permissão de ADMIN";
    }

    @GetMapping("/user")
    private String getUser() {
        return "Usuário possui permissão simples (USER)";
    }
}