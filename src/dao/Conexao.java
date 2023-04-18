package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:sqlserver://192.168.1.111:1433;databaseName=calango;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASSWORD = "Acesso";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

/* 
// Conexao SQL server - OK
// Criar insert via console nas duas tabelas - OK
// select via swing - dentro de um component JCombobox - mostrar área de concentração - OK
   Dentro do JCombobox retornar o nome e id da area de concentracao - OK

  no Jform criar as caixas de texto para cadastrar os cursos
  Ao cadastrar o curso no banco de dados, deverá considerar também o id que veio do JCombobox


  /// criar camadas dao, view, dto

*/