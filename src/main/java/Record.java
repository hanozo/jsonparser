public class Record {

    private String guid;
    private String title;
    private String content;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String toString(String delimiter, ContentParser parser) {
        return String.format("%s%s%s%s%s\n", guid, delimiter, title, delimiter, parser.parse(content));

    }
}
