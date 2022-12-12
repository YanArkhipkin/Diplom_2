import api.client.OrderClient;
import api.client.UserClient;
import api.model.Order;
import api.model.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class GetUserOrdersTest {
    private User user;
    private UserClient userClient;
    private OrderClient orderClient;
    private String token;
    private ValidatableResponse responseUserCreate;
    private ValidatableResponse responseOrderCreate;

    @Before
    public void setUp() {
        user = User.getRandomUser();
        userClient = new UserClient();
        responseUserCreate = userClient.createUser(user);
        token = responseUserCreate.extract().path("accessToken");
        orderClient = new OrderClient();
        responseOrderCreate = orderClient.createOrder(Order.getCorrectOrder(), token);
        responseOrderCreate = orderClient.createOrder(Order.getCorrectOrder(), token);
    }

    @Test
    @DisplayName("Тест получения списка заказов авторизованного пользователя")
    public void getUserOrdersWithAuthTest() {
        ValidatableResponse responseGet = orderClient.getUserOrders(token);
        responseGet.assertThat().statusCode(200)
                .and().body("orders._id", hasSize(2));
    }

    @Test
    @DisplayName("Тест получения списка заказов авторизованного пользователя")
    public void getUserOrdersWithoutAuthTest() {
        ValidatableResponse responseGet = orderClient.getUserOrdersWithoutAuth();
        responseGet.assertThat().statusCode(401)
                .and().body("message", equalTo("You should be authorised"));
    }

    @After
    public void tearDown() {
        userClient.deleteUser(token);
    }
}