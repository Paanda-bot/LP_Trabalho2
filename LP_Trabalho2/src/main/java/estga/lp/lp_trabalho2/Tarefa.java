package estga.lp.lp_trabalho2;

/**
 * Representa uma tarefa dentro de um projeto Kanban.
 * Cada tarefa tem um estado (A Fazer, Em Progresso, Concluido)
 * e pode ter um membro responsavel atribuido.
 */
public class Tarefa {

    private String titulo;
    private String descricao;
    private Membro responsavel;    // pode ser null se nao tiver responsavel
    private EstadoTarefa estado;

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = EstadoTarefa.A_FAZER; // começa sempre como "A Fazer"
        this.responsavel = null;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Membro getResponsavel() { return responsavel; }
    public void setResponsavel(Membro responsavel) { this.responsavel = responsavel; }

    public EstadoTarefa getEstado() { return estado; }
    public void setEstado(EstadoTarefa novoEstado) { this.estado = novoEstado; }

    /**
     * Formato para guardar no ficheiro: "titulo;descricao;estado;responsavel"
     */
    @Override
    public String toString() {
        String resp = (responsavel == null) ? "Sem responsavel" : responsavel.toString();
        return titulo + ";" + descricao + ";" + estado + ";" + resp;
    }
}
