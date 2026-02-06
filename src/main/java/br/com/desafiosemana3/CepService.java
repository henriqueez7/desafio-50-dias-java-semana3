package br.com.desafiosemana3;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CepService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public Endereco buscarEndereco(String cep) {

        if (!cep.matches("\\d{8}")) {
            System.out.println("CEP inválido. Digite exatamente 8 números.");
            return null;
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
                return null;
            }

            Endereco endereco = gson.fromJson(response.body(), Endereco.class);

            if (Boolean.TRUE.equals(endereco.getErro())) {
                System.out.println("CEP não encontrado.");
                return null;
            }

            return endereco;

        } catch (Exception e) {
            System.out.println("Erro de conexão. Tente novamente.");
            return null;
        }
    }
}
