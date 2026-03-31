package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.repositories.ProdutoRepository;
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
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAll(Pageable pageRequest) {
        Page<Produto> produtos = produtoRepository.findAll(pageRequest);
        return produtos.map(ProdutoDTO::new);
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        Optional<Produto> opt = produtoRepository.findById(id);
        Produto produto = opt.orElseThrow(() -> new RegistroNaoEncontrado("Produto não encontrado"));
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();

        CopytoEntity(dto, entity);

        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        if (!produtoRepository.existsById(id)) {
            throw new RegistroNaoEncontrado("Produto não encontrado");
        }
        Produto entity = produtoRepository.getReferenceById(id);

        CopytoEntity(dto, entity);

        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    private void CopytoEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgURl(dto.getImgURl());

        entity.getCategorias().clear();
        for (CategoriaDTO catDto : dto.getCategorias()){
            Categoria cat = categoriaRepository.getReferenceById(catDto.getId());
            entity.getCategorias().add(cat);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegistroNaoEncontrado("Produto não encontrado");
        }
        try {
            produtoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }
}