package br.com.desafiosemana3;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um CEP para consulta: ");
        String cep = scanner.nextLine();

        if (!cep.matches("\\d{8}")) {
            System.out.println("CEP inválido. Digite exatamente 8 números.");
            return;
        }

        try {
            HttpClient client = HttpClient.newHttpClient();
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

            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(response.body(), Endereco.class);

            if (Boolean.TRUE.equals(endereco.getErro())) {
                System.out.println("CEP não encontrado.");
                return;
            }

            System.out.println("\nEndereço encontrado:");
            System.out.println("CEP: " + endereco.getCep());
            System.out.println("Logradouro: " + endereco.getLogradouro());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getLocalidade());
            System.out.println("UF: " + endereco.getUf());

        } catch (Exception e) {
            System.out.println("Erro de conexão ou problema inesperado.");
        }

        scanner.close();
    }
}
