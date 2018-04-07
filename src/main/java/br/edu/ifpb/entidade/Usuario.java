
package br.edu.ifpb.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Usuario implements Serializable {
    
    @Id
    @Column(length = 50)
    private String email;
    
    @Column(length = 16, nullable = false)
    private String senha;

    public Usuario() {
    }
    
    private Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    public static Usuario of(String email, String senha) {
        return new Usuario(email, senha);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
