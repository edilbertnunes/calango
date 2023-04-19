package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AreaConcentracao;

public class AreaConcentracaoDAO {

    public void insert(Connection conn, AreaConcentracao area) throws SQLException {
        String sql = "INSERT INTO AreaConcentracao (nome) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, area.getNome());
            stmt.executeUpdate();
        }
    }

    public List<AreaConcentracao> listarAreasConcentracao(Connection conn) throws SQLException {
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
}
