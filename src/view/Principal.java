package view;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AreaConcentracaoDAO;
import dao.CursoDAO;
import dto.AreaConcentracao;
import dto.Curso;
import dto.ExemploJComboBox;

public class Principal {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao = Menu.showMenu(scanner);

		switch (opcao) {
		case 1:
			System.out.print("Digite o nome da área de concentração: ");
			String nome = scanner.nextLine();

			try (Connection conn = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.1.111:1433;databaseName=calango;encrypt=false;", "sa", "Acesso123")) {
				AreaConcentracao areaConcentracao = new AreaConcentracao(nome);
				AreaConcentracaoDAO areaConcentracaoDAO = new AreaConcentracaoDAO();
				areaConcentracaoDAO.insert(conn, areaConcentracao);
				System.out.println("Registro inserido com sucesso!");
			} catch (SQLException e) {
				System.out.println("Erro ao inserir registro: " + e.getMessage());
			}
			break;

		case 2:
			System.out.print("Cadastrar novo curso");

			JTextField nomeCursoField = new JTextField(20);
			JTextField cargaHorariaField = new JTextField(20);
			JTextField idAreaConcentracaoField = new JTextField(20);

			JPanel myPanel = new JPanel();
			myPanel.setLayout(new GridLayout(3, 2));
			myPanel.add(new JLabel("Nome do Curso:"));
			myPanel.add(nomeCursoField);
			myPanel.add(new JLabel("Carga Horária:"));
			myPanel.add(cargaHorariaField);
			myPanel.add(new JLabel("ID da Área de Concentração:"));
			myPanel.add(idAreaConcentracaoField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, "Cadastro de Curso",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String nomeCurso = nomeCursoField.getText();
				String cargaHorariaStr = cargaHorariaField.getText();
				String idAreaConcentracaoStr = idAreaConcentracaoField.getText();

				// Verifica se todos os campos foram preenchidos
				if (nomeCurso.isEmpty() || cargaHorariaStr.isEmpty() || idAreaConcentracaoStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
					break;
				}

				// Converte a carga horária e o ID da área de concentração para int
				int cargaHoraria;
				int idAreaConcentracao;
				try {
					cargaHoraria = Integer.parseInt(cargaHorariaStr);
					idAreaConcentracao = Integer.parseInt(idAreaConcentracaoStr);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Carga horária e ID da área de concentração devem ser números inteiros");
					break;
				}

				// Cria um novo objeto Curso com os valores digitados pelo usuário
				Curso novoCurso = new Curso(nomeCurso, cargaHoraria, idAreaConcentracao);

				// Insere o novo curso no banco de dados
				try (Connection conn = DriverManager.getConnection(
						"jdbc:sqlserver://192.168.1.111:1433;databaseName=calango;encrypt=false;", "sa", "Acesso123")) {
					CursoDAO cursoDAO = new CursoDAO();
					cursoDAO.insert(novoCurso, conn);
					System.out.println("Curso cadastrado com sucesso!");
				} catch (SQLException e) {
					System.out.println("Erro ao cadastrar curso: " + e.getMessage());
				}
			}

			break;

		case 3:
			new ExemploJComboBox();
			break;
		default:
			System.out.println("Opção inválida!");
			break;
		}
		scanner.close();
	}
}
