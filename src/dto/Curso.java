package dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Curso {
	
	private int id;
	private String nomeCurso;
	private int cargaHoraria;
	private int areaConcentracao;
	
	public Curso () {
		
	}
	
	public Curso(String nomeCurso, int cargaHoraria, int areaConcentracao) {
		this.nomeCurso = nomeCurso;
		this.cargaHoraria = cargaHoraria;
		this.areaConcentracao = areaConcentracao;
	}
	
	public Curso(int id, String nomeCurso, int cargaHoraria, int areaConcentracao) {
		super();
		this.id = id;
		this.nomeCurso = nomeCurso;
		this.cargaHoraria = cargaHoraria;
		this.areaConcentracao = areaConcentracao;
	}

	public void insert(Connection conn) throws SQLException {
	    String sql = "INSERT INTO Curso (nomeCurso, cargaHoraria, idareaConcentracao) VALUES (?, ?, ?)";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, nomeCurso);
	    stmt.setInt(2, cargaHoraria);
	    stmt.setInt(3, areaConcentracao);
	    stmt.executeUpdate();
	}

	public static List<Curso> listarCursos(Connection conn) throws SQLException {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getAreConcentracao() {
		return areaConcentracao;
	}

	public void setAreConcentracao(int areaConcentracao) {
		this.areaConcentracao = areaConcentracao;
	}
	
    @Override
    public String toString() {
        return id + " - " + nomeCurso;
    }

}
