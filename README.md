# TableService
This is a service that accepts JSON object and responds with HTML code to create a table.</br>

## Usage
Users can send POST request with JSON into payload (XmlHttpRequest in my example), and with this service they obtain HTML code to inject in their pages.</br>
1. Using POST method and this JSON
```js
{
    header: ['a', 'b', 'c'], 
    body: { 
        1: ['value A1', 1, 'value C1'],
        2: ['value A2', 2, 'value C2', 65, 899],
        3: ['value A3', 3, 'value C3', 65],
        4: ['value A4', 4, 'value C4']
    } 
};
```
2. Service response will be:
```js
{
    id: 5,
    content: "<table> <tr> <th> a </th><th> b </th><th> c </th> </tr><tr> <td> valore di A1 </td><td> Valore di C1 </td> </tr><tr> <td> valore di A2 </td><td> 2 </td><td> Valore di C2 </td><td> 65 </td><td> 899 </td> </tr><tr> <td> valore di A3 </td><td> 3 </td><td> Valore di C3 </td><td> 65 </td> </tr><tr> <td> valore di A4 </td><td> 4 </td><td> Valore di C4 </td> </tr> </table>",
    tips: "Body line 1 have 2 columns.\nBody line 2 have 5 columns.\nBody line 3 have 4 columns.\n"
}
```
3. This is tested with Postman as you can see below.</br>
![Pastman example image](Postman%20example.png "Pastman example")

## Development
* Please read Spring GUI https://spring.io/guides/gs/rest-service/
* Please read also https://www.baeldung.com/spring-boot-json & https://www.baeldung.com/java-org-json

As you can see in source code there are 2 classes, 1 controller and Spring main to run application.</br>
* 'application.properties' are necessary to change port from 8080 (Tomcat default one) to 8081.
* On my server is already running Apache Tomcat so I decided to run my test page ([tableservice.html](http://www.cm-innovationlab.it:8080/tableservice.html)) with it.
* To run the service on the 8081 port, is fondamental to use @CrossOrigin annotation of Spring-boot. This allow CORS origin to my service from "www.cm-innovationlab.it:8080".
* To create JAR with VS Code, simply use "package" function in maven menù after "Reload" Maven project. Alternativelly `"mvnw.cmd" package -f "pom.xml"` from \TableService\table_service.

## Service Installation on Server
This service needs Java JDK 20

* Save .jar file on your server
* Than run `java -jar [JAR file]`

## Test
Is possible to verify this service with my example:</br>
[http://www.cm-innovationlab.it:8080/tableservice.html](http://www.cm-innovationlab.it:8080/tableservice.html)