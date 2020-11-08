import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.awt.*;
import java.awt.event.InputEvent;

public class Main implements NativeMouseInputListener, NativeKeyListener {

    Robot bot;
    boolean bridgeMode = false;

    {
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void nativeMouseClicked(NativeMouseEvent e) {

    }

    public void nativeMousePressed(NativeMouseEvent e) {
        System.out.println("Mouse Pressed: " + e.getButton());
        if (e.getButton() == 2) {
            for (int i = 0; i < (bridgeMode ? 2 : 1); i++) {
                bot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
//                bot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        if (e.getButton() == 2) {
            bot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
        }
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
//            System.err.println("There was a problem registering the native hook.");
//            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Construct the example object.
        Main example = new Main();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeKeyListener(example);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getRawCode() == 20) {
            bridgeMode = !bridgeMode;
            System.out.println(bridgeMode);
        }
    }
}