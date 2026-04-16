package estga.lp.lp_trabalho2;

import javax.swing.*;
import java.awt.*;

/**
 * Janela para CRIAR OU EDITAR um projeto.
 *
 * Tem campos de texto para o nome e descricao do projeto.
 * O botao "Guardar" cria o objeto Projeto e adiciona ao GestorProjetos.
 *
 * Se receber um projeto existente (projetoParaEditar != null), entra em modo edicao.
 */
public class CriarProjetoFrame extends JFrame {

    private GestorProjetos gestor;
    private JFrame janelaAnterior;
    private Projeto projetoParaEditar;

    private JTextField campNome;
    private JTextArea campDescricao;

    /** Construtor para CRIAR novo projeto */
    public CriarProjetoFrame(GestorProjetos gestor, JFrame janelaAnterior) {
        this(gestor, janelaAnterior, null);
    }

    /** Construtor para EDITAR projeto existente */
    public CriarProjetoFrame(GestorProjetos gestor, JFrame janelaAnterior, Projeto projetoParaEditar) {
        this.gestor = gestor;
        this.janelaAnterior = janelaAnterior;
        this.projetoParaEditar = projetoParaEditar;

        boolean modoEdicao = (projetoParaEditar != null);
        setTitle(modoEdicao ? "Editar Projeto" : "Criar Novo Projeto");
        setSize(800, 550);
        setMinimumSize(new Dimension(800, 550));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        construirInterface(modoEdicao);
    }

    private void construirInterface(boolean modoEdicao) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblNome = new JLabel("Nome do Projeto:");
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblNome);
        painel.add(Box.createVerticalStrut(5));

        campNome = new JTextField();
        campNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        campNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(campNome);
        painel.add(Box.createVerticalStrut(15));

        JLabel lblDesc = new JLabel("Descricao:");
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblDesc);
        painel.add(Box.createVerticalStrut(5));

        campDescricao = new JTextArea(5, 20);
        campDescricao.setLineWrap(true);
        campDescricao.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(campDescricao);
        scrollDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(scrollDesc);
        painel.add(Box.createVerticalStrut(20));

        if (modoEdicao) {
            campNome.setText(projetoParaEditar.getNome());
            campDescricao.setText(projetoParaEditar.getDescricao());
        }

        if (modoEdicao) {
            JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            painelBotoesAcao.setAlignmentX(Component.LEFT_ALIGNMENT);

            JButton btnTarefas = new JButton("Gerir Tarefas");
            btnTarefas.addActionListener(e -> {
                TarefasFrame janelaTarefas = new TarefasFrame(projetoParaEditar, this);
                janelaTarefas.setVisible(true);
                this.setVisible(false);
            });
            painelBotoesAcao.add(btnTarefas);
            painelBotoesAcao.add(Box.createHorizontalStrut(10));

            JButton btnMembros = new JButton("Gerir Membros");
            btnMembros.addActionListener(e -> {
                MembrosFrame janelaMembros = new MembrosFrame(projetoParaEditar, this);
                janelaMembros.setVisible(true);
                this.setVisible(false);
            });
            painelBotoesAcao.add(btnMembros);

            painel.add(painelBotoesAcao);
            painel.add(Box.createVerticalStrut(20));
        }

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarProjeto(modoEdicao));
        painelBotoes.add(btnGuardar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltar());
        painelBotoes.add(btnVoltar);

        painel.add(painelBotoes);
        add(painel);
    }

    private void guardarProjeto(boolean modoEdicao) {
        String nome = campNome.getText().trim();
        String descricao = campDescricao.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O nome do projeto nao pode estar vazio!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (modoEdicao) {
            projetoParaEditar.setNome(nome);
            projetoParaEditar.setDescricao(descricao);
            JOptionPane.showMessageDialog(this, "Projeto atualizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Projeto novoProjeto = new Projeto(nome, descricao);
            gestor.adicionarProjeto(novoProjeto);
            JOptionPane.showMessageDialog(this,
                "Projeto criado com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            campNome.setText("");
            campDescricao.setText("");
        }
    }

    private void voltar() {
        janelaAnterior.setVisible(true);
        this.dispose();
    }
}
