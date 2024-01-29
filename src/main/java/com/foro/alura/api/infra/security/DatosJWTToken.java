package com.foro.alura.api.infra.security;

public record DatosJWTToken(
        Long id,
        String nombre,
        String email,
        String jwtToken
) {
}
