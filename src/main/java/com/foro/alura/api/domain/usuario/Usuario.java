package com.foro.alura.api.domain.usuario;

import com.foro.alura.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOPICO_ID", referencedColumnName = "ID")
    private List<Topico> topicos;*/

  /*  public Usuario(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
