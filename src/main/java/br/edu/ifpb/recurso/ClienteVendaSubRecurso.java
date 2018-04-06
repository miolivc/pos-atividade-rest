package br.edu.ifpb.recurso;

import br.edu.ifpb.entidade.Cliente;
import br.edu.ifpb.entidade.Venda;
import br.edu.ifpb.servico.ServicoCliente;
import br.edu.ifpb.servico.ServicoVenda;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/")
public class ClienteVendaSubRecurso {

    @Inject
    private ServicoVenda servico;

    @Inject
    private ServicoCliente clientes;

    @GET
    public Response todosOsProdutos(@PathParam("id") long id) {
        Cliente cliente = servico.recuperar(id).getCliente();
        if (cliente == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(cliente).build();
    }


    @PUT
    @Path("{cpf}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response adicionarProdutoVenda(@PathParam("id") long id, @PathParam("cpf") String cpf) {
        Venda venda = servico.recuperar(id);
        Cliente cliente = clientes.recuperar(cpf);

        venda.setCliente(cliente);
        servico.editar(id, venda);

        return Response.status(204).build();
    }

}
