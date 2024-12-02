package easyticket;

import java.util.Date;

public class Usuario {
    private String nome;
    private String cpf;
    private String telefone;
    private Date dataNascimento;
    private String senha;

    public Usuario(String nome, String cpf, String telefone, Date dataNascimento, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    // Getters e setters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public Date getDataNascimento() { return dataNascimento; }
    public String getSenha() { return senha; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setSenha(String senha) { this.senha = senha; }
}
