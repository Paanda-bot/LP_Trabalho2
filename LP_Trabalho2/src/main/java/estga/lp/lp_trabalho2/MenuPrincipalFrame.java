package estga.lp.lp_trabalho2;

import javax.swing.*;
import java.awt.*;

/**
 * JANELA PRINCIPAL (Menu) da aplicacao.
 *
 * Esta é a primeira janela que aparece quando o programa arranca.
 * Tem 4 botões:
 *   - Criar novo projeto
 *   - Listar projetos
 *   - Carregar de ficheiro
 *   - Sair
 *
 * O GestorProjetos é passado como parametro - assim todas as janelas
 * partilham os mesmos dados (sem criar copias).
 */
public class MenuPrincipalFrame extends JFrame {

    // Referencia ao gestor central - passada para todas as outras janelas
    private GestorProjetos gestor;

    public MenuPrincipalFrame(GestorProjetos gestor) {
        this.gestor = gestor;

        // --- Configuracao basica da janela ---
        setTitle("Gestor Kanban - Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha o programa ao fechar esta janela
        setLocationRelativeTo(null); // centra na tela

        // --- Construir o conteudo ---
        construirInterface();
    }

    private void construirInterface() {
        // Painel principal com layout vertical (BoxLayout)
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60)); // margens

        // Titulo
        JLabel titulo = new JLabel("Gestor de Projetos Kanban");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(30)); // espaco entre titulo e botoes

        // --- BOTAO: Criar novo projeto ---
        JButton btnCriar = new JButton("Criar Novo Projeto");
        btnCriar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCriar.setMaximumSize(new Dimension(220, 40));
        // Evento: quando o utilizador clica, abre a janela de criacao
        btnCriar.addActionListener(e -> {
            CriarProjetoFrame janelaCrear = new CriarProjetoFrame(gestor, this);
            janelaCrear.setVisible(true);
            this.setVisible(false); // esconde o menu enquanto está noutra janela
        });
        painel.add(btnCriar);
        painel.add(Box.createVerticalStrut(10));

        // --- BOTAO: Listar projetos ---
        JButton btnLista = new JButton("Lista de Projetos");
        btnLista.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLista.setMaximumSize(new Dimension(220, 40));
        btnLista.addActionListener(e -> {
            ListaProjetosFrame janelaLista = new ListaProjetosFrame(gestor, this);
            janelaLista.setVisible(true);
            this.setVisible(false);
        });
        painel.add(btnLista);
        painel.add(Box.createVerticalStrut(10));

        // --- BOTAO: Carregar de ficheiro ---
        JButton btnCarregar = new JButton("Carregar de Ficheiro");
        btnCarregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCarregar.setMaximumSize(new Dimension(220, 40));
        btnCarregar.addActionListener(e -> {
            // Usa JFileChooser para o utilizador escolher o ficheiro
            JFileChooser chooser = new JFileChooser();
            int resultado = chooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String caminho = chooser.getSelectedFile().getAbsolutePath();
                GestorFicheiros.carregar(caminho, gestor);
                JOptionPane.showMessageDialog(this,
                    "Projeto carregado! Verifique na lista.",
                    "Carregado com sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        painel.add(btnCarregar);
        painel.add(Box.createVerticalStrut(20));

        // --- BOTAO: Sair ---
        JButton btnSair = new JButton("Sair");
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.setMaximumSize(new Dimension(220, 40));
        btnSair.addActionListener(e -> System.exit(0));
        painel.add(btnSair);

        add(painel);
    }
}
