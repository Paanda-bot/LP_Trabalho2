package estga.lp.lp_trabalho2;

import java.util.ArrayList;

/**
 * Classe "central" que gere todos os projetos da aplicacao.
 * É o MODEL principal - a GUI vai sempre falar com esta classe.
 *
 * Pensa nela como uma "gaveta" que guarda todos os projetos em memoria.
 */
public class GestorProjetos {

    // A lista de todos os projetos carregados/criados na aplicacao
    private ArrayList<Projeto> projetos;

    public GestorProjetos() {
        this.projetos = new ArrayList<>();
    }

    /** Adiciona um novo projeto à lista */
    public void adicionarProjeto(Projeto p) {
        projetos.add(p);
    }

    /** Remove um projeto da lista */
    public void removerProjeto(Projeto p) {
        projetos.remove(p);
    }

    /** Devolve todos os projetos */
    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    /** Procura um projeto pelo ID */
    public Projeto getProjetoPorId(int id) {
        for (Projeto p : projetos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; // nao encontrou
    }

    /** Devolve o numero total de projetos */
    public int getTotalProjetos() {
        return projetos.size();
    }
}
