package it.cm_innovationlab.table_service;

import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //This annotation create a controller where every method returns a domain object instead of a view.
public class TableController {

	private final AtomicLong counter = new AtomicLong();

	@PostMapping("/table")
    public HtmlTable htmlTable(@RequestBody String inputJSON) {
        JSONObject jsonObjInput = new JSONObject(inputJSON);

        if (jsonObjInput.has("header") && jsonObjInput.has("body")){
            String error = "";
            try {
                StringHtmlTableFromJSON MyTable = new StringHtmlTableFromJSON(
                                                    counter.incrementAndGet(),
                                                    (JSONArray) jsonObjInput.get("header"),
                                                    (JSONObject) jsonObjInput.get("body"));

                return new HtmlTable(MyTable.getId(), MyTable.getTableContent(), MyTable.getTips());
            } catch (Exception e) {
                error = e.toString();
            }
            return new HtmlTable(0, "", error);
        }
        return new HtmlTable(0, "", "BAD JSON PAYLOAD: check 'header' and 'body'.");
    }
}