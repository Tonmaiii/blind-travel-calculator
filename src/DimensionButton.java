import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class DimensionButton extends JButton {

    Font font;
    Frame frame;
    boolean dimension = true;

    DimensionButton(Frame frame) {
        this.frame = frame;
        configureFonts();

        setText("Nether");
        setFont(font);
        setFocusable(false);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setBackground(new Color(0x1c1c27));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dimension = !dimension;
                setText(dimension ? "Nether" : "Overworld");
                frame.updateDimension(dimension);
            }
        });
    }

    void update(boolean dimension) {
        this.dimension = dimension;
        setText(dimension ? "Nether" : "Overworld");
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(new Color(0x2b2d44));
        } else if (getModel().isRollover()) {
            g.setColor(new Color(0x222234));
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    void configureFonts() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class
                    .getResourceAsStream("/resources/OpenSans-Regular.ttf"))
                    .deriveFont(16.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
