package com.example.tuopet.service;

import com.example.tuopet.dto.*;
import com.example.tuopet.entity.AlterarSenha;
import com.example.tuopet.entity.Usuario;
import com.example.tuopet.repository.AlterarSenhaRepository;
import com.example.tuopet.repository.RoleRepository;
import com.example.tuopet.repository.UsuarioRepository;
import com.example.tuopet.utils.CodigoUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationService authenticationService;
    private final AlterarSenhaRepository alterarSenhaRepository;

    @Transactional
    public LoginResponseDTO insertUsuario(UsuarioInsertDto usuarioInsertDto) {
        var role = roleRepository.findByAutoridade("USUARIO");
        var usuario = usuarioInsertDto.toUsuario(role);
        usuario.setSenha(passwordEncoder.encode(usuarioInsertDto.getSenha()));

        usuarioRepository.save(usuario);

        return authenticationService.login(usuarioInsertDto.toAuthenticationDTO());
    }

    public LoginResponseDTO alterarSenha(AlterarSenhaDto alterarSenhaDto) {
        var usuario = buscaUsuarioPorEmail(alterarSenhaDto.getEmail());
        usuario.setSenha(passwordEncoder.encode(alterarSenhaDto.getSenha()));

        usuarioRepository.save(usuario);
        return authenticationService.login(alterarSenhaDto.toAuthenticationDTO());
    }
    public void alterarSenha(EmailDto emailDto) {
        var usuario = buscaUsuarioPorEmail(emailDto.getEmail());
        var alterarSenha = alterarSenhaRepository.findByUsuarioId(usuario.getId());
        if (alterarSenha != null)
            alterarSenhaRepository.deleteById(alterarSenha.getId());

        var codigo = CodigoUtils.gerarCodigo();

        emailService.sendMail(
                new EmailDetailsDTO(
                        usuario.getEmail(),
                        "Alterar senha - Tuo Pet",
                        "Seu código para alteração de senha é: " + codigo
                )
        );

        alterarSenhaRepository.save(AlterarSenha.from(usuario, codigo));
    }

    public Usuario buscaUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioDto getUsuario(Long idUsuario) {
        return buscaUsuario(idUsuario)
                .toUsuarioDto();
    }

    public UsuarioDto atualizaUsuario(Long usuarioId, UsuarioUpdateDto usuarioUpdateDto) {
        var usuario = buscaUsuario(usuarioId);
        usuario.atualizaCampos(usuarioUpdateDto);

        return usuarioRepository.save(usuario)
                .toUsuarioDto();
    }

    public void deleteUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    public Boolean getValidacaoCodigo(CodigoDto codigoDto) {
        var usuario = buscaUsuarioPorEmail(codigoDto.getEmail());
        var alterarSenha = alterarSenhaRepository.findByUsuarioId(usuario.getId());

        return alterarSenha.getCodigo().equals(codigoDto.getCodigo());
    }
}
