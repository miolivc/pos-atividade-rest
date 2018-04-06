package br.edu.ifpb.servicos;

import br.edu.ifpb.entidade.Venda;
import br.edu.ifpb.repositorio.Vendas;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ServicoVenda {

    @Inject
    private Vendas vendas;

    public void adicionar(Venda venda) {
        vendas.adicionar(venda);
    }

    public void remover(Venda venda) {
        vendas.remover(venda);
    }

    public void editar(long id, Venda venda) {
        vendas.editar(id, venda);
    }

    public Venda recuperar(long id) {
        return vendas.recuperar(id);
    }

    public List<Venda> todos() {
        return vendas.recuperar();
    }
}
