package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Curso;

public class CursoDAO {
	
	public void insert(Curso curso, Connection conn) throws SQLException {
	    String sql = "INSERT INTO Curso (nomeCurso, cargaHoraria, idareaConcentracao) VALUES (?, ?, ?)";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, curso.getNomeCurso());
	    stmt.setInt(2, curso.getCargaHoraria());
	    stmt.setInt(3, curso.getAreaConcentracao());
	    stmt.executeUpdate();
	}

	public List<Curso> listarCursos(Connection conn) throws SQLException {
	    List<Curso> cursos = new ArrayList<>();
	    String sql = "SELECT * FROM Curso";
	    try (PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nomeCurso = rs.getString("nomeCurso");
	            int cargaHoraria = rs.getInt("cargaHoraria");
	            int areaConcentracao = rs.getInt("idareaConcentracao");
	            Curso curso = new Curso(id, nomeCurso, cargaHoraria, areaConcentracao);
	            cursos.add(curso);
	        }
	    }
	    return cursos;
	}
}

