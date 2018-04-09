package br.edu.ifpb.recurso;

import br.edu.ifpb.entidade.Cliente;
import br.edu.ifpb.servico.ServicoCliente;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Stateless
@Path("cliente")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ClienteRecurso {

    @Inject
    private ServicoCliente servico;

    @GET
    public Response todosOsClientes() {
        List<Cliente> clientes = servico.todos();
        if (clientes == null || clientes.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        GenericEntity resposta = new GenericEntity<List<Cliente>>(clientes) {};
        return Response.ok().entity(resposta).build();
    }

    @GET
    @Path("{cpf}")
    public Response recuperarCliente(@PathParam("cpf") String cpf) {
        Cliente cliente = servico.recuperar(cpf);
        if (cliente == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(cliente).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criarCliente(@Context UriInfo info, Cliente cliente) {
        String cpf = servico.adicionar(cliente).getCpf();
        URI path = info.getAbsolutePathBuilder().path(cpf).build();
        return Response.created(path).build();
    }

    @PUT
    @Path("{cpf}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editarCliente(@PathParam("cpf") String cpf, Cliente cliente) {
        servico.editar(cpf, cliente);
        return Response.status(204).build();
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response apagarCliente(Cliente cliente) {
        servico.remover(cliente);
        return Response.status(204).build();
    }

}
