package br.edu.ifpb.servico;

import br.edu.ifpb.entidade.Usuario;
import br.edu.ifpb.repositorio.Usuarios;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ServicoUsuario {

    @Inject
    private Usuarios usuarios;

    public Usuario adicionar(Usuario usuario) {
        return usuarios.adicionar(usuario);
    }

    public void remover(Usuario usuario) {
        usuarios.remover(usuario);
    }

    public void editar(String email, Usuario usuario) {
        usuarios.editar(email, usuario);
    }

    public Usuario recuperar(String email) {
        return usuarios.recuperar(email);
    }

    public List<Usuario> todos() {
        return usuarios.recuperar();
    }
}
