/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudoapp;

import model.Projeto;
import tudoapp.controler.ProjetoControler;
import util.ConectionFactory;
/**
 *
 * @author 
 */
public class Tudoapp {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
            
       ProjetoControler projetoControler = new ProjetoControler();
       Projeto projeto = new Projeto();
       projeto.setNome("ProjetoTeste");
       projeto.setTarefa("Tarefa");
       projetoControler.save(projeto);
       
       
       
   }            
       /** // TODO code application logic here
       Connection c = ConectionFactory.getConection();
       ConectionFactory.closeConnection(c); */
              
    
    
}
