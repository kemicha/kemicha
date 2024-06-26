package src.UI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowsClose extends WindowAdapter {


    public class WindowCloser extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            Window window = e.getWindow();
            int result = JOptionPane.showConfirmDialog(window, "Wollen Sie die Anwendung wirklich beenden?", "Achtung!", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                window.setVisible(false);
                window.dispose();
                System.exit(0);
            }
        }
    }

}
