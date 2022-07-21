import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            // URL da API do IMDB
            String imdbUrl = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";

            var httpDataClient = new HttpDataClient();
            String json = httpDataClient.searchData(imdbUrl);

            ContentExtractor contentExtractor = new IMDBContentExtractor();
            List<Content> contents = contentExtractor.extractContent(json);

            // Gerar os stikers com os postres dos filmes
            var geradoraDeStikers = new StikersGenerator();

            for (Content content : contents){

                String title = content.getTitle();
                String imageUrl = content.getImageUrl();

                System.out.println(title + "\n");

                InputStream inputStream = new URL(imageUrl).openStream();
                geradoraDeStikers.create(inputStream, title);
            }
        }catch (Exception exception){

            System.out.println("Ocorreu um erro inesperado.");
        }
    }
}
