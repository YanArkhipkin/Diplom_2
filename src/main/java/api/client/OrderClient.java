package api.client;
import api.client.Client;
import api.model.Order;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order, String token) {
        return given()
                .spec(getSpec())
                .header("Authorization", token)
                .body(order)
                .when()
                .post(ORDERS)
                .then().log().all();
    }

    @Step("Создание заказа без авторизации")
    public ValidatableResponse createOrderWithoutAuth(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS)
                .then().log().all();
    }

    @Step("Получение заказов пользователя")
    public ValidatableResponse getUserOrders(String token) {
        return given()
                .spec(getSpec())
                .header("Authorization", token)
                .when()
                .get(ORDERS)
                .then().log().all();
    }

    @Step("Получение заказов пользователя без авторизации")
    public ValidatableResponse getUserOrdersWithoutAuth() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDERS)
                .then().log().all();
    }
}