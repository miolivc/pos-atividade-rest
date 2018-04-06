package br.edu.ifpb.security;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.*;

@Provider
public class FiltroAutorizacao implements ContainerRequestFilter {

    private File usuarios = new File("/home/miolivc/Development/pos-atividade-rest/src/main/resources/usuarios.txt");
    private boolean autenticado = false;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (requestContext.getUriInfo().getPath().contains("usuario")) {
            return;
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isEmpty() || (! authHeader.contains("Basic "))) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{'msg':'unauthorized'}")
                    .build());
        }

        String decodeString = AutorizacaoBasic.decode(authHeader);

        BufferedReader leitor = new BufferedReader(new FileReader(usuarios));
        String usuario = leitor.readLine();
        while (usuario != null) {
            if (usuario.equalsIgnoreCase(decodeString)) {
                autenticado = true;
                break;
            }
        }

        if (! autenticado) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{'msg':'unauthorized'}")
                    .build());
        }
    }

}
