package br.edu.ifpb.entidade;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@SequenceGenerator(name = "venda_seq", sequenceName = "venda_seq")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
    private long id;
    @OneToOne
    private Cliente cliente;
    @OneToMany
    private List<Produto> produtos;
    private LocalDateTime criadoEm;

    {
        this.produtos = new ArrayList<>();
        this.criadoEm = LocalDateTime.now();
    }

    public Venda() {
    }

    public Venda(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);
    }

    @XmlElement
    @Column(precision = 2, columnDefinition = "DEFAULT 0", nullable = false)
    public double getValor() {
        double valor = 0;
        for (Produto produto: produtos) {
            valor += produto.getPreco();
        }
        return valor;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
