// Implementation from NinjabrainBot

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

public class ClipboardReader implements Runnable {

    Frame frame;
    Clipboard clipboard;
    String lastClipboardString;
    Thread runner;

    public ClipboardReader(Frame frame) {
        this.frame = frame;
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        lastClipboardString = "";
        runner = new Thread(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String clipboardString = (String) clipboard
                        .getData(DataFlavor.stringFlavor);
                if (!lastClipboardString.equals(clipboardString)) {
                    frame.update(clipboardString);
                    lastClipboardString = clipboardString;
                }
            } catch (Exception e) {
            }
            // Sleep 0.1 seconds
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Old implementation by me

// import java.awt.Toolkit;
// import java.awt.datatransfer.Clipboard;
// import java.awt.datatransfer.ClipboardOwner;
// import java.awt.datatransfer.DataFlavor;
// import java.awt.datatransfer.Transferable;

// public class ClipboardReader extends Thread implements ClipboardOwner {
// static Clipboard clipboard;
// String string;
// Frame frame;
// Thread runner;

// ClipboardReader(Frame _frame) throws Exception {
// frame = _frame;
// clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
// string = readClipboard();
// frame.update(string);
// runner = new Thread(this);
// runner.start();
// }

// public static String readClipboard() throws Exception {
// return (String) clipboard.getData(DataFlavor.stringFlavor);
// }

// @Override
// public void run() {
// while (true) {
// try {
// String newClipboard = readClipboard();
// if (newClipboard == null)
// return;
// if (!newClipboard.equals(string)) {
// string = newClipboard;
// frame.update(string);
// }
// Thread.sleep(100);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }

// @Override
// public void lostOwnership(Clipboard c, Transferable t) {
// Transferable contents = clipboard.getContents(this); // EXCEPTION
// processContents(contents);
// regainOwnership(contents);
// }

// void processContents(Transferable t) {
// System.out.println("Processing: " + t);
// }

// void regainOwnership(Transferable t) {
// clipboard.setContents(t, this);
// }
// }
