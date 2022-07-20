import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {

    public static void imprimirDadosFilme(String titulo, float avaliacao){

        //  Imprimir dados do filme
        System.out.println("\u001b[1m" + titulo + "\u001b[0m");

        System.out.print(avaliacao + " ");

        for (int i = 0; i < avaliacao; i++){
            System.out.print("\u001b[33m \u001b[1m\u2b50");
        }
        System.out.println("\u001b[0m \n");
    }

    public static void main(String[] args) {

        try {
            // Realizar uma requisição HTTP
            String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";    //URL da API
            URI endereco = new URI(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            // Obter os dados de interesse tais como: nome, imagem, avaliação
            JsonParser parser = new JsonParser();
            List<Map<String, String>> filmes = parser.parse(body);

            // Gerar os stikers com os postres dos filmes
            var geradoraDeStikers = new GeradoraDeStikers();

            for (Map<String, String> filme : filmes){

                // Obter dados do filme
                String titulo = filme.get("title");
                String nomeDoArquivo = titulo + ".png";
                String imagem = filme.get("image");
                float rating = Float.parseFloat(filme.get(",\"imDbRating"));

                // Imprimir dados do filme
                imprimirDadosFilme(titulo, rating);

                // Gerar os stikers
                InputStream inputStream = new URL(imagem).openStream();
                geradoraDeStikers.criar(inputStream, nomeDoArquivo);
            }
        }catch (Exception exception){

            System.out.println("Ocorreu um erro inesperado.");
        }
    }
}
