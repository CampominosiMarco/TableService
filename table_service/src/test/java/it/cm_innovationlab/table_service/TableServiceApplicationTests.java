package it.cm_innovationlab.table_service;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TableServiceApplicationTests {

	private final AtomicLong counter = new AtomicLong();

	private JSONArray jsonHeader = new JSONArray();
	private JSONObject jsonBody = new JSONObject();

	@Test
	void stringHtmlTableFromJSONTest() {

		//Create JSON
		createJsonObjects();

		//Create StringHtmlTableFromJSON Object
		StringHtmlTableFromJSON testInput = new StringHtmlTableFromJSON(
												counter.incrementAndGet(),
												jsonHeader,
												jsonBody);

		//Test StringHtmlTableFromJSON methods
		assertThat(testInput.getId() == 5L).isTrue();
		assertThat(testInput.getId() != 3L).isFalse();






		if (testInput.getId() != 1L){
			System.err.println("StringHtmlTableFromJSON: ID is not correct");
		}else{
			System.out.println("StringHtmlTableFromJSON: ID ok \n[" + testInput.getId() + "]\n");
		}





		if (!testInput.getTableContent().equals(
			"<table> <tr> <th> a </th><th> b </th><th> c </th> </tr>" +
			"<tr> <td> valore di A1 </td><td> Valore di C1 </td> </tr>" +
			"<tr> <td> valore di A2 </td><td> 2 </td><td> Valore di C2 </td><td> 65 </td><td> 899 </td> </tr>" +
			"<tr> <td> valore di A3 </td><td> 3 </td><td> Valore di C3 </td><td> 65 </td> </tr>" +
			"<tr> <td> valore di A4 </td><td> 4 </td><td> Valore di C4 </td> </tr> </table>")){
			System.err.println("StringHtmlTableFromJSON: CONTENT is not correct");
		}else{
			System.out.println("StringHtmlTableFromJSON: CONTENT ok \n[" + testInput.getTableContent() + "]\n");
		}

		if (!testInput.getTips().equals("Body line 1 have 2 columns.\nBody line 2 have 5 columns.\nBody line 3 have 4 columns.\n")){
			System.err.println("StringHtmlTableFromJSON: TIPS is not correct");
		}else{
			System.out.println("StringHtmlTableFromJSON: TIPS ok \n[" + testInput.getTips() + "]\n");
		}
	}

	private void createJsonObjects() throws JSONException{
		jsonHeader.put("a");
		jsonHeader.put("b");
		jsonHeader.put("c");

		JSONArray body1 = new JSONArray();
		body1.put("valore di A1");
		body1.put("Valore di C1");

		JSONArray body2 = new JSONArray();
		body2.put("valore di A2");
		body2.put(2);
		body2.put("Valore di C2");
		body2.put(65);
		body2.put(899);
		
		JSONArray body3 = new JSONArray();
		body3.put("valore di A3");
		body3.put(3);
		body3.put("Valore di C3");
		body3.put(65);
		
		JSONArray body4 = new JSONArray();
		body4.put("valore di A4");
		body4.put(4);
		body4.put("Valore di C4");

		jsonBody.put("1", body1);
		jsonBody.put("2", body2);
		jsonBody.put("3", body3);
		jsonBody.put("4", body4);
	}
}
