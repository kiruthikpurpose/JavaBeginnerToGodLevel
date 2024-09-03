import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebScraper {
    public static void main(String[] args) {
        String url = "http://example.com";

        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("h1");

            for (org.jsoup.nodes.Element element : elements) {
                System.out.println(element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
