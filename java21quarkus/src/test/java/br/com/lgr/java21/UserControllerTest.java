package br.com.lgr.java21;

import br.com.lgr.java21.domain.record.UserRecord;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserControllerTest {

    @Test
    public void whenFindAllUsers_thenAllUsersShoulBeFound() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", is(2), "username", hasItems("usuario01","usuario02"));
    }

    @Test
    public void whenFindAllUsersProjection_thenAllUsersShoulBeFound() {
        given()
                .when()
                .get("/users/projection")
                .then()
                .statusCode(200)
                .body("size()", is(2), "username", hasItems("usuario01","usuario02"));
    }

    @Test
    public void whenFindAllUsersHQL_thenAllUsersShoulBeFound() {
        given()
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
                .when()
                .get("/users/" + id)
                .then()
                .statusCode(404);
    }

    @Test
    public void whenCreateUser_thenUserShouldBeFound() {
        UserRecord userRecord = new UserRecord("usuario01","grupo01");
        given()
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("username", is("usuario01"));

        userRecord = new UserRecord("usuario02","grupo02");
        given()
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/users")
                .then().statusCode(201)
                .body("username", is("usuario02"));

        userRecord = new UserRecord("usuario03",null);
        given()
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/users")
                .then().statusCode(201)
                .body("username", is("usuario03"));

        userRecord = new UserRecord("usuario04","");
        given()
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
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .put("/users/" + id)
                .then()
                .statusCode(200)
                .body("username", is("usuario01"));

        userRecord = new UserRecord("usuario01",null);
        given()
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
                .when()
                .delete("/users/" + id)
                .then()
                .statusCode(204);
    }

}
