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

    public Produto recuperarPeloNome(String nome) {
        return manager.createQuery("SELECT p FROM Produto p "
                + "WHERE UPPPER(p.nome) = UPPPER(:nome)", Produto.class)
                      .setParameter("nome", nome)
                      .getSingleResult();
    }

    public List<Produto> recuperarDescricaoCom(String chave) {
        return manager.createQuery("SELECT p FROM Produto p "
                + "WHERE UPPPER(p.nome) LIKE '%UPPPER(:chave)%'", Produto.class)
                      .setParameter("chave", chave)
                      .getResultList();
    }

    public List<Produto> recuperarPrecoEntre(double inicio, double fim) {
        return manager.createQuery("SELECT p FROM Produto p "
                + "WHERE p.preco BETWEEN :inicio AND :fim", Produto.class)
                      .setParameter("inicio", inicio)
                      .setParameter("fim", fim)
                      .getResultList();
    }
}
