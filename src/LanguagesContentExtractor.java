import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LanguagesContentExtractor implements ContentExtractor{

    @Override
    public List<Content> extractContent(String json) {

        var parser = new JsonParser();

        List<Map<String, String>> attributesList = parser.parse(json);
        
        List<Content> contents = new ArrayList<>();

        for (Map<String, String> attribute : attributesList) {

            String title = attribute.get("title");
            String image = attribute.get("image");

            Content content = new Content(title, image);

            contents.add(content);
        }

        return contents;
    }
}
