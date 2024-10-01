package br.com.lgr.java21;

import br.com.lgr.java21.domain.record.UserRecord;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserControllerTest {

    @ConfigProperty(name="quarkus.oidc.auth-server-url")
    String serverUrl;

    @ConfigProperty(name="quarkus.oidc.client-id")
    String clientId;

    @ConfigProperty(name="quarkus.oidc.credentials.secret")
    String secret;

    @ConfigProperty(name="oidc-user")
    String user;

    @ConfigProperty(name="oidc-user-password")
    String password;

    @Test
    public void whenFindAllUsers_thenAllUsersShoulBeFound() {
        given()
                .auth()
                .oauth2(findAccessToken())
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", is(2), "username", hasItems("usuario01","usuario02"));
    }

    @Test
    public void whenFindAllUsersProjection_thenAllUsersShoulBeFound() {
        given()
                .auth()
                .oauth2(findAccessToken())
                .when()
                .get("/users/projection")
                .then()
                .statusCode(200)
                .body("size()", is(2), "username", hasItems("usuario01","usuario02"));
    }

    @Test
    public void whenFindAllUsersHQL_thenAllUsersShoulBeFound() {
        given()
                .auth()
                .oauth2(findAccessToken())
                .when()
                .get("/users/hql")
                .then()
                .statusCode(200)
                .body("size()", is(2), "username", hasItems("usuario01","usuario02"));
    }

    @Test
    public void whenFindUserById_thenUserShoulBeFound() {
        String id = "5d08275c-3cc0-4c85-97a2-1f2776e93aa4";
        given()
                .auth()
                .oauth2(findAccessToken())
                .when()
                .get("/users/" + id)
                .then()
                .statusCode(200)
                .body("username", is("usuario01"), "userId", is(id));
    }

    @Test
    public void whenFindUserById_thenUserShoulNotBeFound() {
        String id = "7f0455cc-b97f-4f60-abdb-8649576bee17";
        given()
                .auth()
                .oauth2(findAccessToken())
                .when()
                .get("/users/" + id)
                .then()
                .statusCode(404);
    }

    @Test
    public void whenCreateUser_thenUserShouldBeFound() {
        UserRecord userRecord = new UserRecord("usuario01","grupo01");
        given()
                .auth()
                .oauth2(findAccessToken())
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("username", is("usuario01"));

        userRecord = new UserRecord("usuario02","grupo02");
        given()
                .auth()
                .oauth2(findAccessToken())
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/users")
                .then().statusCode(201)
                .body("username", is("usuario02"));

        userRecord = new UserRecord("usuario03",null);
        given()
                .auth()
                .oauth2(findAccessToken())
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/users")
                .then().statusCode(201)
                .body("username", is("usuario03"));

        userRecord = new UserRecord("usuario04","");
        given()
                .auth()
                .oauth2(findAccessToken())
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/users")
                .then().statusCode(201)
                .body("username", is("usuario04"));

    }

    @Test
    public void whenUpdateUser_thenUserShouldBeFound() {
        String id = "5d08275c-3cc0-4c85-97a2-1f2776e93aa4";
        UserRecord userRecord = new UserRecord("usuario01","grupo01");
        given()
                .auth()
                .oauth2(findAccessToken())
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .put("/users/" + id)
                .then()
                .statusCode(200)
                .body("username", is("usuario01"));

        userRecord = new UserRecord("usuario01",null);
        given()
                .auth()
                .oauth2(findAccessToken())
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .put("/users/" + id)
                .then()
                .statusCode(200)
                .body("username", is("usuario01"));

    }

    @Test
    public void whenDeleteUser_thenNotContentShouldBeFound() {
        String id = "5d08275c-3cc0-4c85-97a2-1f2776e93aa4";
        given()
                .auth()
                .oauth2(findAccessToken())
                .when()
                .delete("/users/" + id)
                .then()
                .statusCode(204);
    }

    /*String findAccessToken() {
        return given()
                .auth()
                .preemptive()
                .basic(clientId, secret)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .baseUri(serverUrl)
                .body("username=admin&password=5354231Lgr#1&grant_type=password")
                .post("/protocol/openid-connect/token")
                .then().extract().response().jsonPath().getString("access_token");
    }*/
/*
    String findAccessToken() {
        return given()
                .auth()
                .preemptive()
                .basic(clientId, secret)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username","admin")
                .formParam("password", "5354231Lgr#1")
                .formParam("grant_type", "password")
                //.post(serverUrl+ "/protocol/openid-connect/token")
                .baseUri(serverUrl)
                .when()
                .post("/protocol/openid-connect/token")
                .then().extract().response().jsonPath().getString("access_token");
    }*/

    String findAccessToken() {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username",user)
                .formParam("password", password)
                .formParam("grant_type", "password")
                .formParam("client_id", clientId)
                .formParam("client_secret", secret)
                //.post(serverUrl+ "/protocol/openid-connect/token")
                .basePath("")
                .port(80)
                .baseUri(serverUrl)
                .when()
                .post("protocol/openid-connect/token")
                .then().extract().response().jsonPath().getString("access_token");
    }

}
