import api.client.UserClient;
import api.model.Credentials;
import api.model.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest {
    private UserClient userClient;
    private User user;
    private String token;
    private int statusCode;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandomUser();
    }

    @Test
    @DisplayName("Тест создания нового пользователя")
    public void createUserTest() {
        ValidatableResponse responseCreate = userClient.createUser(user);
        responseCreate.assertThat().statusCode(200)
                .and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Тест создания уже существующего пользователя")
    public void createExistingUserTest() {
        userClient.createUser(user);
        ValidatableResponse responseCreate = userClient.createUser(user);
        responseCreate.assertThat().statusCode(403)
                .and().body("message", equalTo("api.model.User already exists"));
    }

    @Test
    @DisplayName("Тест создания пользователя без email")
    public void createUserWithoutEmailTest() {
        user = User.getUserWithoutEmail();
        ValidatableResponse responseCreate = userClient.createUser(user);
        responseCreate.assertThat().statusCode(403)
                .and().body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Тест создания пользователя без password")
    public void createUserWithoutPasswordTest() {
        user = User.getUserWithoutPassword();
        ValidatableResponse responseCreate = userClient.createUser(user);
        responseCreate.assertThat().statusCode(403)
                .and().body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Тест создания пользователя без name")
    public void createUserWithoutNameTest() {
        user = User.getUserWithoutName();
        ValidatableResponse responseCreate = userClient.createUser(user);
        responseCreate.assertThat().statusCode(403)
                .and().body("message", equalTo("Email, password and name are required fields"));
    }

    @After
    public void deleteUser() {
        statusCode = userClient.loginUser(Credentials.from(user)).extract().statusCode();
        if(statusCode == 200) {
            token = userClient.loginUser(Credentials.from(user)).extract().path("accessToken");
            userClient.deleteUser(token);
        }
    }
}