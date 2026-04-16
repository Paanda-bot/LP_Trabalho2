package estga.lp.lp_trabalho2;

import java.util.ArrayList;
import trabalho2.IODataClass;

/**
 * Classe responsável por GUARDAR e CARREGAR projetos em ficheiros TXT,
 * utilizando a biblioteca IODataLibrary fornecida (IODataClass).
 *
 * A IODataClass disponibiliza dois métodos principais:
 *   - {@code loadData(filepath)}  → lê o ficheiro e devolve um {@code String[]}
 *                                   onde cada posição é uma linha do ficheiro.
 *   - {@code writeData(filepath, String[])} → escreve cada elemento do array
 *                                             numa linha separada do ficheiro.
 *
 * FORMATO DO FICHEIRO (uma linha por entrada):
 *   PROJETO;nome;descricao
 *   MEMBRO;nome;nmec
 *   TAREFA;titulo;descricao;estado;nomeResponsavel;nmecResponsavel
 *   (se não houver responsável: TAREFA;titulo;descricao;estado;null;0)
 */
public class GestorFicheiros {

    /**
     * Guarda um projeto num ficheiro de texto usando a IODataClass.
     *
     * Constrói um array de Strings onde cada elemento é uma linha
     * do ficheiro, e delega a escrita à biblioteca.
     *
     * @param caminho caminho absoluto do ficheiro de destino
     * @param projeto o projeto a guardar; não deve ser {@code null}
     * @throws RuntimeException (lançada pela IODataClass) se ocorrer erro de I/O
     */
    public static void guardar(String caminho, Projeto projeto) {
        // 1. Montar a lista de linhas
        ArrayList<String> linhas = new ArrayList<>();

        linhas.add("PROJETO;" + projeto.getNome() + ";" + projeto.getDescricao());

        for (Membro m : projeto.getEquipa()) {
            linhas.add("MEMBRO;" + m.getNome() + ";" + m.getNmec());
        }

        for (Tarefa t : projeto.getListaTarefas()) {
            String resp = (t.getResponsavel() != null)
                ? t.getResponsavel().getNome() + ";" + t.getResponsavel().getNmec()
                : "null;0";
            linhas.add("TAREFA;" + t.getTitulo() + ";" + t.getDescricao()
                       + ";" + t.getEstado().name() + ";" + resp);
        }

        // 2. Converter para String[] e chamar a biblioteca
        String[] dados = linhas.toArray(new String[0]);
        IODataClass io = new IODataClass();
        io.writeData(caminho, dados);
    }

    /**
     * Carrega um projeto a partir de um ficheiro de texto usando a IODataClass.
     *
     * A biblioteca devolve um {@code String[]} com todas as linhas do ficheiro.
     * Este método interpreta cada linha pelo prefixo (PROJETO / MEMBRO / TAREFA)
     * e reconstrói os objetos Java correspondentes.
     *
     * @param caminho caminho do ficheiro a ler
     * @param gestor  o {@link GestorProjetos} onde o projeto será adicionado
     * @throws RuntimeException (lançada pela IODataClass) se o ficheiro não for encontrado
     */
    public static void carregar(String caminho, GestorProjetos gestor) {
        IODataClass io = new IODataClass();
        String[] linhas = io.loadData(caminho); // cada posição = uma linha

        Projeto projetoAtual = null;

        for (String linha : linhas) {
            if (linha == null || linha.isBlank()) continue;

            String[] partes = linha.split(";");

            if (partes[0].equals("PROJETO") && partes.length >= 3) {
                projetoAtual = new Projeto(partes[1], partes[2]);
                gestor.adicionarProjeto(projetoAtual);

            } else if (partes[0].equals("MEMBRO") && partes.length >= 3 && projetoAtual != null) {
                Membro m = new Membro(partes[1], Integer.parseInt(partes[2]));
                projetoAtual.adicionarMembro(m);

            } else if (partes[0].equals("TAREFA") && partes.length >= 6 && projetoAtual != null) {
                Tarefa t = new Tarefa(partes[1], partes[2]);
                t.setEstado(EstadoTarefa.valueOf(partes[3]));
                if (!partes[4].equals("null")) {
                    Membro resp = new Membro(partes[4], Integer.parseInt(partes[5]));
                    t.setResponsavel(resp);
                }
                projetoAtual.adicionarTarefa(t);
            }
        }
    }

    /**
     * Exporta um projeto para um ficheiro TXT de leitura humana (relatório).
     *
     * Usa a IODataClass para a escrita. O ficheiro gerado é apenas para
     * consulta — <b>não pode</b> ser reimportado pela aplicação.
     *
     * @param caminho caminho do ficheiro de destino
     * @param projeto o projeto a exportar
     */
    public static void exportar(String caminho, Projeto projeto) {
        ArrayList<String> linhas = new ArrayList<>();

        linhas.add("=== PROJETO: " + projeto.getNome() + " ===");
        linhas.add("Descricao: " + projeto.getDescricao());
        linhas.add("");
        linhas.add("--- MEMBROS DA EQUIPA ---");

        if (projeto.getEquipa().isEmpty()) {
            linhas.add("(sem membros)");
        } else {
            for (Membro m : projeto.getEquipa()) {
                linhas.add("  - " + m.getNome() + " (nmec: " + m.getNmec() + ")");
            }
        }

        linhas.add("");
        linhas.add("--- TAREFAS ---");

        if (projeto.getListaTarefas().isEmpty()) {
            linhas.add("(sem tarefas)");
        } else {
            for (Tarefa t : projeto.getListaTarefas()) {
                linhas.add("  [" + t.getEstado() + "] " + t.getTitulo());
                linhas.add("    Desc: " + t.getDescricao());
                if (t.getResponsavel() != null) {
                    linhas.add("    Responsavel: " + t.getResponsavel().getNome());
                }
            }
        }

        String[] dados = linhas.toArray(new String[0]);
        IODataClass io = new IODataClass();
        io.writeData(caminho, dados);
    }
}