import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Text extends JPanel {

    JLabel label;

    Text() {
        label = new JLabel();
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);
        add(label);

    }
}
