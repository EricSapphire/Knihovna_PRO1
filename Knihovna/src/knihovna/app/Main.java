package knihovna.app;

import knihovna.gui.KnihovnaFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KnihovnaFrame().setVisible(true);
            }
        });
    }
}