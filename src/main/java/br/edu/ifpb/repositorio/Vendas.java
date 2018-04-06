package br.edu.ifpb.repositorio;

import br.edu.ifpb.entidade.Venda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class Vendas {

    @PersistenceContext
    private EntityManager manager;

    public void adicionar(Venda venda) {
        manager.persist(venda);
    }

    public void remover(Venda venda) {
        manager.remove(venda);
    }

    public void editar(long id, Venda venda) {
        Venda editado = manager.find(Venda.class, id);
        editado.setCliente(venda.getCliente());
        editado.setId(venda.getId());
        editado.setProdutos(venda.getProdutos());
        manager.merge(editado);
    }

    public Venda recuperar(long id) {
        return manager.find(Venda.class, id);
    }

    public List<Venda> recuperar() {
        return manager.createQuery("SELECT v FROM Venda v", Venda.class)
                .getResultList();
    }
}
