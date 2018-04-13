package br.edu.ifpb.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
public class Cliente implements Serializable {

    @Id
    @Column(length = 14)
    private String cpf;

    @Column(length = 70, nullable = false)
    private String nome;
    
    @Column(length = 50)
    private String email;

    public Cliente() {
    }

    private Cliente(String cpf, String nome, String email){
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public static Cliente of(String cpf, String nome, String email) {
        return new Cliente(cpf, nome, email);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
