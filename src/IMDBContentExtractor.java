import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDBContentExtractor implements ContentExtractor{

    @Override
    public List<Content> extractContent(String json) {

        var parser = new JsonParser();
        List<Map<String, String >> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        for (Map<String, String> attribute: attributesList) {

            String title = attribute.get("title");
            String imageUrl = attribute.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var content = new Content(title, imageUrl);

            contents.add(content);
        }

        return contents;
    }
}
