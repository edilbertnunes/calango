package dto;
 import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ExemploJComboBox extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<AreaConcentracao> comboBox;
    private JComboBox<Curso> comboBoxCursos;

    public ExemploJComboBox() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(new JLabel("Selecione uma área de concentração:"));
        comboBox = new JComboBox<AreaConcentracao>();
        add(comboBox);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        carregarAreasConcentracao();
        
        add(new JLabel("Selecione um curso:"));
        comboBoxCursos = new JComboBox<Curso>();
        add(comboBoxCursos);
        carregarCursos();
    }

    private void carregarAreasConcentracao() {
        List<AreaConcentracao> areasConcentracao = getAreasConcentracao();

        SwingUtilities.invokeLater(() -> {
            for (AreaConcentracao area : areasConcentracao) {
                comboBox.addItem(area);
            }
        });
    }
    
    private void carregarCursos() {
        List<Curso> cursos = getCursos();

        SwingUtilities.invokeLater(() -> {
            for (Curso curso : cursos) {
                comboBoxCursos.addItem(curso);
            }
        });
    }
    
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.111:1433;databaseName=calango;encrypt=false;", "sa", "Acesso123")) {
            String sql = "SELECT * FROM Curso";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        int id = result.getInt("idcurso");
                        String nome = result.getString("nomeCurso");
                        int cargaHoraria = result.getInt("cargaHoraria");
                        int idAreaConcentracao = result.getInt("idAreaConcentracao");
                        Curso curso = new Curso(id, nome, cargaHoraria, idAreaConcentracao);
                        cursos.add(curso);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cursos: " + e.getMessage());
        }
        return cursos;
    }
    
    public static List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.111:1433;databaseName=calango;encrypt=false;", "sa", "Acesso123")) {
            cursos = Curso.listarCursos(conn);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cursos: " + e.getMessage());
        }
        return cursos;
    }

    public List<AreaConcentracao> getAreasConcentracao() {
        List<AreaConcentracao> areas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.111:1433;databaseName=calango;encrypt=false;", "sa", "Acesso123")) {
            String sql = "SELECT * FROM AreaConcentracao";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        int id = result.getInt("id");
                        String nome = result.getString("nome");
                        AreaConcentracao area = new AreaConcentracao(id, nome);
                        areas.add(area);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar áreas de concentração: " + e.getMessage());
        }
        return areas;
    }

    public void listarCursos(AreaConcentracao area) {
        List<Curso> cursos = getCursosPorArea(area);

        System.out.println("Cursos da área de concentração " + area.getNome() + ":");
        for (Curso curso : cursos) {
            System.out.println("Nome: " + curso.getNomeCurso() + " | Carga horária: " + curso.getCargaHoraria());
        }
    }

    private List<Curso> getCursosPorArea(AreaConcentracao area) {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.111:1433;databaseName=calango;encrypt=false;", "sa", "Acesso123")) {
            String sql = "SELECT * FROM Curso WHERE idareaConcentracao = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, area.getId());
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        String nomeCurso = result.getString("nomeCurso");
                        int cargaHoraria = result.getInt("cargaHoraria");
                        int idAreaConcentracao = result.getInt("idareaConcentracao");
                        Curso curso = new Curso(nomeCurso, cargaHoraria, idAreaConcentracao);
                        cursos.add(curso);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cursos: " + e.getMessage());
        }
        return cursos;
    }
}
