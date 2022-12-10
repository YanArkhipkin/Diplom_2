public class Credentials {
    String email;
    String password;

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Credentials from(User user) {
        return new Credentials(user.getEmail() , user.getPassword());
    }

    public static Credentials getCredentialsWithWrongEmail(User user) {
        return new Credentials(user.getEmail() + "m", user.getPassword());
    }

    public static Credentials getCredentialsWithWrongPassword(User user) {
        return new Credentials(user.getEmail(), user.getPassword() + "12");
    }

    public static Credentials getCredentialsWithWrongData(User user) {
        return new Credentials(user.getEmail() + "n", user.getPassword() + "77");
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

}
