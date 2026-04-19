package estga.lp.lp_trabalho2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Janela que lista todos os projetos criados/carregados.
 *
 * Permite ao utilizador:
 *   - Ver todos os projetos
 *   - Selecionar um para editar
 *   - Eliminar um projeto
 *   - Guardar um projeto em ficheiro
 *   - Exportar um projeto para TXT
 */
public class ListaProjetosFrame extends JFrame {

    private GestorProjetos gestor;
    private JFrame janelaAnterior;

    private DefaultListModel<Projeto> modeloLista;
    private JList<Projeto> listaVisual;

    public ListaProjetosFrame(GestorProjetos gestor, JFrame janelaAnterior) {
        this.gestor = gestor;
        this.janelaAnterior = janelaAnterior;

        setTitle("Lista de Projetos");
        setSize(800, 550);
        setMinimumSize(new Dimension(800, 550));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        construirInterface();
        carregarProjetos();
    }

    private void construirInterface() {
    setLayout(new BorderLayout(10, 10));

    modeloLista = new DefaultListModel<Projeto>();
    listaVisual = new JList<Projeto>(modeloLista);
    listaVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scroll = new JScrollPane(listaVisual);
    add(scroll, BorderLayout.CENTER);

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

    // botao editar
    JButton btnEditar = new JButton("Editar");
    btnEditar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editarSelecionado();
        }
    });
    painelBotoes.add(btnEditar);

    // botao eliminar
    JButton btnEliminar = new JButton("Eliminar");
    btnEliminar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            eliminarSelecionado();
        }
    });
    painelBotoes.add(btnEliminar);

    // botao guardar em ficheiro
    JButton btnGuardar = new JButton("Guardar em Ficheiro");
    btnGuardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            guardarSelecionado();
        }
    });
    painelBotoes.add(btnGuardar);

    // botao exportar txt
    JButton btnExportar = new JButton("Exportar TXT");
    btnExportar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            exportarSelecionado();
        }
    });
    painelBotoes.add(btnExportar);

    // botao voltar
    JButton btnVoltar = new JButton("Voltar");
    btnVoltar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            voltar();
        }
    });
    painelBotoes.add(btnVoltar);

    add(painelBotoes, BorderLayout.SOUTH);

    JLabel lblTitulo = new JLabel("  Projetos disponiveis:", SwingConstants.LEFT);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
    add(lblTitulo, BorderLayout.NORTH);
    }

    private void carregarProjetos() {
        modeloLista.clear();
        for (Projeto p : gestor.getProjetos()) {
            modeloLista.addElement(p);
        }
    }

    private void editarSelecionado() {
        Projeto selecionado = listaVisual.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um projeto primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        CriarProjetoFrame janelaEditar = new CriarProjetoFrame(gestor, this, selecionado);
        janelaEditar.setVisible(true);
        this.setVisible(false);
    }

    private void eliminarSelecionado() {
        Projeto selecionado = listaVisual.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um projeto primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Tem a certeza que quer eliminar " + selecionado.getNome() + "?",
            "Confirmar eliminacao", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            gestor.removerProjeto(selecionado);
            carregarProjetos();
        }
    }

    private void guardarSelecionado() {
        Projeto selecionado = listaVisual.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um projeto para guardar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new java.io.File(selecionado.getNome() + ".txt"));
        int resultado = chooser.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String caminho = chooser.getSelectedFile().getAbsolutePath();
            GestorFicheiros.guardar(caminho, selecionado);
            JOptionPane.showMessageDialog(this,
                "Projeto guardado com sucesso!",
                "Guardado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exportarSelecionado() {
        Projeto selecionado = listaVisual.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um projeto para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new java.io.File("exportacao_" + selecionado.getNome() + ".txt"));
        int resultado = chooser.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String caminho = chooser.getSelectedFile().getAbsolutePath();
            GestorFicheiros.exportar(caminho, selecionado);
            JOptionPane.showMessageDialog(this,
                "Exportado com sucesso!",
                "Exportado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void voltar() {
        janelaAnterior.setVisible(true);
        this.dispose();
    }
}
