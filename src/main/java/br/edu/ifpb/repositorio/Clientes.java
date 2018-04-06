package br.edu.ifpb.repositorio;

import br.edu.ifpb.entidade.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class Clientes {

    @PersistenceContext
    private EntityManager manager;

    public Cliente adicionar(Cliente cliente) {
        manager.persist(cliente);
        return cliente;
    }

    public void remover(Cliente cliente) {
        manager.remove(cliente);
    }

    public void editar(String cpf, Cliente cliente) {
        Cliente editado = manager.find(Cliente.class, cpf);
        editado.setNome(cliente.getNome());
        manager.merge(editado);
    }

    public Cliente recuperar(String cpf) {
        return manager.find(Cliente.class, cpf);
    }

    public List<Cliente> recuperar() {
        return manager.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }
}