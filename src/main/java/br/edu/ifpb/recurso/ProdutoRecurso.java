package br.edu.ifpb.recurso;

import br.edu.ifpb.entidade.Produto;
import br.edu.ifpb.servico.ServicoProduto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Stateless
@Path("produto")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProdutoRecurso {

    @Inject
    private ServicoProduto servico;

    @GET
    public Response todosOsProdutos() {
        List<Produto> produtos = servico.todos();
        if (produtos == null || produtos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        GenericEntity resposta = new GenericEntity<List<Produto>>(produtos) {};
        return Response.ok().entity(resposta).build();
    }

    @GET
    @Path("{id}")
    public Response recuperarProduto(@PathParam("id") long id) {
        Produto produto = servico.recuperar(id);
        if (produto == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(produto).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criarProduto(@Context UriInfo info, Produto produto) {
        String id = String.valueOf(servico.adicionar(produto).getId());
        URI path = info.getAbsolutePathBuilder().path("produto").path(id).build();
        return Response.created(path).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarProduto(@PathParam("id") long id, Produto produto) {
        servico.editar(id, produto);
        return Response.status(204).build();
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response apagarProduto(Produto produto) {
        servico.remover(produto);
        return Response.status(204).build();
    }

}
