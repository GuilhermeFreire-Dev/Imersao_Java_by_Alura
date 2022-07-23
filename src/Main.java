import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            String linguagensUrl = "http://localhost:8080/languages";

            var httpDataClient = new HttpDataClient();
            String json = httpDataClient.searchData(linguagensUrl);

            ContentExtractor contentExtractor = new LanguagesContentExtractor() ;
            List<Content> contents = contentExtractor.extractContent(json);

            // Gerar os stikers com os postres dos filmes
            var geradoraDeStikers = new StikersGenerator();

            for (Content content : contents){

                String title = content.getTitle();
                String imageUrl = content.getImageUrl();

                System.out.println(title + "\n");

                InputStream inputStream = new URL(imageUrl).openStream();
                geradoraDeStikers.create(inputStream, title, "Olá mundo!");
            }
        }catch (Exception exception){

            System.out.println("Ocorreu um erro inesperado.");
        }
    }
}
