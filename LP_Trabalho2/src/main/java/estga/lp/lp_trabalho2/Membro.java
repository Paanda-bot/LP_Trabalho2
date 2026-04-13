package estga.lp.lp_trabalho2;

/**
 * Representa um membro da equipa de um projeto.
 * Guarda nome e numero mecanografico (nmec).
 */
public class Membro {

    private String nome;
    private int nmec;

    public Membro(String nome, int nmec) {
        this.nome = nome;
        this.nmec = nmec;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getNmec() { return nmec; }
    public void setNmec(int nmec) { this.nmec = nmec; }

    /**
     * Formato usado para guardar no ficheiro TXT: "Nome;12345"
     */
    @Override
    public String toString() {
        return nome + ";" + nmec;
    }
}
