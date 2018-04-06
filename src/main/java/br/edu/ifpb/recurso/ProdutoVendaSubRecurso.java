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
import java.util.stream.Collectors;

@Stateless
@Path("/")
public class ProdutoVendaSubRecurso {

    @Inject
    private ServicoVenda servico;

    @Inject
    private ServicoProduto produtos;

    @GET
    public Response todosOsProdutos(@PathParam("id") long id) {
        List<Produto> produtos = servico.recuperar(id).getProdutos();
        if (produtos == null || produtos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        GenericEntity resposta = new GenericEntity<List<Produto>>(produtos) {};
        return Response.ok().entity(resposta).build();
    }


    @PUT
    @Path("{idProduto}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response adicionarProdutoVenda(@PathParam("id") long id, @PathParam("idProduto") long idProduto) {
        Venda venda = servico.recuperar(id);
        Produto produto = produtos.recuperar(idProduto);

        venda.addProduto(produto);
        servico.editar(id, venda);

        return Response.status(204).build();
    }

}
