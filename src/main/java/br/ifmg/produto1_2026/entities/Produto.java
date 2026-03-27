package br.ifmg.produto1_2026.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private Double preco;
    private String imgURl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataCriacao = Instant.now();
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataAtualizacao = Instant.now();

    @ManyToMany
    @JoinTable(
            name="tb_produto_categoria", // nome da tabela
            joinColumns = @JoinColumn(name="id_produto"),
            inverseJoinColumns = @JoinColumn(name="id_categoria")
    )
    private Set<Categoria> categorias =  new HashSet<Categoria>();

    
    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, Double preco, String imgURl, Instant dataCriacao, Instant dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgURl = imgURl;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    @PrePersist
    public void prePersist() {
        this.dataCriacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = Instant.now();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", imgURl='" + imgURl + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
