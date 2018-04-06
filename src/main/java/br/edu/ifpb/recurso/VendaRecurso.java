package br.edu.ifpb.recurso;

import br.edu.ifpb.entidade.Venda;
import br.edu.ifpb.servico.ServicoVenda;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Stateless
@Path("venda")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class VendaRecurso {

    @Inject
    private ServicoVenda servico;

    @GET
    public Response todosAsVendas() {
        List<Venda> vendas = servico.todos();
        if (vendas == null || vendas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        GenericEntity resposta = new GenericEntity<List<Venda>>(vendas) {};
        return Response.ok().entity(resposta).build();
    }

    @GET
    @Path("{id}")
    public Response recuperarVenda(@PathParam("id") long id) {
        Venda venda = servico.recuperar(id);
        if (venda == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(venda).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criarVenda(@Context UriInfo info, Venda venda) {
        String id = String.valueOf(servico.adicionar(venda).getId());
        URI path = info.getAbsolutePathBuilder().path("venda").path(id).build();
        return Response.created(path).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarVenda(@PathParam("id") long id, Venda venda) {
        servico.editar(id, venda);
        return Response.status(204).build();
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response apagarVenda(Venda venda) {
        servico.remover(venda);
        return Response.status(204).build();
    }
}