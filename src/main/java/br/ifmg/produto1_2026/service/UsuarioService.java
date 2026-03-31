package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository produtoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageRequest) {
        Page<Usuario> produtos = produtoRepository.findAll(pageRequest);
        return produtos.map(UsuarioDTO::new);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Optional<Usuario> opt = produtoRepository.findById(id);
        Usuario produto = opt.orElseThrow(() -> new RegistroNaoEncontrado("Usuario não encontrado"));
        return new UsuarioDTO(produto);
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();

        CopytoEntity(dto, entity);

        entity = produtoRepository.save(entity);
        return new UsuarioDTO(entity);
    }

    private void CopytoEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setTelefone(dto.getTelefone());

        entity.getPerfis().clear();
        for (PerfilDTO udto : dto.getPerfis()){
            Perfil uDTO = perfilRepository.getReferenceById(udto.getId());
            entity.getPerfis().add(uDTO);
        }
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        if (!produtoRepository.existsById(id)) {
            throw new RegistroNaoEncontrado("Usuario não encontrado");
        }
        Usuario entity = produtoRepository.getReferenceById(id);

        CopytoEntity(dto, entity);

        entity = produtoRepository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegistroNaoEncontrado("Usuario não encontrado");
        }
        try {
            produtoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }
}