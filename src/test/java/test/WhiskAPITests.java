package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class WhiskAPITests {

    String id, token = "4PkSbMXhfI4xMmdWy9xpTLLHkvImTGkY84zW9xbbdR8Dm4Q8FH0BL3Sdy73mdkSo";
    String body = "{\n" +
            "  \"name\": \"string\",\n" +
            "  \"primary\": false\n" +
            "}\n";

    @Test(description = "Create/Get shopping list.")
    public void TestCase1() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                    .contentType(ContentType.JSON)
                    .post("https://api.whisk-dev.com/list/v2");
        id = response.jsonPath().get("list.id");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                    .get("https://api.whisk-dev.com/list/v2/{id}", id)
                .then()
                    .body("list.id", equalTo(id), "content", anEmptyMap());

    }

    @Test(description = "Create/Delete/Get shopping list.")
    public void TestCase2() {
        String responseMessage = "{\"code\":\"shoppingList.notFound\"}";
        Response response1 = given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                    .contentType(ContentType.JSON)
                    .post("https://api.whisk-dev.com/list/v2");
        String id = response1.jsonPath().get("list.id");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                    .delete("https://api.whisk-dev.com/list/v2/{id}", id);

        Response response2 = given()
                .header("Authorization", "Bearer " + token)
                .when()
                    .get("https://api.whisk-dev.com/list/v2/{id}", id)
                .then()
                    .statusCode(400)//TODO statusCode is 200 in the test task
                    .extract().response();
        Assert.assertEquals(response2.asString().trim(),responseMessage, "Deleted shopping list was found!");
    }

}
