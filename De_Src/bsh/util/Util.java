/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.Interpreter;
import bsh.util.BshCanvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;

public class Util {
    static Window splashScreen;

    public static void startSplashScreen() {
        int width = 275;
        int height = 148;
        Window win = new Window(new Frame());
        win.pack();
        BshCanvas can = new BshCanvas();
        can.setSize(width, height);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        win.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        win.add("Center", can);
        Image img = tk.getImage(Interpreter.class.getResource("/bsh/util/lib/splash.gif"));
        MediaTracker mt = new MediaTracker(can);
        mt.addImage(img, 0);
        try {
            mt.waitForAll();
        } catch (Exception e) {
            // empty catch block
        }
        Graphics gr = can.getBufferedGraphics();
        gr.drawImage(img, 0, 0, can);
        win.setVisible(true);
        win.toFront();
        splashScreen = win;
    }

    public static void endSplashScreen() {
        if (splashScreen != null) {
            splashScreen.dispose();
        }
    }
}

