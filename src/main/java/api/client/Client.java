package api.client;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String USER_REGISTER = "api/auth/register";
    public static final String USER_LOGIN = "api/auth/login";
    public static final String USER_AUTH = "api/auth/user";
    public static final String ORDERS = "api/orders";

    protected RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}