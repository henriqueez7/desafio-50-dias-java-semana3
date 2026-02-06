package br.com.desafiosemana3;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CepService cepService = new CepService();

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n=== Consulta de CEP ===");
            System.out.println("1 - Consultar CEP");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> consultarCep();
                case 0 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void consultarCep() {
        System.out.print("\nDigite o CEP (8 números): ");
        String cep = scanner.nextLine();

        Endereco endereco = cepService.buscarEndereco(cep);
        if (endereco != null) {
            System.out.println("\nEndereço encontrado:");
            System.out.println("CEP: " + endereco.getCep());
            System.out.println("Logradouro: " + endereco.getLogradouro());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getLocalidade());
            System.out.println("UF: " + endereco.getUf());
        }
    }
}
