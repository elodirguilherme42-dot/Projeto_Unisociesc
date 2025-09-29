package Unisociesc;

public class Usuario {
    String nome;
    int idade;
    String sexo;
    String email;
    String telefone;
    String cpf;
    String cidade;
    String senha; 

    public String getNome() {
    return this.nome;
    }

    public String getSenha() {
    return this.nome;
    }

    public Usuario(String nome, int idade, String sexo, String email, String telefone, String cpf, String cidade, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cidade = cidade;
        this.senha = senha;
    }


    public String toString() {
        return "Usuário: " + nome + " (" + idade + " anos, " + cidade + ")";
    }


}
