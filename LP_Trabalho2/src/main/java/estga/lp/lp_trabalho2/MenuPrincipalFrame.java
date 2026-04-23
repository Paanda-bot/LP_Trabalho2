package estga.lp.lp_trabalho2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JANELA PRINCIPAL (Menu) da aplicacao.
 *
 * Esta é a primeira janela que aparece quando o programa inicia.
 * Tem 4 botões:
 *   - Criar novo projeto
 *   - Listar projetos
 *   - Carregar de ficheiro
 *   - Sair
 *
 * O GestorProjetos é passado como parametro - assim todas as janelas
 * partilham os mesmos dados (sem criar copias)- isso otimiza e é mais simples.
 */
public class MenuPrincipalFrame extends JFrame { //Jframe é necessario para a parte visual

    // Referencia ao gestor central - passada para todas as outras janelas
    private GestorProjetos gestor;

    public MenuPrincipalFrame(GestorProjetos gestor) {
        this.gestor = gestor;

        // --- Configuracao basica da janela ---
        setTitle("Gestor Kanban - Menu Principal");
        setSize(800, 550);
        setMinimumSize(new Dimension(800, 550));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // fecha o programa ao fechar esta janela

        // Construir o conteudo
        construirInterface();
    }

    private void construirInterface() {
    // painel principal com layout vertical
    JPanel painel = new JPanel();
    painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
    painel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

    // titulo da janela
    JLabel titulo = new JLabel("Gestor de Projetos Kanban");
    titulo.setFont(new Font("Arial", Font.BOLD, 18));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    painel.add(titulo);
    painel.add(Box.createVerticalStrut(30));

    // botao criar novo projeto
    JButton btnCriar = new JButton("Criar Novo Projeto");
    btnCriar.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnCriar.setMaximumSize(new Dimension(220, 40));
    btnCriar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CriarProjetoFrame janelaCrear = new CriarProjetoFrame(gestor, MenuPrincipalFrame.this);
            janelaCrear.setVisible(true);
            setVisible(false);
        }
    });
    painel.add(btnCriar);

    // botao listar projetos
    JButton btnLista = new JButton("Lista de Projetos");
    btnLista.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnLista.setMaximumSize(new Dimension(220, 40));
    btnLista.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ListaProjetosFrame janelaLista = new ListaProjetosFrame(gestor, MenuPrincipalFrame.this);
            janelaLista.setVisible(true);
            setVisible(false);
        }
    });
    painel.add(btnLista);

    // botao carregar de ficheiro
    JButton btnCarregar = new JButton("Carregar de Ficheiro");
btnCarregar.setAlignmentX(Component.CENTER_ALIGNMENT);
btnCarregar.setMaximumSize(new Dimension(220, 40));
btnCarregar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int resultado = chooser.showOpenDialog(MenuPrincipalFrame.this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String caminho = chooser.getSelectedFile().getAbsolutePath();
            int projetosAntes = gestor.getTotalProjetos();
            try {
                GestorFicheiros.carregar(caminho, gestor);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MenuPrincipalFrame.this,
                    "Erro ao carregar: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int projetosDepois = gestor.getTotalProjetos();
            if (projetosDepois > projetosAntes) {
                JOptionPane.showMessageDialog(MenuPrincipalFrame.this,
                    "Projeto carregado! Verifique na lista.",
                    "Carregado com sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(MenuPrincipalFrame.this,
                    "Ficheiro inválido ou formato não reconhecido.\nVerifica se é um ficheiro guardado por esta aplicação.",
                    "Erro ao carregar",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
painel.add(btnCarregar);
painel.add(Box.createVerticalStrut(20));

    // botao sair
    JButton btnSair = new JButton("Sair");
    btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnSair.setMaximumSize(new Dimension(220, 40));
    btnSair.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
    painel.add(btnSair);

    add(painel);
    }
}
