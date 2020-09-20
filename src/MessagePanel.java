import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Dimension;

public class MessagePanel extends JPanel {
    private static final long serialVersionUID = 3502217729174259608L;

    public MessagePanel(String text) {
        super();

        ImageIcon image = new ImageIcon("../img/balloon.png");
        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(image);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(150, 59));
        JLabel textLabel = new JLabel(text);
        layeredPane.add(textLabel);
        layeredPane.add(imageContainer);
        add(layeredPane);

        imageContainer.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        textLabel.setBounds(15, 10, 130, 35);
    }
}
