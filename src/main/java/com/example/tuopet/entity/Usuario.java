package com.example.tuopet.entity;

import com.example.tuopet.dto.UsuarioDto;
import com.example.tuopet.dto.UsuarioUpdateDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String cpf;

    private String email;

    private String celular;

    private String genero;

    private LocalDate dataNascimento;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UsuarioDto toUsuarioDto() {
        return UsuarioDto.builder()
                .id(id)
                .email(email)
                .nomeCompleto(nomeCompleto)
                .role(role.getAutoridade())
                .celular(celular)
                .cpf(cpf)
                .dataNascimento(dataNascimento)
                .genero(getGenero())
                .build();
    }

    public void atualizaCampos(UsuarioUpdateDto usuarioUpdateDto) {
        setCelular(usuarioUpdateDto.getCelular());
        setCpf(usuarioUpdateDto.getCpf());
        setGenero(usuarioUpdateDto.getGenero());
        setDataNascimento(usuarioUpdateDto.getDataNascimento());
        setNomeCompleto(usuarioUpdateDto.getNomeCompleto());
        setEmail(usuarioUpdateDto.getEmail());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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

}
