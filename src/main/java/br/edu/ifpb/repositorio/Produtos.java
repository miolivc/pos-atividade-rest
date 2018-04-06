package br.edu.ifpb.repositorio;

import br.edu.ifpb.entidade.Produto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class Produtos {

    @PersistenceContext
    private EntityManager manager;

    public Produto adicionar(Produto produto) {
        manager.persist(produto);
        return produto;
    }

    public void remover(Produto produto) {
        manager.remove(produto);
    }

    public void editar(long id, Produto produto) {
        Produto editado = manager.find(Produto.class, id);
        editado.setNome(produto.getNome());
        editado.setDescricao(produto.getDescricao());
        editado.setPreco(produto.getPreco());
        manager.merge(editado);
    }

    public Produto recuperar(long id) {
        return manager.find(Produto.class, id);
    }

    public List<Produto> recuperar() {
        return manager.createQuery("SELECT p FROM Produto p", Produto.class)
                .getResultList();
    }
}
