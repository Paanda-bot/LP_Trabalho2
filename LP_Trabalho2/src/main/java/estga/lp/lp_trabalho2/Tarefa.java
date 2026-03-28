/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.lp.lp_trabalho2;

/**
 *
 * @author SebastianMazoSimoes
 */
public class Tarefa {
    
    //Atributos da classe Tarefa
    private String titulo;
    private String descricao;
    private Membro responsavel;
    private EstadoTarefa estado;
    
    //Construtor
    public Tarefa(String titulo, String descricao){
        
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = EstadoTarefa.A_FAZER;
    }
    
    //Getters e Setters para permitir que os mesmos atributos sejam modificados futuramente
    public String getTitulo(){
        
        return titulo;
    }
    
    public void setTitulo(String titulo){
        
        this.titulo = titulo;
    }
    
    public String getDescricao(){
        
        return descricao;
    }
    
    public void setDescricao(String descricao){
        
        this.descricao = descricao;
    }
    
    public Membro getResponsavel(){
        
        return responsavel;
    }
    
    public void setResponsavel(Membro responsavel){
        
        this.responsavel = responsavel;
    }
    
    public EstadoTarefa getEstado(){
        
        return estado;
    }
    
    public void setEstado(EstadoTarefa novoEstado){
        
        this.estado = novoEstado;
    }
    
            
    
    //Formato visual para guardar no .TXT 
    @Override
    public String toString(){
        
        if (responsavel == null){
            
            return titulo + ";" + descricao + ";" + estado + ";" + "Sem responsavel";
        }
        else {
            
            return titulo + ";" + descricao + ";" + estado + ";" + responsavel;
            
        }
    }
}
