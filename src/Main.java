import java.awt.*;
import java.awt.event.AWTEventListener;

public class Main {
    public static void main(String[] args) {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {

            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
    }
}
