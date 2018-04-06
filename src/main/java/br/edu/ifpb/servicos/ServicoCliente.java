package br.edu.ifpb.servicos;

import br.edu.ifpb.entidade.Cliente;
import br.edu.ifpb.repositorio.Clientes;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ServicoCliente {

    @Inject
    private Clientes clientes;

    public Cliente adicionar(Cliente cliente) {
        return clientes.adicionar(cliente);
    }

    public void remover(Cliente cliente) {
        clientes.remover(cliente);
    }

    public void editar(String cpf, Cliente cliente) {
        clientes.editar(cpf, cliente);
    }

    public Cliente recuperar(String cpf) {
        return clientes.recuperar(cpf);
    }

    public List<Cliente> todos() {
        return clientes.recuperar();
    }
}
