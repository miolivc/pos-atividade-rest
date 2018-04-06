package br.edu.ifpb.entidade;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(generator = "produto_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String nome;

    @Lob
    private String descricao;

    @Column(nullable = false, precision = 2)
    private double preco;

    public Produto() {
    }

    private Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public static Produto of(String nome, String descricao) {
        return new Produto(nome, descricao);
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
