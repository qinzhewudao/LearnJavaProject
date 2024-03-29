package annotation;

/**
 * author sheyang
 * created at 2018/11/7
 */
public class PasswordUtils {
    @UseCase.UseCases(id = "47", description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase.UseCases(id = "48")
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
}
