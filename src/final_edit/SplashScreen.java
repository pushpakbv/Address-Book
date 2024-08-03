package final_edit;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SplashScreen extends JWindow {

    private static final long serialVersionUID = 1L;
    private final int duration;

    public SplashScreen(int d) {
        duration = d;
    }


    public void showSplash() {
        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;


        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setBounds(r);

        // Build the splash screen
        JLabel label = new JLabel();
        URL imageURL = SplashScreen.class.getResource("splash_image.png");
        if (imageURL != null) {
            ImageIcon imgIcon = new ImageIcon(imageURL);
            Image scaledImage = imgIcon.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
            imgIcon = new ImageIcon(scaledImage);
            label.setIcon(imgIcon);
        } else {
            label.setText("Splash Screen Image Not Found");
        }

        getContentPane().add(label);

        // Display the splash screen
        setVisible(true);

        // Wait for the specified duration and then hide the splash screen
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
    }
}
