import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class Frame extends MoveJFrame {

    Visualizer visualizer;
    Table table;
    Info info;
    ClipboardReader clipboardReader;

    Frame() throws Exception {
        visualizer = new Visualizer();
        table = new Table();
        info = new Info(this);
        clipboardReader = new ClipboardReader(this);

        setSize(400, 400);
        setTitle("Blind Travel Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        //setUndecorated(true);
        setVisible(true);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        add(info, c);

        c.gridx = 0;
        c.gridy = 1;
        add(table, c);

        c.gridx = 0;
        c.gridy = 2;
        add(visualizer, c);

        pack();
        clipboardReader.runner.start();
    }

    public void update(String string) throws Exception {
        if (string == null)
            return;

        Pattern netherPattern = Pattern.compile(
                "^\\/execute in minecraft:the_nether run tp @s (?<x>-?\\d+\\.\\d\\d) -?\\d+\\.\\d\\d (?<z>-?\\d+\\.\\d\\d) -?\\d+\\.\\d\\d -?\\d+\\.\\d\\d$");
        Matcher netherMatcher = netherPattern.matcher(string);

        Pattern overworldPattern = Pattern.compile(
                "^\\/execute in minecraft:overworld run tp @s (?<x>-?\\d+\\.\\d\\d) -?\\d+\\.\\d\\d (?<z>-?\\d+\\.\\d\\d) -?\\d+\\.\\d\\d -?\\d+\\.\\d\\d$");
        Matcher overworldMatcher = overworldPattern.matcher(string);

        int x, z;
        if (netherMatcher.find()) {
            x = (int) (Double.parseDouble(netherMatcher.group("x")) * 8);
            z = (int) (Double.parseDouble(netherMatcher.group("z")) * 8);
            updateDimension(true);
        } else if (overworldMatcher.find()) {
            x = (int) (Double.parseDouble(overworldMatcher.group("x")));
            z = (int) (Double.parseDouble(overworldMatcher.group("z")));
            updateDimension(false);
        } else
            return;

        visualizer.update(x, z);
        table.update(x, z);
        info.update();
    }

    public void updateDimension(boolean dimension) {
        table.updateDimension(dimension);
        info.dimensionButton.update(dimension);
    }

    // @formatter:off
    // /execute in minecraft:the_nether run tp @s 1.50 70.00 -0.00 306.32 27.00
    // /execute in minecraft:overworld run tp @s 118.00 70.00 -500.00 306.32 27.00
}
