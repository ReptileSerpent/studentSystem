import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationForm {
    public JPanel authenticationPanel;
    private JLabel titleField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel incorrectCredentialsWarningField;

    public AuthenticationForm() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var email = emailField.getText();
                var password = passwordField.getText();
                var resultId = 0;
                var resultRole = 0;
                var correctCredentials = false;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            var resultSet = statement.executeQuery("SELECT Id, Password, Role_id FROM User WHERE Email=\"" + email + "\"");
                            var resultPassword = "";

                            while (resultSet.next())
                            {
                                resultId = resultSet.getInt("Id");
                                resultPassword = resultSet.getString("Password");
                                resultRole = resultSet.getInt("Role_id");
                            }
                            if (password.equals(resultPassword))
                                correctCredentials = true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else
                    System.out.println("Failed to get database connection");

                System.out.println(resultRole);
                if (correctCredentials)
                {
                    if (resultRole == 1)
                    {
                        JFrameManager.hideAuthenticationFrame();
                        JFrameManager.showAdminHomeFrame();
                        System.out.println("welcome");
                    }
                    else if (resultRole == 2)
                    {
                        JFrameManager.hideAuthenticationFrame();
                        JFrameManager.showTeacherHomeFrame();
                        System.out.println("welcome");
                    }
                    else if (resultRole == 3)
                    {
                        JFrameManager.hideAuthenticationFrame();
                        JFrameManager.showStudentHomeFrame(resultId);
                        System.out.println("welcome");
                    }
                }
                else
                    incorrectCredentialsWarningField.setText("Incorrect email or password");
            }
        });
    }

    public static void main(String[] args) {
    }

}
