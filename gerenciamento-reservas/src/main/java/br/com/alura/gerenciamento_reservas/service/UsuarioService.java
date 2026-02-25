package br.com.alura.gerenciamento_reservas.service;

import br.com.alura.gerenciamento_reservas.dto.AtualizarUsuarioDto;
import br.com.alura.gerenciamento_reservas.dto.CadastrarUsuarioDto;
import br.com.alura.gerenciamento_reservas.dto.UsuarioDto;
import br.com.alura.gerenciamento_reservas.exception.ValidacaoException;
import br.com.alura.gerenciamento_reservas.model.Usuario;
import br.com.alura.gerenciamento_reservas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto cadastrarUsuario(CadastrarUsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        if(usuarioRepository.existsByEmail(dto.email())) {
            throw new ValidacaoException("Email já cadastrado");
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioDto(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail()
        );
    }

    public List<UsuarioDto> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(
                usuario -> new UsuarioDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail()
                )).toList();
    }

    public UsuarioDto atualizarUsuario(Long id, AtualizarUsuarioDto dto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new ValidacaoException("Usuário não encontrado"));
        if (usuarioRepository.existsByEmail(dto.email())
                && !usuario.getEmail().equals(dto.email())) {
            throw new ValidacaoException("Email já cadastrado");
        }

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioDto(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail()
        );


    }
    public void deletarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new ValidacaoException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }
}
