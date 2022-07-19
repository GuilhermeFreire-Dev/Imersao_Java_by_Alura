import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            // Realizar uma requisição HTTP

            String url = "https://api.mocki.io/v2/549a5d8b";    //URL da API
            URI endereco = new URI(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            // Obter os dados de interesse tais como: nome, imagem, avaliação

            JsonParser parser = new JsonParser();
            List<Map<String, String>> filmes = parser.parse(body);

            // Exibir os resultados

            for (Map<String, String> filme : filmes){
                System.out.println("\u001b[1m" + filme.get("title") + "\u001b[0m");

                float rating = Float.parseFloat(filme.get(",\"imDbRating"));
                System.out.print(rating + " ");

                for (int i = 0; i < rating; i++){
                    System.out.print("\u001b[33m \u001b[1m\u2b50");
                }
                System.out.println("\u001b[0m \n");
            }

        }catch (Exception exception){
            System.out.println("Ocorreu um erro inesperado.");
        }
    }
}
