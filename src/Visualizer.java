import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Visualizer extends JPanel {

    static final Color RING_COLOR = new Color(0x32b84f);
    static final Color RING_MIDDLE_COLOR = new Color(0x2ba145);
    static final Color BACKGROUND_COLOR = new Color(0x202024);
    static final Color PLAYER_COLOR = new Color(0x558cd4);

    Font font;
    static final int[][] rings = Main.RINGS;

    int playerX = 0;
    int playerY = 0;

    int width = 400;
    int height = 300;

    Visualizer() {
        setPreferredSize(new Dimension(width, height));
        setBackground(BACKGROUND_COLOR);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class
                    .getResourceAsStream("/resources/OpenSans-Regular.ttf"))
                    .deriveFont(24.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int x, int z) {
        playerX = x;
        playerY = z;
        repaint();
    }

    public void draw(Graphics g) {
        g.setFont(font);
        double scale = 0.125;
        int scaledX = (int) (playerX * scale);
        int scaledY = (int) (playerY * scale);

        // Draw rings
        for (int i = rings.length - 1; i >= 0; i--) {
            int r = (int) (rings[i][0] * scale);
            int R = (int) (rings[i][1] * scale);
            int middle = (r + R) / 2;
            g.setColor(RING_COLOR);
            g.fillOval(-R - scaledX + width / 2, -R - scaledY + height / 2,
                    2 * R, 2 * R);
            g.setColor(BACKGROUND_COLOR);
            g.fillOval(-r - scaledX + width / 2, -r - scaledY + height / 2,
                    2 * r, 2 * r);
            g.setColor(RING_MIDDLE_COLOR);
            g.drawOval(-middle - scaledX + width / 2,
                    -middle - scaledY + height / 2,
                    2 * middle, 2 * middle);
        }

        // Draw axes
        g.setColor(Color.BLUE);
        g.drawLine(-scaledX + width / 2, 0, -scaledX + width / 2, height);
        g.drawString("-z", width / 2, 20);
        g.drawString("+z", width / 2, height - 5);

        g.setColor(Color.RED);
        g.drawLine(0, -scaledY + height / 2, width, -scaledY + height / 2);
        g.drawString("-x", 5, height / 2);
        g.drawString("+x", width - 30, height / 2);

        // Draw player
        g.setColor(PLAYER_COLOR);
        g.fillOval(-5 + width / 2, -5 + height / 2, 10, 10);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
