package br.edu.ifpb.servico;

import br.edu.ifpb.entidade.Produto;
import br.edu.ifpb.repositorio.Produtos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ServicoProduto {

    @Inject
    private Produtos produtos;

    public Produto adicionar(Produto produto) {
        return produtos.adicionar(produto);
    }

    public void remover(Produto produto) {
        produtos.remover(produto);
    }

    public void editar(long id, Produto produto) {
        produtos.editar(id, produto);
    }

    public Produto recuperar(long id) {
        return produtos.recuperar(id);
    }

    public List<Produto> todos() {
        return produtos.recuperar();
    }
}
