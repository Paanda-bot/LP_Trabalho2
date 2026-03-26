/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.lp.lp_trabalho2;
import java.util.ArrayList;

/**
 *
 * @author SebastianMazoSimoes
 */
public class Projeto {
    
    //Atributos do Projeto
    private String nome;
    private String descricao;
    private ArrayList<Tarefa> listaTarefas;
    private ArrayList<Membro> equipa;
    
    //Construtor
    public Projeto(String nome, String descricao){
        
        this.nome = nome;
        this.descricao = descricao;
        this.listaTarefas = new ArrayList<>();
        this.equipa = new ArrayList<>();
    }
           
        //Gestão das Tarefas utilizando a ferramenta ArrayList:
    
        //Para adicionar uma tarefa    
        public void adicionarTarefa(Tarefa t){
            
            listaTarefas.add(t);        
        }
        
        //Para remover uma tarefa
        public void removerTarefa(Tarefa t){
            
            listaTarefas.remove(t);
        }
        
        //Para adicionar membros à equipa
        public void adicionarMembro(Membro m){
            
            equipa.add(m);
        }
        
        //Para remover membros da equipa
        public void removerMembro(Membro m){
            
            equipa.remove(m);
        }
      
        //Setter e Getters para editar o titulo e descrição num futuro
        public String getNome(){
            
            return nome;
        }
        
        public void setNome(String nome){
            
            this.nome = nome;
        }

        public String getDescricao() {
        return descricao;
        }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
        
        
    
    
}
