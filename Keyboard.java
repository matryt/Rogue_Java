import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Keyboard implements KeyListener {

    private int pressedKey;

    JFrame frame;

    public Keyboard() {
        frame = new JFrame();
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1, 1);
        frame.setVisible(true);
    }

    public String waitUntilPressed() {
        while (pressedKey == 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String keyText = KeyEvent.getKeyText(pressedKey);
        pressedKey = 0; // Reset pressedKey to 0
        return keyText;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKey = e.getKeyCode();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Ignore keyTyped events
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }
}