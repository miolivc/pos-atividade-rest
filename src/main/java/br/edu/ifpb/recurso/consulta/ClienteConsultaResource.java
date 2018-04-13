
package br.edu.ifpb.recurso.consulta;

import br.edu.ifpb.entidade.Cliente;
import br.edu.ifpb.servico.ServicoCliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("consulta/cliente")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ClienteConsultaResource {
    
    @Inject
    private ServicoCliente clientes;
    
    @GET
    @Path("nome/{letter}")
    public Response clientesLetraNome(@PathParam("letter") String letter) {
        List<Cliente> matchs = clientes.iniciaComLetra(letter);

        if (matchs == null || matchs.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        
        GenericEntity answer = new GenericEntity<List<Cliente>>(matchs) {};
        return Response.ok().entity(answer).build();
    }
    
    @GET
    @Path("email/{email}")
    public Response clienteComEmail(@PathParam("email") String email) {
        Cliente match = clientes.comEmail(email);
        
        if (match == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        
        return Response.ok().entity(match).build();
    }
    
    
    @GET
    @Path("cpf/{cpf}")
    public Response recuperarCliente(@PathParam("cpf") String cpf) {
        Cliente cliente = clientes.recuperar(cpf);
        if (cliente == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(cliente).build();
    }
    
}