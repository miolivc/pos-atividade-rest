package br.edu.ifpb.recurso.consulta;

import br.edu.ifpb.entidade.Produto;
import br.edu.ifpb.servico.ServicoProduto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("consulta/produto")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProdutoConsultaResource {

    @Inject
    private ServicoProduto produtos;

    @GET
    @Path("id/{id}")
    public Response recuperarProduto(@PathParam("id") long id) {
        Produto produto = produtos.recuperar(id);
        if (produto == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(produto).build();
    }

    @GET
    @Path("descricao/{chave}")
    public Response descricaoContem(@PathParam("nome") String chave) {
        List<Produto> matchs = produtos.descricaoCom(chave);

        if (matchs == null || matchs.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        GenericEntity answer = new GenericEntity<List<Produto>>(matchs) {};
        return Response.ok().entity(answer).build();
    }

    @GET
    @Path("nome/{nome}")
    public Response comNome(@PathParam("nome") String nome) {
        Produto match = produtos.porNome(nome);

        if (match == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(match).build();
    }

    @GET
    @Path("preco")
    public Response precoEntre(@QueryParam("inicio") double inicio, 
                               @QueryParam("fim") double fim) {
        
        List<Produto> matchs = produtos.precoEntre(inicio, fim);

        if (matchs == null || matchs.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        
        GenericEntity answer = new GenericEntity<List<Produto>>(matchs) {};
        return Response.ok().entity(answer).build();
    }
    

}
