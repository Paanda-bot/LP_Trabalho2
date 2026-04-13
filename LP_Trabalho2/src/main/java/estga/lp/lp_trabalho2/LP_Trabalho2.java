package estga.lp.lp_trabalho2;

import javax.swing.SwingUtilities;

/**
 * PONTO DE ENTRADA da aplicacao.
 *
 * O main cria o GestorProjetos (model central) e abre o MenuPrincipalFrame.
 * Todas as janelas vao receber este mesmo gestor como parametro,
 * garantindo que todos os dados ficam centralizados.
 *
 * SwingUtilities.invokeLater() e a forma correta de arrancar uma aplicacao Swing
 * - garante que a GUI e criada na thread de eventos do Swing (EDT).
 */
public class LP_Trabalho2 {

    public static void main(String[] args) {

        // Cria o modelo central (sera passado a todas as janelas)
        GestorProjetos gestor = new GestorProjetos();

        // Abre o menu principal na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalFrame menu = new MenuPrincipalFrame(gestor);
            menu.setVisible(true);
        });
    }
}
