package it.cm_innovationlab.table_service;

//With JDK 20, best solution is RECORD.
//Records are immutable data classes that require only the type and name of fields.
//public record HtmlTable (long id, String content, String tips){ }

public class HtmlTable{
    private long id;
    private String content;
    private String tips;

    public HtmlTable (long id, String content, String tips){ 
        this.id = id;
        this.content = content;
        this.tips = tips;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTips() {
        return tips;
    }
}