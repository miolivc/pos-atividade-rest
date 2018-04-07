package br.edu.ifpb.repositorio;

import br.edu.ifpb.entidade.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class Usuarios {

    @PersistenceContext
    private EntityManager manager;

    public Usuario adicionar(Usuario usuario) {
        manager.persist(usuario);
        return usuario;
    }

    public void remover(Usuario usuario) {
        manager.remove(usuario);
    }

    public void editar(String email, Usuario usuario) {
        Usuario editado = manager.find(Usuario.class, email);
        editado.setEmail(usuario.getEmail());
        manager.merge(editado);
    }

    public Usuario recuperar(String email) {
        return manager.find(Usuario.class, email);
    }

    public List<Usuario> recuperar() {
        return manager.createQuery("SELECT c FROM Usuario c", Usuario.class)
                .getResultList();
    }
}
