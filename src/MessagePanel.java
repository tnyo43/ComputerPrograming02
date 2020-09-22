import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Image;

public class MessagePanel extends JPanel {
    private static final long serialVersionUID = 3502217729174259608L;

    public MessagePanel(String text, String userIcon) {
        super();

        ImageIcon iconImage = getImageIcon(String.format("../img/%s.png", userIcon), 40, 40);
        JLabel iconImageContainer = new JLabel();
        iconImageContainer.setIcon(iconImage);

        ImageIcon image = getImageIcon("../img/balloon.png", 150, 60);
        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(image);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(190, 60));
        JLabel textLabel = new JLabel(text);
        layeredPane.add(textLabel);
        layeredPane.add(imageContainer);
        layeredPane.add(iconImageContainer);
        add(layeredPane);

        imageContainer.setBounds(40, 0, image.getIconWidth(), image.getIconHeight());
        iconImageContainer.setBounds(0, 0, iconImage.getIconWidth(), iconImage.getIconHeight());
        textLabel.setBounds(55, 10, 130, 35);
    }

    private ImageIcon getImageIcon(String filename, int width, int height) {
        ImageIcon ii = new ImageIcon(filename);
        Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }
}
