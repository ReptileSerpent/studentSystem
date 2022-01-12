import javax.swing.*;

public class JFrameManager
{
    private static JFrame authenticationFrame;
    private static JFrame adminHomeFrame;
    private static JFrame teacherHomeFrame;
    private static JFrame studentHomeFrame;

    public static void showAuthenticationFrame()
    {
        authenticationFrame = new JFrame();
        authenticationFrame.setContentPane(new AuthenticationForm().authenticationPanel);
        authenticationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        authenticationFrame.setSize(400,200);
        authenticationFrame.setVisible(true);
    }

    public static void hideAuthenticationFrame()
    {
        authenticationFrame.setVisible(false);
    }

    public static void showAdminHomeFrame()
    {
        adminHomeFrame = new JFrame();
        adminHomeFrame.setContentPane(new AdminHomeForm().adminHomePanel);
        adminHomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminHomeFrame.setSize(1000,600);
        adminHomeFrame.setVisible(true);
    }

    public static void showTeacherHomeFrame()
    {
        teacherHomeFrame = new JFrame();
        teacherHomeFrame.setContentPane(new TeacherHomeForm().teacherHomePanel);
        teacherHomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        teacherHomeFrame.setSize(1000,600);
        teacherHomeFrame.setVisible(true);
    }

    public static void showStudentHomeFrame(int resultId)
    {
        studentHomeFrame = new JFrame();
        studentHomeFrame.setContentPane(new StudentHomeForm(resultId).studentHomePanel);
        studentHomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentHomeFrame.setSize(1000,600);
        studentHomeFrame.setVisible(true);
    }

}
