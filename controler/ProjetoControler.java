/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudoapp.controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Projeto;
import util.ConectionFactory;

/**
 *
 * @author PC
 */
public class ProjetoControler {
    
     public void save(Projeto projeto){
        
        String sql = "INSERT INTO Projeto (Nome,"
                + "Tarefa"                
                + " Data_de_criacao,"
                + " Data_atualizacao,"
                + " Id_projects)"
                + " VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, projeto.getNome());
            statement.setString(2, projeto.getTarefa());
            statement.setDate(3, (java.sql.Date) new Date(projeto.getData_de_Criacao().getTime()));
            statement.setDate(4, (java.sql.Date) new Date(projeto.getData_atualizacao().getTime()));
            statement.execute();       
            
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto " + ex.getMessage(), ex);
        }
        finally {
            ConectionFactory.closeConection(connection, statement);
                            
            }
        }
     
     public void update(Projeto projeto){
         String sql = "UPDATE projeto set, "
                 + "Nome = ?, "
                 + "Tarefa = ?, "
                 + "Data_de_criacao = ?,"
                 + "Data_de_criacao = ?";
         Connection connection = null;
         PreparedStatement statement = null;
         
         try {
             
             connection = ConectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             
             statement.setString(1, projeto.getNome());
             statement.setString(2, projeto.getTarefa());
             statement.setDate(3, (java.sql.Date) new Date(projeto.getData_de_Criacao().getTime()));
             statement.setDate(4, (java.sql.Date) new Date(projeto.getData_atualizacao().getTime()));
             statement.execute();
                 
             
         } catch (Exception ex) {
             throw new RuntimeException ("Erro ao atualizar o banco de dados");
            
         }
         finally{ 
             
             ConectionFactory.closeConection(connection, statement);
     }
     }
     
     public void removebyId(int projetoId)throws SQLDataException{
         String SQL = "DELETE FROM Projeto WHERE Id = ?";
         
         Connection connection = null;
         PreparedStatement statement = null;
         
         try {
             connection = ConectionFactory.getConnection();
             statement = connection.prepareStatement(SQL);
             
             statement.setInt(1, projetoId);
             statement.execute();
             
             
                   
         } catch (Exception ex) {
             throw new RuntimeException("Erro ao DELETAR Projeto" + ex.getMessage(), ex);
         }
         finally{
             ConectionFactory.closeConection(connection, statement);
         }
     }

     
     public List<Projeto> getAll(){
        
        String sql = "SELECT * FROM Projetos";
        
        //Lista de tarefas que será devolvida quando o metodo acontecer
       
        List<Projeto> projetos = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
         
        try {
            
            //criando a conexao
            connection = ConectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos no meu resultSet
            while(resultSet.next()){    
            
                Projeto projeto = new Projeto();
                projeto.setId(resultSet.getInt("id"));
                projeto.setNome(resultSet.getString("Nome"));
                projeto.setTarefa(resultSet.getString("Tarefa"));
                projeto.setData_de_Criacao(resultSet.getDate("Data_de_criacao"));
                projeto.setData_atualizacao(resultSet.getDate("Data_atualizacao"));           
                projetos.add(projeto);
                
                
            }
            
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao Inserir tarefa" + ex.getMessage(), ex);
        }finally {
            ConectionFactory.closeConection(connection, statement, resultSet);
        }
          
         
     //retornar listas de tarefas que foi criada e carregada do banco de dados
     return projetos;
        
    }
    
    
    
}
