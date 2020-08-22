package test;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.equalTo;


public class WhiskAPITests {

    PropertyManager props = new PropertyManager();
    String token = props.get("token");
    String id;
    JSONObject jsonObj = new JSONObject()
            .put("name", "string")
            .put("\nprimary", "false");

    @Test(description = "Create/Get shopping list.")
    public void testCase1() {
        Response response1 = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post("https://api.whisk-dev.com/list/v2");
        id = response1.jsonPath().get("list.id");

        Response response2 = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.whisk-dev.com/list/v2/{id}", id)
                .then()
                .body("list.id", equalTo(id), "content", anEmptyMap())
                .extract().response();
        System.out.println(response2.body().asString());
    }

    @Test(description = "Create/Delete/Get shopping list.")
    public void testCase2() {
        String responseMessage = "{\"code\":\"shoppingList.notFound\"}";
        Response response1 = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
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
                .statusCode(400)//TODO Expected status code is 400 BUT 200 in the test task
                .extract().response();
        Assert.assertEquals(response2.asString().trim(),responseMessage, "Deleted shopping list was found!");
    }

}
