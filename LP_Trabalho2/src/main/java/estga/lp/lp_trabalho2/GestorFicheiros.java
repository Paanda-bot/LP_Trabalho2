package estga.lp.lp_trabalho2;

import java.io.*;

/**
 * Classe responsavel por GUARDAR e CARREGAR projetos em ficheiros TXT.
 *
 * Nao usa IODataLibrary ainda (pois nao temos a biblioteca),
 * mas faz leitura/escrita manual - quando tiveres a biblioteca
 * so precisas de substituir os metodos guardar() e carregar().
 *
 * FORMATO DO FICHEIRO:
 *   PROJETO;nome;descricao
 *   TAREFA;titulo;descricao;estado;responsavel
 *   MEMBRO;nome;nmec
 *   --- (separador entre secoes)
 */
public class GestorFicheiros {

    /**
     * Guarda UM projeto num ficheiro.
     * Chamado pelo botao "Guardar em Ficheiro" na ListaProjetosFrame.
     */
    public static void guardar(String caminho, Projeto projeto) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminho))) {

            // Linha do projeto
            pw.println("PROJETO;" + projeto.getNome() + ";" + projeto.getDescricao());
            pw.println("---");

            // Membros
            for (Membro m : projeto.getEquipa()) {
                pw.println("MEMBRO;" + m.getNome() + ";" + m.getNmec());
            }
            pw.println("---");

            // Tarefas
            for (Tarefa t : projeto.getListaTarefas()) {
                String resp = (t.getResponsavel() != null) ? t.getResponsavel().getNome() + ";" + t.getResponsavel().getNmec() : "null;0";
                pw.println("TAREFA;" + t.getTitulo() + ";" + t.getDescricao() + ";" + t.getEstado().name() + ";" + resp);
            }

        } catch (IOException e) {
            System.err.println("Erro ao guardar ficheiro: " + e.getMessage());
        }
    }

    /**
     * Carrega um projeto a partir de um ficheiro e adiciona ao GestorProjetos.
     * Chamado pelo botao "Carregar de Ficheiro" no MenuPrincipalFrame.
     */
    public static void carregar(String caminho, GestorProjetos gestor) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            Projeto projetoAtual = null;

            while ((linha = br.readLine()) != null) {
                if (linha.equals("---")) continue; // ignora separadores

                String[] partes = linha.split(";");

                if (partes[0].equals("PROJETO") && partes.length >= 3) {
                    projetoAtual = new Projeto(partes[1], partes[2]);
                    gestor.adicionarProjeto(projetoAtual);

                } else if (partes[0].equals("MEMBRO") && partes.length >= 3 && projetoAtual != null) {
                    Membro m = new Membro(partes[1], Integer.parseInt(partes[2]));
                    projetoAtual.adicionarMembro(m);

                } else if (partes[0].equals("TAREFA") && partes.length >= 5 && projetoAtual != null) {
                    Tarefa t = new Tarefa(partes[1], partes[2]);
                    // Converte o nome do enum de volta para o objeto
                    t.setEstado(EstadoTarefa.valueOf(partes[3]));
                    // Atribui responsavel se nao for "null"
                    if (!partes[4].equals("null")) {
                        Membro resp = new Membro(partes[4], Integer.parseInt(partes[5]));
                        t.setResponsavel(resp);
                    }
                    projetoAtual.adicionarTarefa(t);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar ficheiro: " + e.getMessage());
        }
    }

    /**
     * Exporta um projeto para um ficheiro TXT mais legivel (para apresentacao).
     */
    public static void exportar(String caminho, Projeto projeto) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminho))) {
            pw.println("=== PROJETO: " + projeto.getNome() + " ===");
            pw.println("Descrição: " + projeto.getDescricao());
            pw.println();

            pw.println("--- MEMBROS DA EQUIPA ---");
            if (projeto.getEquipa().isEmpty()) {
                pw.println("(sem membros)");
            } else {
                for (Membro m : projeto.getEquipa()) {
                    pw.println("  - " + m.getNome() + " (nmec: " + m.getNmec() + ")");
                }
            }
            pw.println();

            pw.println("--- TAREFAS ---");
            if (projeto.getListaTarefas().isEmpty()) {
                pw.println("(sem tarefas)");
            } else {
                for (Tarefa t : projeto.getListaTarefas()) {
                    pw.println("  [" + t.getEstado() + "] " + t.getTitulo());
                    pw.println("    Desc: " + t.getDescricao());
                    if (t.getResponsavel() != null) {
                        pw.println("    Responsavel: " + t.getResponsavel().getNome());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar ficheiro: " + e.getMessage());
        }
    }
}
