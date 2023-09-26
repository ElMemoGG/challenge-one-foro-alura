package com.foro.alura.api.controller;


import com.foro.alura.api.domain.usuario.DatosAutenticacionUsuario;
import com.foro.alura.api.domain.usuario.DatosCreateUsuario;
import com.foro.alura.api.domain.usuario.Usuario;
import com.foro.alura.api.infra.security.DatosJWTToken;
import com.foro.alura.api.service.AutenticacionService;
import com.foro.alura.api.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Usuario", description = "")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticacionService autenticacionService;

    @PostMapping
    public ResponseEntity loginUser(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(), datosAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @PostMapping("/registro")
    public ResponseEntity registrarUser(@RequestBody @Valid DatosCreateUsuario datosCreateUsuario){
        var usuario = new Usuario(datosCreateUsuario.name(), datosCreateUsuario.email(), datosCreateUsuario.password());
        return ResponseEntity.ok(autenticacionService.UserRegister(usuario));
    }

}
