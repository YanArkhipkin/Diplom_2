package api.client;
import api.client.Client;
import api.model.Credentials;
import api.model.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    @Step("Создание нового пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(USER_REGISTER)
                .then();
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser(Credentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(USER_LOGIN)
                .then().log().all();
    }

    @Step("Обновление информации о пользователе")
    public ValidatableResponse updateUserData(User user, String token) {
        return given()
                .header("Authorization", token)
                .spec(getSpec())
                .body(user)
                .when()
                .patch(USER_AUTH)
                .then().log().all();
    }

    @Step("Обновление информации о пользователе без авторизации")
    public ValidatableResponse updateUserDataWithoutAuth(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .patch(USER_AUTH)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .spec(getSpec())
                .when()
                .delete(USER_AUTH)
                .then();
    }
}