package com.foro.alura.api.service;


import com.foro.alura.api.domain.usuario.DatosCreateUsuario;
import com.foro.alura.api.domain.usuario.Usuario;
import com.foro.alura.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {



    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }

    public DatosCreateUsuario UserRegister(Usuario usuario){
        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        var newUser = usuarioRepository.save(usuario);
        return new DatosCreateUsuario(newUser);
    }
}
