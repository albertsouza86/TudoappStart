/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudoapp.controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Tarefa;
import util.ConectionFactory;



/**
 *
 * @author PC
 */
public class TarefaControler {
    
    public void save(Tarefa tarefa){
        
        String sql = "INSERT INTO tarefa (Nome,"
                + " Descricao,"
                + " Tf_status,"
                + " Observacoes,"
                + " Prazo,"
                + " Data_de_criacao,"
                + " Data_atualizacao,"
                + " Id_projects) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, tarefa.getNome());
            statement.setString(2, tarefa.getDescricao());
            statement.setBoolean(3, tarefa.isTf_status());
            statement.setString(4, tarefa.getObservacoes());
            statement.setDate(5, new Date(tarefa.getPrazo().getTime()));
            statement.setDate(6, new Date(tarefa.getData_de_criacao().getTime()));
            statement.setDate(7, new Date(tarefa.getData_atualizacao().getTime()));
            statement.setInt(8, tarefa.getId_projects());
            statement.execute();       
            
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar " + ex.getMessage(), ex);
        }
        finally {
            ConectionFactory.closeConection(connection, statement);
                            
            }
        }
        
       
    public void update(Tarefa tarefa){
        
        String sql = "UPDATE tarefa SET, "
                + "Nome = ?,"
                + "Descricao = ?,"
                + "Observacoes = ?,"
                + "Tf_status = ?"
                + "Prazo = ?,"
                + "Data_de_criacao = ?,"
                + "Data_atualizacao = ?,"
                + "Id_projects = ?"
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //estabelecendo a conexão com o banco de dados
            connection = ConectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //setando os valores do statement
            statement.setString(1, tarefa.getNome());
            statement.setString(2, tarefa.getDescricao());
            statement.setString(3, tarefa.getObservacoes());
            statement.setBoolean(4, tarefa.isTf_status());
            statement.setDate(5, new Date(tarefa.getPrazo().getTime()));
            statement.setDate(6, new Date(tarefa.getData_de_criacao().getTime()));
            statement.setDate(7, new Date(tarefa.getData_atualizacao().getTime()));
            statement.setInt(8, tarefa.getId_projects());
            statement.setInt(9, tarefa.getId());
            
            //executando a query
            statement.execute();       
                                                
        } catch (Exception ex) {
            
            //tratando o erro se ocorrer
            throw new RuntimeException("Erro ao atualizar a tarefa" + ex.getMessage(), ex);            
        }
        finally {
            //fechando a conexao com o banco de dados
            ConectionFactory.closeConection(connection, statement);
                            
            }
        
        
    }
    
    public void removebyid(int tarefaId) {
        
        String sql = "DELETE FROM tarefa WHERE ID = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //preparando a conexao com o banco de dados
            connection = ConectionFactory.getConnection();
            
            //preparando a query
            statement = connection.prepareStatement(sql);
            
            //setando os valores
            statement.setInt(1, tarefaId);
            
            //executando a query
            statement.execute();           
       
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao DELETAR tarefa" + ex.getMessage(), ex);
        } finally {
            ConectionFactory.closeConection(connection, statement);
                    
        }
        
    }
    
        public List<Tarefa> getAll(int Id_projects){
        
        String sql = "SELECT FROM tarefa  WHERE Id_projects = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de tarefas que será devolvida quando o metodo acontecer
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        
        try {
            
            //criando a conexao
            connection = ConectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //setando os valores que corresponde ao filtro de busca
            statement.setInt(1, Id_projects);
            
            //valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos no meu resultSet
            while(resultSet.next()){
                
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultSet.getInt("id"));
                tarefa.setNome(resultSet.getString("Nome"));
                tarefa.setDescricao(resultSet.getString("Descricao"));
                tarefa.setObservacoes(resultSet.getString("Descricacao"));
                tarefa.setTf_status(resultSet.getBoolean("Tf_Status"));
                tarefa.setPrazo(resultSet.getDate("Prazo"));
                tarefa.setData_de_criacao(resultSet.getDate("Data_de_criacao"));
                tarefa.setData_atualizacao(resultSet.getDate("Data_atualizacao"));           
                tarefa.setId_projects(resultSet.getInt("Id_projects"));
                
                tarefas.add(tarefa);
                
                
            }
            
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao Inserir tarefa" + ex.getMessage(), ex);
        }finally {
            ConectionFactory.closeConection(connection, statement, resultSet);
        }
          
         
     //retornar listas de tarefas que foi criada e carregada do banco de dados
     return tarefas;
        
    }
    
        
  
    
   
}
