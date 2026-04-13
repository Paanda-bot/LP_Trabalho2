package estga.lp.lp_trabalho2;

import javax.swing.*;
import java.awt.*;

/**
 * Janela para gerir os MEMBROS da equipa de um projeto.
 *
 * Permite adicionar e remover membros.
 * O nome do membro vai ser mostrado no dropdown quando se atribui tarefas.
 */
public class MembrosFrame extends JFrame {

    private Projeto projeto;
    private JFrame janelaAnterior;
    private DefaultListModel<Membro> modeloLista;

    public MembrosFrame(Projeto projeto, JFrame janelaAnterior) {
        this.projeto = projeto;
        this.janelaAnterior = janelaAnterior;

        setTitle("Membros da Equipa - " + projeto.getNome());
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        construirInterface();
        carregarMembros();
    }

    private void construirInterface() {
        setLayout(new BorderLayout(5, 5));

        JLabel lblTitulo = new JLabel("  Membros da Equipa:", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblTitulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        JList<Membro> listaVisual = new JList<>(modeloLista);
        add(new JScrollPane(listaVisual), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnAdicionar = new JButton("Adicionar Membro");
        btnAdicionar.addActionListener(e -> adicionarMembro());
        painelBotoes.add(btnAdicionar);

        JButton btnEliminar = new JButton("Eliminar Membro");
        btnEliminar.addActionListener(e -> {
            Membro selecionado = listaVisual.getSelectedValue();
            if (selecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um membro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            projeto.removerMembro(selecionado);
            carregarMembros();
        });
        painelBotoes.add(btnEliminar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltar());
        painelBotoes.add(btnVoltar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void adicionarMembro() {
        JTextField fNome = new JTextField();
        JTextField fNmec = new JTextField();
        Object[] campos = {"Nome do Membro:", fNome, "Número Mecanográfico:", fNmec};

        int op = JOptionPane.showConfirmDialog(this, campos, "Adicionar Membro", JOptionPane.OK_CANCEL_OPTION);
        if (op == JOptionPane.OK_OPTION) {
            String nome = fNome.getText().trim();
            String nmecStr = fNmec.getText().trim();

            if (nome.isEmpty() || nmecStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int nmec = Integer.parseInt(nmecStr);
                Membro novo = new Membro(nome, nmec);
                projeto.adicionarMembro(novo);
                carregarMembros();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "O número mecanográfico deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarMembros() {
        modeloLista.clear();
        for (Membro m : projeto.getEquipa()) {
            modeloLista.addElement(m);
        }
    }

    private void voltar() {
        janelaAnterior.setVisible(true);
        this.dispose();
    }
}
