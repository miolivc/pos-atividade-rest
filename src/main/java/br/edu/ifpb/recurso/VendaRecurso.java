package br.edu.ifpb.recurso;

import br.edu.ifpb.entidade.Venda;
import br.edu.ifpb.recurso.modelo.ModeloVenda;
import br.edu.ifpb.servico.ServicoVenda;
import javax.ws.rs.container.ResourceContext;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("venda")
public class VendaRecurso {

    @Context
    private ResourceContext resourceContext;

    @Inject
    private ServicoVenda servico;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response todosAsVendas(@Context UriInfo info) {
        List<Venda> vendas = servico.todos();
        if (vendas == null || vendas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        List<ModeloVenda> modeloVenda = new ArrayList<>();
        vendas.stream().forEach((venda) -> {
            modeloVenda.add(new ModeloVenda(info, venda));
        });

        GenericEntity resposta = new GenericEntity<List<ModeloVenda>>(modeloVenda) {};
        return Response.ok().entity(resposta).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response recuperarVenda(@Context UriInfo info, @PathParam("id") long id) {
        Venda venda = servico.recuperar(id);
        if (venda == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        ModeloVenda modelo = new ModeloVenda(info, venda);
        return Response.ok().entity(modelo).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criarVenda(@Context UriInfo info) {
        Venda venda = new Venda();
        String id = String.valueOf(servico.adicionar(venda).getId());
        URI path = info.getAbsolutePathBuilder().path(id).build();
        return Response.created(path).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarVenda(@PathParam("id") long id, Venda venda) {
        servico.editar(id, venda);
        return Response.status(200).build();
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response apagarVenda(Venda venda) {
        servico.remover(venda);
        return Response.status(200).build();
    }

    @Path("{idVenda}/produtos")
    public ProdutoVendaSubRecurso produtosDaVenda() {
        return resourceContext.getResource(ProdutoVendaSubRecurso.class);
    }

    @Path("{id}/cliente")
    public ClienteVendaSubRecurso clienteDaVenda() {
        return resourceContext.getResource(ClienteVendaSubRecurso.class);
    }
}
