public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("Application launched");
        DatabaseManager.checkConnection();
        JFrameManager.showAuthenticationFrame();
    }
}
