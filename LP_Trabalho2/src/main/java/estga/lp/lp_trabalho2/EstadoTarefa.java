/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.lp.lp_trabalho2;

/**
 *
 * @author SebastianMazoSimoes
 */
public enum EstadoTarefa {
    
    A_FAZER("A Fazer"),
    EM_PROGERSSO("Em Progresso"),
    CONCLUIDO("Concluido");
    
    private final String estado;
    
    EstadoTarefa(String estado){
        
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        
        return estado;
    }
    
    
}
