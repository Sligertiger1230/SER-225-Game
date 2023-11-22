package Scripts.TestMap;

import javax.swing.*;
import java.awt.*;

public class BlackScreen extends JDialog {

    private static final int TRANSITION_DURATION = 1000; // Adjust the duration as needed
    private static final int STEPS = 50;
    private static final float OPACITY_INCREMENT = 1.0f / STEPS;

    private static BlackScreen instance;

    private float currentOpacity = 0.0f;

    private BlackScreen() {
        super((Frame) null, true); // No owner frame
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public static BlackScreen getInstance() {
        if (instance == null) {
            instance = new BlackScreen();
        }
        return instance;
    }

    public void showWithTransition() {
        currentOpacity = 0.0f;
        setVisible(true);

        // Perform the gradient transition
        for (int i = 0; i <= STEPS; i++) {
            currentOpacity += OPACITY_INCREMENT;
            repaint();
            try {
                Thread.sleep(TRANSITION_DURATION / STEPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void hideWithTransition() {
        // Perform the gradient transition
        for (int i = 0; i <= STEPS; i++) {
            currentOpacity -= OPACITY_INCREMENT;
            repaint();
            try {
                Thread.sleep(TRANSITION_DURATION / STEPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 0, (int) (currentOpacity * 255)));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
