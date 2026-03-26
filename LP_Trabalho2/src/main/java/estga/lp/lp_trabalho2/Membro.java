/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.lp.lp_trabalho2;

/**
 *
 * @author SebastianMazoSimoes
 */
public class Membro {
    
    //Atributos do aluno ou membro da equipa
    private String nome;
    private int nmec;
    
    //Construtor
    public Membro(String nome, int nmec){
        
        this.nome = nome;
        this.nmec = nmec;
    }
   
    //Getters e Setters para alterar os atributos dos alunos
    public String getNome(){
    
        return nome;
    }

    public void setNome(String nome){
        
        this.nome = nome;
    }
    
    public int getNmec(){
        
        return nmec;
    }
    
    public void setNmec(int nmec){
        
        this.nmec = nmec;
    }
    
    @Override
    public String toString(){
        
        return nome + ";" + nmec;
    }
}