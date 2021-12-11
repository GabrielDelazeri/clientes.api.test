package localhost;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

class ClientsTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/clients";
    }

    @Test
    void testListaClientes_TodosClientesSaoListados() {
        RestAssured.given() //Dado - Given
                .log().all() //loga toda a request - solicitação (entrada) - mostrará o cabeçalho da solicitação
                .when() //Quando - When
                .get("http://localhost:8080/clients") //Faz a solicitação em si, método/verbo GET passando como parâmetro a URL/URI a ser acessada
                .then() //Então
                .log().all()  // loga toda a response - resposta
                .statusCode(200);  //verifica se o resultado da request é HTTP 200 OK
    }

    @Test
    void testListaUnicoCliente_ClienteEhListadoComTodasAsInformacoes() {

        int id = 1;

        RestAssured.given() //Dado - Given
                .log().all() //loga toda a request - solicitação (entrada) - mostrará o cabeçalho da solicitação
                .when() //Quando - When - REQUEST
                .get("/" + id) //Faz a solicitação em si, método/verbo GET passando como parâmetro a URL/URI a ser acessada
                .then() //Então - RESPONSE
                .log().all()  // loga toda a response - resposta
                .statusCode(200)  //verifica se o resultado da request é HTTP 200 OK
                .body("id", is(id));

    }

    @Test
    void testIncluiCliente_ClienteEhIncluidoComSucesso() {

        String email = "fulana@teste.com";
        String name = "Fulana";

        RestAssured.given() //Dado - Given - Pré-condição
                .body("{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"name\": \"" + name + "\" \n" +
                        "}")
                .log().all() //loga toda a request - solicitação (entrada) - mostrará o cabeçalho da solicitação
                .contentType(ContentType.JSON)
                .when() //Quando - When - REQUEST
                .post() //Faz a solicitação em si, método/verbo GET passando como parâmetro a URL/URI a ser acessada
                .then() //Então - RESPONSE
                .log().all()  // loga toda a response - resposta
                .statusCode(201);  //verifica se o resultado da request é HTTP 200 OK
    }

    //To do - Fazer testes para os verbos DELET e PUT

}