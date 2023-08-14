package it.cm_innovationlab.table_service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class StringHtmlTableFromJSON{

    private long id;
    private JSONArray header;
    private JSONObject body;
    private String tips = "";

    private final int columnNumber;

    private static final String tableTemplate = "<table> %s </table>";
    private static final String rowTemplate = "<tr> %s </tr>";
    private static final String headerTemplate = "<th> %s </th>";
    private static final String cellTemplate = "<td> %s </td>";
    
    public StringHtmlTableFromJSON (long id, JSONArray header, JSONObject body){
        this.id = id;
        this.header = header;
        this.body = body;
        this.columnNumber = header.length();
    }

    public long getId() {
        return id;
    }

    private List<String> convertJsonArrayToList(JSONArray input) {
        List<String> output = new ArrayList<>();
        try {
            input.forEach(item -> {
                output.add(item.toString());
            });
        } catch (Exception e) {
            this.tips += e.toString() + "\n";
        }
        return output;
    }

    private List<String> getHeaderList() {
        return convertJsonArrayToList(this.header);
    }

    private List<List<String>> getBodyLists() {
        List<List<String>> output = new ArrayList<>();
        try {
            this.body.keys().forEachRemaining(key -> {
                JSONArray value = (JSONArray) this.body.get(key);
                if (value.length() != columnNumber){
                    this.tips += "Body line " + key + " have " + value.length() + " columns.\n";
                }
                output.add(convertJsonArrayToList(value));
            });
        } catch (Exception e) {
            this.tips += e.toString() + "\n";
        }
        return output;
    }

    public String getTableContent() {

        String headerOutput = "";
        String bodyOutput = "";

        List<String> headTemp = getHeaderList();
        for (int index = 0; index < headTemp.size(); index++){
            headerOutput += String.format(headerTemplate, headTemp.get(index).toString());
        }
        headerOutput = String.format(rowTemplate, headerOutput);

        List<List<String>> bodyTemp = getBodyLists();
        for (int rowNumber = 0; rowNumber < bodyTemp.size(); rowNumber++){
            String bodySingleLine = "";
            List<String> tempRow = bodyTemp.get(rowNumber);
            for (int index = 0; index < tempRow.size(); index++){
                bodySingleLine += String.format(cellTemplate, tempRow.get(index).toString());
            }
            bodyOutput += String.format(rowTemplate, bodySingleLine);
        }

        return String.format(tableTemplate, headerOutput + bodyOutput);
    }

    public String getTips() {
        return this.tips;
    }
}