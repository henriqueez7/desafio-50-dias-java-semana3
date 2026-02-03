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

        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(response.body(), Endereco.class);

            System.out.println("\nEndereço encontrado:");
            System.out.println("CEP: " + endereco.getCep());
            System.out.println("Logradouro: " + endereco.getLogradouro());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getLocalidade());
            System.out.println("UF: " + endereco.getUf());

        } catch (Exception e) {
            System.out.println("Erro ao consultar o CEP.");
        }

        scanner.close();
    }
}
