package dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaConcentracao {
    private int id;
    private String nome;

    public AreaConcentracao(String nome) {
        this.nome = nome;
    }
    
    public AreaConcentracao(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public void insert(Connection conn) throws SQLException {
        String sql = "INSERT INTO AreaConcentracao (nome) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.executeUpdate();
    }
	
    public static List<AreaConcentracao> listarAreasConcentracao(Connection conn) throws SQLException {
        List<AreaConcentracao> areas = new ArrayList<>();
        String sql = "SELECT * FROM AreaConcentracao";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                AreaConcentracao area = new AreaConcentracao(id, nome);
                areas.add(area);
            }
        }
        return areas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
