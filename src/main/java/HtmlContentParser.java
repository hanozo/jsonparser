import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HtmlContentParser implements ContentParser {

    public String parse(String content) {
        try (InputStream stream = new ByteArrayInputStream(content.getBytes())) {
            BodyContentHandler handler = new BodyContentHandler();
            HtmlParser parser = new HtmlParser();
            Metadata metadata = new Metadata();
            parser.parse(stream, handler, metadata, new ParseContext());
            return handler.toString();
        } catch (IOException | TikaException | SAXException e) {
            e.printStackTrace();
            return "";
        }
    }
}
