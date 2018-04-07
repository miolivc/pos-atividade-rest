package br.edu.ifpb.recurso;

import br.edu.ifpb.configuracao.AppLogger;
import br.edu.ifpb.entidade.Usuario;
import br.edu.ifpb.security.AutorizacaoBasic;
import br.edu.ifpb.servico.ServicoUsuario;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
@Path("usuario")
@Interceptors(AppLogger.class)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UsuarioRecurso {

    @Inject
    private ServicoUsuario usuarios;

    @POST
    public Response criarUsuario(@FormParam("email") String email,
                @FormParam("senha") String senha) throws IOException {

        Usuario usuario = Usuario.of(email, senha);
        usuarios.adicionar(usuario);
        
        String token = AutorizacaoBasic.encode(email, senha);

        return Response.ok()
                .entity("{'Token': " + token + "}")
                .build();
    }

}
