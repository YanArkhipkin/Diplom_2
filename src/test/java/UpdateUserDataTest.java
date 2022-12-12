import api.client.UserClient;
import api.model.Credentials;
import api.model.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateUserDataTest {
    private User user;
    private UserClient userClient;
    private String token;
    private ValidatableResponse responseCreate;

    @Before
    public void setUp() {
        user = User.getRandomUser();
        userClient = new UserClient();
        responseCreate = userClient.createUser(user);
    }

    @Test
    @DisplayName("Тест изменения данных пользователя с авторизацией")
    public void updateUserDataWithAuthTest() {
        ValidatableResponse responseLogin = userClient.loginUser(Credentials.from(user));
        token = responseLogin.extract().path("accessToken");
        User userNewData = new User(user.getEmail() + "ail", user.getPassword() + "77", user.getName() + "ер");
        ValidatableResponse responseUpdate = userClient.updateUserData(userNewData, token);
        responseUpdate.assertThat().statusCode(200)
                .and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Тест изменения данных пользователя без авторизации")
    public void updateUserDataWithoutAuthTest() {
        token = responseCreate.extract().path("accessToken");
        User userNewData = new User(user.getEmail() + "ail", user.getPassword() + "77", user.getName() + "ер");
        ValidatableResponse responseUpdate = userClient.updateUserDataWithoutAuth(userNewData);
        responseUpdate.assertThat().statusCode(401)
                .and().body("message", equalTo("You should be authorised"));
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(token);
    }
}