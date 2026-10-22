package br.com.fiap.portal.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity//Definição do tipo da classe para Entidade
@Table(name="ALUNO") //Relacionar a tabela do Banco
public class Aluno {
    @Id //Definição da chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Definição do gerador automatico
    private Long id;

    @NotBlank //Definição de campo obrigatório
    private  String nome;
    private String email;
    private String telefone;


    public Aluno(Long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Aluno (){

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
