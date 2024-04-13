package com.example;
//importando as classes de conexao e excecao
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String url = "jdbc:mysql://localhost:3306/biblioteca"; 
        String user = "root";
        String passowrd = "minhasenha";
        try (Connection conexao = DriverManager.getConnection(url, user, passowrd)){
            System.out.println("Conexao bem sucedida");
            Statement comando = conexao.createStatement();
    /*       System.out.println(InserirLivro(comando, "RedHat", "Tulio", 2024)); */
            ConsultarLivros(comando);
            AtualizarLivro(comando, "Revolucao dos bichos", "Gorge", 2018, 2);
            ConsultarLivros(comando);
            DeletarLivro(comando, 1);
            ConsultarLivros(comando);
        } catch (SQLException e) { //tratar excecoes
            System.out.println ("Excessao SQL");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println ("Excessao Generica");
            e.printStackTrace();
        }
    }
    private static String InserirLivro (Statement statement, String titulo, String autor, int ano_publicacao) throws SQLException
    {
        int retorno = statement.executeUpdate("INSERT INTO livros (titulo, autor, ano_publicacao) VALUES ('"+ titulo + "', '"+ autor + "', "+ ano_publicacao + ");");
        if (retorno == 1) {
            return "Livro inserido com sucesso";
            
        }
        return "Erro ao inserir livro"; 
    }

    private static void ConsultarLivros (Statement statement) throws SQLException
    {
        ResultSet resultados = statement.executeQuery("SELECT * FROM livros"); 
        while (resultados.next()) {
            System.out.println("ID: " + resultados.getInt("id") + ", Título: " + resultados.getString("titulo") + ", Autor: " + resultados.getString("autor") + ", Ano de Publicação: " + resultados.getInt("ano_publicacao"));
            
        }
    }

        private static String AtualizarLivro (Statement statement, String titulo, String autor, int ano_publicacao, int id) throws SQLException
    {
        int retorno = statement.executeUpdate("UPDATE livros SET titulo='" + titulo + "', autor='" + autor + "', ano_publicacao=" + ano_publicacao + " WHERE id=" + id); 
            if (retorno == 1) {
                return "Livro atualizado com sucesso";
                
            }
            return "Erro ao atualizar livro"; 
    }
        
        private static String DeletarLivro (Statement statement, int id) throws SQLException
    {
        int retorno = statement.executeUpdate("DELETE FROM livros WHERE id=" + id);
        if (retorno == 1) {
            return "Livro deletado com sucesso";
            
        }
        return "Erro ao deletar livro"; 
    }
}
