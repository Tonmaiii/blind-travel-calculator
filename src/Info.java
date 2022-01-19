import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Info extends JPanel {

    Font font;
    DimensionButton dimensionButton;
    Frame frame;
    Text location;
    int playerX, playerZ;

    Info(Frame frame) {
        this.frame = frame;

        setLayout(new GridLayout(1, 4));
        setPreferredSize(new Dimension(400, 30));
        configureFonts();

        Text dimensionLabel = new Text();
        dimensionLabel.setBackground(new Color(0x1c1c27));
        dimensionLabel.label.setFont(font);
        dimensionLabel.label.setText("Dimension:");
        add(dimensionLabel);

        dimensionButton = new DimensionButton(frame);
        add(dimensionButton);

        Text locationLabel = new Text();
        locationLabel.setBackground(new Color(0x1c1c27));
        locationLabel.label.setFont(font);
        locationLabel.label.setText("Location:");
        add(locationLabel);

        location = new Text();
        location.setBackground(new Color(0x1c1c27));
        location.label.setFont(font);
        location.label.setText("0, 0");
        add(location);
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

    void update() {
        int scale = dimensionButton.dimension ? 8 : 1;
        location.label.setText(Integer.toString(frame.table.playerX / scale)
                + ", " + Integer.toString(frame.table.playerZ / scale));
    }

    void updateDimension(boolean dimension) {
        dimensionButton.dimension = dimension;
        update();
    }
}
