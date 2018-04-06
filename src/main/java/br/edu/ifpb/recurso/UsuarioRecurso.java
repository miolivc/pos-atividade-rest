package br.edu.ifpb.recurso;

import br.edu.ifpb.security.AutorizacaoBasic;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Stateless
@Path("usuario")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UsuarioRecurso {

    private File usuarios = new File("usuarios.txt");

    @POST
    public Response criarUsuario(@FormParam("email") String email, @FormParam("senha") String senha) throws IOException {

        String usuario = String.format("[%s, %s]", email.toLowerCase(), senha.toLowerCase());

        BufferedReader leitor = new BufferedReader(new FileReader(usuarios));
        String usuarioExistente = leitor.readLine();

        while (usuarioExistente != null) {
            if (usuario.equalsIgnoreCase(usuario)) {
                Response.status(Response.Status.NOT_MODIFIED).build();
            }
        }

        PrintWriter escritor = new PrintWriter(usuarios);
        escritor.println(usuario);

        String token = AutorizacaoBasic.encode(email, senha);

        return Response.ok()
                .entity("{'Token': " + token + "}")
                .build();
    }

}
