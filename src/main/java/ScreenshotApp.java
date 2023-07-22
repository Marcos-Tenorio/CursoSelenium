import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScreenshotApp extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    private JLabel screenshotLabel;

    public ScreenshotApp() {
        super("Screenshot App");
        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(800, 600));
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        screenshotLabel = new JLabel();
        contentPane.add(screenshotLabel);

        addKeyListener(this);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ScreenshotApp();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            try {
                BufferedImage screenshot = captureScreen();
                saveScreenshot(screenshot);
                displayScreenshot(screenshot);
            } catch (AWTException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private BufferedImage captureScreen() throws AWTException {
        Rectangle screenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return new Robot().createScreenCapture(screenBounds);
    }

    private void saveScreenshot(BufferedImage screenshot) throws IOException {
        File outputFile = new File("screenshot.png");
        ImageIO.write(screenshot, "png", outputFile);
    }

    private void displayScreenshot(BufferedImage screenshot) {
        ImageIcon screenshotIcon = new ImageIcon(screenshot);
        screenshotLabel.setIcon(screenshotIcon);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}