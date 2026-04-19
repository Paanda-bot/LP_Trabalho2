package estga.lp.lp_trabalho2;

import java.util.ArrayList;

/**
 * Representa um projeto completo com as suas tarefas e equipa.
 * É o bloco principal do nosso Gestor do Kanban.
 */
public class Projeto {

    private static int contadorId = 1; // gera IDs automaticos para cada projeto
    private int id;
    private String nome;
    private String descricao;
    private ArrayList<Tarefa> listaTarefas;
    private ArrayList<Membro> equipa;

    public Projeto(String nome, String descricao) {
        this.id = contadorId++;  // atribui ID unico e incrementa para o proximo
        this.nome = nome;
        this.descricao = descricao;
        this.listaTarefas = new ArrayList<>();
        this.equipa = new ArrayList<>();
    }

    // --- Gestao de Tarefas ---

    public void adicionarTarefa(Tarefa t) {
        listaTarefas.add(t);
    }

    public void removerTarefa(Tarefa t) {
        listaTarefas.remove(t);
    }

    /** Devolve tarefa pela posicao na lista (0 = primeira) */
    public Tarefa getTarefa(int indice) {
        if (indice >= 0 && indice < listaTarefas.size()) {
            return listaTarefas.get(indice);
        }
        return null;
    }

    // --- Gestao de Membros ---

    public void adicionarMembro(Membro m) {
        equipa.add(m);
    }

    public void removerMembro(Membro m) {
        equipa.remove(m);
    }

    // --- Getters e Setters ---

    public int getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public ArrayList<Tarefa> getListaTarefas() { return listaTarefas; }
    public ArrayList<Membro> getEquipa() { return equipa; }

    /** Usado para mostrar o projeto na lista da GUI */
    @Override
    public String toString() {
        return "[" + id + "] " + nome;
    }
}
