package final_edit;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen(3000);
        splash.showSplash();
        SwingUtilities.invokeLater(AddressBookUI::new);
    }
}
