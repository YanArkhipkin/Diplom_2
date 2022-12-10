import org.apache.commons.lang3.RandomStringUtils;

public class User {
    String email;
    String password;
    String name;

    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User getRandomUser() {
        final String userEmail = RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
        final String userPassword = RandomStringUtils.randomAlphabetic(8);
        final String userName = RandomStringUtils.randomAlphabetic(8);
        return new User(userEmail, userPassword, userName);
    }

    public static User getUserWithoutEmail() {
        final String userPassword = RandomStringUtils.randomAlphabetic(8);
        final String userName = RandomStringUtils.randomAlphabetic(8);
        return new User("", userPassword, userName);
    }

    public static User getUserWithoutPassword() {
        final String userEmail = RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
        final String userName = RandomStringUtils.randomAlphabetic(8);
        return new User(userEmail, "", userName);
    }

    public static User getUserWithoutName() {
        final String userEmail = RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
        final String userPassword = RandomStringUtils.randomAlphabetic(8);
        return new User(userEmail, userPassword, "");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
