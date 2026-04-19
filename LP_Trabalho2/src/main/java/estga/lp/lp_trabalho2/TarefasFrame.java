package estga.lp.lp_trabalho2;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Janela para gerir as TAREFAS de um projeto.
 *
 * Mostra 3 colunas (tal do quadro kanban):
 *   - A Fazer
 *   - Em Progresso
 *   - Concluido
 *
 * O utilizador pode adicionar tarefas, mudar o estado delas,
 * atribuir membros e elimina os mesmos.
 */
public class TarefasFrame extends JFrame {
    //herdando de Jframe - biblioteca do java para janelas
    
    private Projeto projeto;
    private JFrame janelaAnterior;

    private DefaultListModel<Tarefa> modeloAFazer;
    private DefaultListModel<Tarefa> modeloEmProgresso;
    private DefaultListModel<Tarefa> modeloConcluido;

    public TarefasFrame(Projeto projeto, JFrame janelaAnterior) {
        this.projeto = projeto;
        this.janelaAnterior = janelaAnterior;

        setTitle("Tarefas - " + projeto.getNome());
        setSize(800, 550);
        setMinimumSize(new Dimension(800, 550));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        construirInterface();
        atualizarKanban();
    }

    private void construirInterface() {
    setLayout(new BorderLayout(5, 5));

    JPanel painelKanban = new JPanel(new GridLayout(1, 3, 10, 0));
    painelKanban.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    modeloAFazer = new DefaultListModel<Tarefa>();
    modeloEmProgresso = new DefaultListModel<Tarefa>();
    modeloConcluido = new DefaultListModel<Tarefa>();

    // cores simples com new Color em vez de Color.decode
    painelKanban.add(criarColunaKanban("A Fazer", modeloAFazer, new Color(0xFFF3CD)));
    painelKanban.add(criarColunaKanban("Em Progresso", modeloEmProgresso, new Color(0xCCE5FF)));
    painelKanban.add(criarColunaKanban("Concluido", modeloConcluido, new Color(0xD4EDDA)));

    add(painelKanban, BorderLayout.CENTER);

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));

    JButton btnAdicionar = new JButton("+ Adicionar Tarefa");
    btnAdicionar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            adicionarTarefa();
        }
    });
    painelBotoes.add(btnAdicionar);

    JButton btnMoverAvanco = new JButton("Avancar Estado");
    btnMoverAvanco.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverEstadoTarefa(1);
        }
    });
    painelBotoes.add(btnMoverAvanco);

    JButton btnMoverRecuo = new JButton("Recuar Estado");
    btnMoverRecuo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverEstadoTarefa(-1);
        }
    });
    painelBotoes.add(btnMoverRecuo);

    JButton btnAtribuir = new JButton("Atribuir Membro");
    btnAtribuir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            atribuirMembro();
        }
    });
    painelBotoes.add(btnAtribuir);

    JButton btnEliminar = new JButton("Eliminar Tarefa");
    btnEliminar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            eliminarTarefa();
        }
    });
    painelBotoes.add(btnEliminar);

    JButton btnVoltar = new JButton("Voltar");
    btnVoltar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            voltar();
        }
    });
    painelBotoes.add(btnVoltar);

    add(painelBotoes, BorderLayout.SOUTH);
}

    private JPanel criarColunaKanban(String titulo, DefaultListModel<Tarefa> modelo, Color cor) {
    JPanel col = new JPanel(new BorderLayout());
    col.setBackground(cor);

    JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    col.add(lblTitulo, BorderLayout.NORTH);

    JList<Tarefa> lista = new JList<Tarefa>(modelo);
    lista.setBackground(cor);

    // personalizar como cada tarefa aparece na lista
    lista.setCellRenderer(new ListCellRenderer<Tarefa>() {
        @Override
        public Component getListCellRendererComponent(JList<? extends Tarefa> list, Tarefa value, int index, boolean isSelected, boolean cellHasFocus) {

            String resp = "";
            if (value.getResponsavel() != null) {
                resp = " [" + value.getResponsavel().getNome() + "]";
            }

            JLabel lbl = new JLabel("<html>" + value.getTitulo()
                    + "<br><small>" + value.getDescricao() + resp + "</small></html>");

            lbl.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));

            if (isSelected) {
                lbl.setBackground(Color.WHITE);
            }
            lbl.setOpaque(true);

            return lbl;
        }
    });

    col.add(new JScrollPane(lista), BorderLayout.CENTER);
    return col;
    }

    private void atualizarKanban() {
        modeloAFazer.clear();
        modeloEmProgresso.clear();
        modeloConcluido.clear();

        for (Tarefa t : projeto.getListaTarefas()) {
            switch (t.getEstado()) {
                case A_FAZER:      modeloAFazer.addElement(t); break;
                case EM_PROGRESSO: modeloEmProgresso.addElement(t); break;
                case CONCLUIDO:    modeloConcluido.addElement(t); break;
            }
        }
    }

    private void adicionarTarefa() {
        JTextField fTitulo = new JTextField();
        JTextField fDesc = new JTextField();
        Object[] campos = {"Titulo da Tarefa:", fTitulo, "Descricao:", fDesc};

        int op = JOptionPane.showConfirmDialog(this, campos, "Nova Tarefa", JOptionPane.OK_CANCEL_OPTION);
        if (op == JOptionPane.OK_OPTION) {
            String titulo = fTitulo.getText().trim();
            if (!titulo.isEmpty()) {
                Tarefa nova = new Tarefa(titulo, fDesc.getText().trim());
                projeto.adicionarTarefa(nova);
                atualizarKanban();
            }
        }
    }

    private void moverEstadoTarefa(int direcao) {
        Tarefa selecionada = getTarefaSelecionada();
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        EstadoTarefa[] estados = EstadoTarefa.values();
        int indiceAtual = selecionada.getEstado().ordinal();
        int novoIndice = indiceAtual + direcao;

        if (novoIndice < 0 || novoIndice >= estados.length) {
            JOptionPane.showMessageDialog(this, "A tarefa ja esta no estado limite!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        selecionada.setEstado(estados[novoIndice]);
        atualizarKanban();
    }

    private void atribuirMembro() {
        Tarefa selecionada = getTarefaSelecionada();
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (projeto.getEquipa().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O projeto nao tem membros. Adicione membros primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Membro[] membros = projeto.getEquipa().toArray(new Membro[0]);
        Membro escolhido = (Membro) JOptionPane.showInputDialog(this,
            "Escolha o membro responsavel:", "Atribuir Membro",
            JOptionPane.PLAIN_MESSAGE, null, membros, membros[0]);

        if (escolhido != null) {
            selecionada.setResponsavel(escolhido);
            atualizarKanban();
        }
    }

    private void eliminarTarefa() {
        Tarefa selecionada = getTarefaSelecionada();
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int conf = JOptionPane.showConfirmDialog(this,
            "Eliminar " + selecionada.getTitulo() + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            projeto.removerTarefa(selecionada);
            atualizarKanban();
        }
    }

    /**
     * Pede ao utilizador para escolher a tarefa por nome num dialogo.
     * Retorna null se nao houver tarefas ou o utilizador cancelar.
     */
    private Tarefa getTarefaSelecionada() {
        if (projeto.getListaTarefas().isEmpty()) return null;

        String[] titulos = new String[projeto.getListaTarefas().size()];
        for (int i = 0; i < projeto.getListaTarefas().size(); i++) {
            Tarefa t = projeto.getListaTarefas().get(i);
            titulos[i] = t.getTitulo() + " [" + t.getEstado() + "]";
        }

        String escolhida = (String) JOptionPane.showInputDialog(this,
            "Selecione a tarefa:", "Selecionar Tarefa",
            JOptionPane.PLAIN_MESSAGE, null, titulos, titulos[0]);

        if (escolhida == null) return null;

        for (Tarefa t : projeto.getListaTarefas()) {
            if (escolhida.startsWith(t.getTitulo())) return t;
        }
        return null;
    }

    private void voltar() {
        janelaAnterior.setVisible(true);
        this.dispose();
    }
}
