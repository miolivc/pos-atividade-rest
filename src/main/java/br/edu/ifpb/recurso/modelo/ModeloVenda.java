package br.edu.ifpb.recurso.modelo;

import br.edu.ifpb.entidade.Cliente;
import br.edu.ifpb.entidade.Produto;
import br.edu.ifpb.entidade.Venda;
import br.edu.ifpb.recurso.ClienteRecurso;
import br.edu.ifpb.recurso.ProdutoRecurso;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@XmlRootElement
public class ModeloVenda implements Serializable {

    private UriInfo info;
    private long id;
    private LocalDateTime criadoEm;
    private Link cliente = new Link();
    private List<Link> produtos = new ArrayList<>();
    private double valor;

    public ModeloVenda(UriInfo info, Venda venda) {
        this.info = info;
        this.id = venda.getId();
        this.criadoEm = venda.getCriadoEm();
        this.valor = venda.getValor();
        if (venda.getCliente() != null) {
            this.cliente = linkCliente(info, venda.getCliente());
        }
        if (venda.getProdutos() != null && ! venda.getProdutos().isEmpty()) {
            this.produtos = linkProdutos(info, venda.getProdutos());
        }
    }

    private Link linkCliente(UriInfo info, Cliente cliente) {
        URI localCliente = info.getBaseUriBuilder()
                .path(ClienteRecurso.class)
                .path(cliente.getCpf())
                .build();

        return new Link(cliente.getNome(), localCliente.toString());
    }

    private List<Link> linkProdutos(UriInfo info, List<Produto> produtos) {
        List<Link> links = new ArrayList<>();

        produtos.forEach(produto -> {
            URI localProduto = info.getBaseUriBuilder()
                    .path(ProdutoRecurso.class)
                    .path(String.valueOf(produto.getId()))
                    .build();
            links.add(new Link(produto.getNome(), localProduto.toString()));
        });

        return links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Link getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = linkCliente(info, cliente);
    }

    public List<Link> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = linkProdutos(info, produtos);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
