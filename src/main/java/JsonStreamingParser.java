import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

public class JsonStreamingParser {

    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {

        System.out.printf("Parsing file: %s\n", args[0]);

        FileInputStream stream = new FileInputStream(args[0]);

        Path file = Paths.get(args[1]);

        String delimiter = args[2];

        ContentParser parser = new HtmlContentParser();

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

            readJsonStream(stream, rec -> {
                try {
                    writer.write(rec.toString(delimiter, parser));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void readJsonStream(InputStream in, Consumer<Record> consumer) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
//        reader.setLenient(true);
            reader.beginArray();
            while (reader.hasNext()) {
                consumer.accept(gson.fromJson(reader, Record.class));
            }
            reader.endArray();
        }
    }
}
