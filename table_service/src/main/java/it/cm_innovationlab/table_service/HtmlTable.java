package it.cm_innovationlab.table_service;

//Records are immutable data classes that require only the type and name of fields.
public record HtmlTable (long id, String content, String tips){ }