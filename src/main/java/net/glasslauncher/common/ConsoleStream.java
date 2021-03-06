/*
This uses a buffer system because appending to a TextPane takes about 20ms.
 */

package net.glasslauncher.common;

import javax.swing.JTextArea;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

public class ConsoleStream extends OutputStream {
    private Timer timer = new Timer();

    private String buffer = "";

    public ConsoleStream(JTextArea textArea, PrintStream jcons) {
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (!buffer.isEmpty()) {
                            textArea.setText(textArea.getText() + buffer);
                            textArea.setCaretPosition(textArea.getDocument().getLength());
                            jcons.print(buffer);
                            textArea.repaint();
                            buffer = "";
                        }
                    }
                },
                0,
                100);
    }

    @Override
    public void write(int i) {
        buffer += (char) i;
    }
}