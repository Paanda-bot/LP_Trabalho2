package estga.lp.lp_trabalho2;

/**
 * Enum que representa os estados possiveis de uma tarefa no quadro Kanban.
 * Um enum é como uma lista fixa de opções - aqui temos 3 colunas do Kanban.
 */
public enum EstadoTarefa {

    A_FAZER("A Fazer"),
    EM_PROGRESSO("Em Progresso"),   // corrigido o typo "PROGERSO" que estavas a ter
    CONCLUIDO("Concluído");

    // Texto legível que aparece na interface
    private final String descricao;

    EstadoTarefa(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
