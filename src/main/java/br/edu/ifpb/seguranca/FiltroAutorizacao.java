package br.edu.ifpb.seguranca;

import br.edu.ifpb.entidade.Usuario;
import br.edu.ifpb.servico.ServicoUsuario;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.util.Map;
import javax.ejb.EJB;
import javax.ws.rs.container.PreMatching;

@Provider
@PreMatching
public class FiltroAutorizacao implements ContainerRequestFilter {

    @EJB
    private ServicoUsuario usuarios;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (requestContext.getUriInfo().getPath().contains("usuario")) {
            return;
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isEmpty() || (!authHeader.contains("Basic "))) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{'msg':'unauthorized'}")
                    .build());
        }

        Map<String, String> decodeString = AutorizacaoBasic.decode(authHeader);
        Usuario usuario = usuarios.recuperar(decodeString.get("email"));
        
        if (usuario != null && ! usuario.getEmail().equalsIgnoreCase(decodeString.get("email"))
                || ! usuario.getSenha().equalsIgnoreCase(decodeString.get("senha"))) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{'msg':'unauthorized'}")
                    .build());
        }
    }

}
