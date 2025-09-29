package Unisociesc;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Evento {
    String nome;
    LocalDateTime horario;
    String local;
    String descricao;
    String categoria;

    public String getNome() {
    return this.nome;
    }

    public LocalDateTime getHorario() {
    return this.horario;
}

    public String getLocal() {
    return this.local;
    }

    public String getDescricao() {
    return this.descricao;
    }

    public String getCategoria() {
    return this.categoria;
    }

    List<Usuario> participantes;
    static String[] categoriasValidas = {"Festa", "Show", "Esporte", "Aniversário", "Cultural", "Casamento"};

    public Evento(String nome, LocalDateTime  horario, String local, String descricao, String categoria) {
        this.nome = nome;

        
        this.horario = horario;


        this.local = local;
        
        boolean valida = false;
        for(String c : categoriasValidas) {
            if(c.equalsIgnoreCase(categoria)){
                valida = true;
                break;
            }
        }
        if(valida){
            this.categoria = categoria;
        } else {
            System.out.println("Categoria inválida! Utilize categoria padrão 'Outros'.");
            this.categoria = "Outros";
        }
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    public void adicionarParticipante(Usuario u){
        participantes.add(u);
        System.out.println(u.getNome() + "adicionado ao evento " + this.nome);
    }

    public void removerParticipante(Usuario u){
        participantes.remove(u);
        System.out.println(u.getNome() + "removido do evento " + this.nome);
    }

    public void listarParticipantes() {
    if (participantes.isEmpty()) {
        System.out.println("A lista de participantes está vazia.");
    } else {
        System.out.println("Lista de participantes:");
        for (Usuario u : participantes) {
            System.out.println(u.getNome());
        }
    }
}

    @Override
    public String toString() {
        LocalDateTime agora = LocalDateTime.now();
        String status;
        if (horario.isBefore(agora)) {
            status = " (JÁ ACONTECEU)";
        } else if (horario.isAfter(agora) && horario.isBefore(agora.plusHours(2))){
            status  = " (EVENTO ATIVO)";
        } else {
            status = " (PRÓXIMO EVENTO) " ;
        }
        return "Evento: " + nome + " - " + horario + " - " + local + " - " + descricao + status;
    }

}



