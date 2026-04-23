package estga.lp.lp_trabalho2;


/**
 * PONTO DE ENTRADA da aplicacao.
 *
 * O main cria o GestorProjetos (model central) e abre o MenuPrincipalFrame.
 * Todas as janelas vao receber este mesmo gestor como parametro/configuração,
 * garantindo que todos os dados ficam centralizados.
 * menuprincipalframe parte visual e que gerencia eventos para a comunicação via 
 * interface do usuario com o programa
 *
 */
public class LP_Trabalho2 { 

    public static void main(String[] args) {
        
        GestorProjetos gestor = new GestorProjetos();
        MenuPrincipalFrame menu = new MenuPrincipalFrame(gestor);
        menu.setVisible(true); //esse menu nunca é apagado apenas fica visivel 
      //ou na
    }
}
