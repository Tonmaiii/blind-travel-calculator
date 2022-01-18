import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

public class Table extends JPanel {

    int rows = 2;
    int columns = 5;
    Font font = new Font(null);
    Font fontLight = new Font(null);
    Text[][] cells = new Text[rows][columns];
    int playerX, playerZ;
    boolean dimension = true;

    Table() throws FontFormatException, IOException {

        setLayout(new GridLayout(rows, columns));
        setPreferredSize(new Dimension(400, 60));
        configureFonts();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Text text = new Text();
                if (i == 0) {
                    text.setBackground(new Color(0x252538));
                    text.label.setFont(font);
                } else {
                    text.setBackground(new Color(0x2b2d44));
                    text.label.setFont(fontLight);
                }
                add(text);
                cells[i][j] = text;
            }
        }

        cells[0][0].label.setText("Ring No.");
        cells[0][1].label.setText("Dist.");
        cells[0][2].label.setText("Dist. Hi");
        cells[0][3].label.setText("Target");
        cells[0][4].label.setText("Target Hi");

        update(0, 0);

    }

    void configureFonts() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class
                    .getResourceAsStream("/resources/OpenSans-Regular.ttf"))
                    .deriveFont(16.0f);
            fontLight = Font.createFont(Font.TRUETYPE_FONT, Main.class
                    .getResourceAsStream("/resources/OpenSans-Light.ttf"))
                    .deriveFont(16.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update(int x, int z) {
        int scale = dimension ? 8 : 1;
        playerX = x;
        playerZ = z;

        int closestRing = Calculator.closestRing(x, z) + 1;
        int distanceToClosestRing = Calculator.distanceToClosestRing(x, z);
        int distanceToMiddleClosestRing = Calculator
                .distanceToMiddleClosestRing(x, z);
        int[] locationOfClosestRing = Calculator.locationOfClosestRing(x, z);
        int[] locationOfMiddleClosestRing = Calculator
                .locationOfMiddleClosestRing(x, z);

        cells[1][0].label.setText(Integer.toString(closestRing));
        cells[1][1].label.setText(Integer.toString(
                distanceToMiddleClosestRing / scale));
        cells[1][2].label
                .setText(Integer.toString(distanceToClosestRing / scale));
        cells[1][3].label
                .setText(Integer
                        .toString(locationOfMiddleClosestRing[0] / scale)
                        + ", "
                        + Integer.toString(
                                locationOfMiddleClosestRing[1] / scale));
        cells[1][4].label
                .setText(Integer.toString(locationOfClosestRing[0] / scale)
                        + ", " +
                        Integer.toString(locationOfClosestRing[1] / scale));
    }

    void updateDimension(boolean dimension) {
        this.dimension = dimension;
        update(playerX, playerZ);
    }

}
