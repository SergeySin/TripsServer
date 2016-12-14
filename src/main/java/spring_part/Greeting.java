package spring_part;

public class Greeting {

    private final long id;
    private final String content;
    private final String comment;

    public Greeting(long id, String content, String comment) {
        this.id = id;
        this.content = content;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }



}
