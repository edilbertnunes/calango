package view;

import java.util.Scanner;

public class Menu {
	public static int showMenu(Scanner scanner) {
		int opcao = 0;

		while (opcao != 1 && opcao != 2 && opcao != 3) {
			System.out.println("======== Bem vindo ao controle de curso ========");
			System.out.println("=========== Selecione a opcao ==============");
			System.out.println("[1] Area");
			System.out.println("[2] Curso");
			System.out.println("[3] Listar áreas de concentração e cursos");
			System.out.println("==========================");
			opcao = scanner.nextInt();
			scanner.nextLine(); // buffer
			if (opcao != 1 && opcao != 2 && opcao != 3) {
				System.out.println(
						"Opção inválida. Por favor, selecione [1] inserir area ou [2] inserir curso [3] Listar áreas e cursos");
			}
		}

		return opcao;
	}
}