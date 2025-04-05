package br.com.unisales.freela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unisales.freela.dto.UsuarioCadastroDTO;
import br.com.unisales.freela.dto.UsuarioDTO;
import br.com.unisales.freela.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Usuários", description = "Cadastro e gestão de usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário (cliente ou prestador)")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
        UsuarioDTO usuarioDTO = service.cadastrarUsuario(usuarioCadastroDTO);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = service.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
        UsuarioDTO usuarioDTO = service.atualizarUsuario(id, usuarioCadastroDTO);
        return ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = service.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}