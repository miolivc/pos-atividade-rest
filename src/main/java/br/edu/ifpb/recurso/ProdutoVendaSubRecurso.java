package br.edu.ifpb.recurso;

import br.edu.ifpb.entidade.Produto;
import br.edu.ifpb.entidade.Venda;
import br.edu.ifpb.servico.ServicoProduto;
import br.edu.ifpb.servico.ServicoVenda;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
//@Path("/")
public class ProdutoVendaSubRecurso {

    @Inject
    private ServicoVenda servico;

    @Inject
    private ServicoProduto produtos;

    @GET
    public Response todosOsProdutos(@PathParam("idVenda") long idVenda) {
        List<Produto> prods = servico.recuperar(idVenda).getProdutos();
        if (prods == null || prods.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        GenericEntity resposta = new GenericEntity<List<Produto>>(prods) {};
        return Response.ok().entity(resposta).build();
    }


    @PUT
    @Path("{idProduto}")
    public Response adicionarProdutoVenda(@PathParam("idVenda") long idVenda, 
            @PathParam("idProduto") long idProduto) {
        Venda venda = servico.recuperar(idVenda);
        Produto produto = produtos.recuperar(idProduto);

        venda.addProduto(produto);
        servico.editar(idVenda, venda);

        return Response.status(204).build();
    }

}
