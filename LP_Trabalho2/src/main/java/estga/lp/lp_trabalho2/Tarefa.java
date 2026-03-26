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
    private String estado;
    private Membro responsavel;
    
    //Construtor
    public Tarefa(String titulo, String descricao){
        
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = "A fazer"; // Estado inicial da tarefa
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
    
    public String getEstado(){
        
        return estado;
    }
    
    public void setEstado(String novoEstado){
        
        this.estado = novoEstado;
    }
    
    public Membro getResponsavel(){
        
        return responsavel;
    }
    
    public void setResponsavel(Membro responsavel){
        
        this.responsavel = responsavel;
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
