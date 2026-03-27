package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.entities.Perfil;

import java.util.Objects;

public class PerfilDTO {
    private long id;
    private String nome;
    private String descricao;

    public PerfilDTO() {
    }

    public PerfilDTO(long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    public PerfilDTO(Perfil perfil) {
        this.id = perfil.getId();
        this.nome = perfil.getNome();
        this.descricao = perfil.getDescricao();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PerfilDTO perfilDTO = (PerfilDTO) o;
        return id == perfilDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PerfilDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
