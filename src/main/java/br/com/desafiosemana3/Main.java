package br.com.desafiosemana3;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n=== Consulta de CEP ===");
            System.out.println("1 - Consultar CEP");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    consultarCep();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void consultarCep() {
        System.out.print("\nDigite o CEP (8 números): ");
        String cep = scanner.nextLine();

        if (!cep.matches("\\d{8}")) {
            System.out.println("CEP inválido. Digite exatamente 8 números.");
            return;
        }

        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro ao acessar a API. Código: " + response.statusCode());
                return;
            }

            Endereco endereco = gson.fromJson(response.body(), Endereco.class);

            if (Boolean.TRUE.equals(endereco.getErro())) {
                System.out.println("CEP não encontrado.");
                return;
            }

            exibirEndereco(endereco);

        } catch (Exception e) {
            System.out.println("Erro de conexão. Tente novamente.");
        }
    }

    private static void exibirEndereco(Endereco endereco) {
        System.out.println("\nEndereço encontrado:");
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Logradouro: " + endereco.getLogradouro());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("Cidade: " + endereco.getLocalidade());
        System.out.println("UF: " + endereco.getUf());
    }
}
