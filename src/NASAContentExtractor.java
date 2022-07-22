import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NASAContentExtractor implements ContentExtractor{

    @Override
    public List<Content> extractContent(String json) {

        var parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);
        
        List<Content> contents = new ArrayList<>();

        for (Map<String, String> attribute: attributesList) {

            // Configurar para API da NASA
            String title = attribute.get("key");
            String imageUrl = attribute.get("key");

            var content = new Content(title, imageUrl);

            contents.add(content);
        }

        return contents;
    }
}
