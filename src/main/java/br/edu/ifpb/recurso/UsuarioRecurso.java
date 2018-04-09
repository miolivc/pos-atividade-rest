package br.edu.ifpb.recurso;

import br.edu.ifpb.entidade.Usuario;
import br.edu.ifpb.seguranca.AutorizacaoBasic;
import br.edu.ifpb.servico.ServicoUsuario;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

@Stateless
@Path("usuario")
@Produces({MediaType.APPLICATION_JSON})
public class UsuarioRecurso {

    @Inject
    private ServicoUsuario usuarios;

    @POST
    public Response criarUsuario(@FormParam("email") String email,
                @FormParam("senha") String senha) throws IOException {

        Usuario usuario = Usuario.of(email, senha);
        usuarios.adicionar(usuario);
        
        String token = AutorizacaoBasic.encode(email, senha);

        JsonObject answer = Json.createObjectBuilder()
                                .add("Token", token)
                                .build();
        
        return Response.ok()
                .entity(answer)
                .build();
    }

}
