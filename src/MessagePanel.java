import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Image;

public class MessagePanel extends JPanel {
    private static final long serialVersionUID = 3502217729174259608L;

    private static final int ICON_SIZE = 40;
    private static final int BALOON_WIDTH = 150;
    private static final int BALOON_HEIGHT = 60;

    private static final int MARGIN_LR = 15;
    private static final int MARGIN_UD = 10;

    public MessagePanel(String text, String userIcon) {
        super();

        ImageIcon iconImage = getImageIcon(String.format("../img/%s.png", userIcon), ICON_SIZE, ICON_SIZE);
        JLabel iconImageContainer = new JLabel();
        iconImageContainer.setIcon(iconImage);

        ImageIcon image = getImageIcon("../img/balloon.png", BALOON_WIDTH, BALOON_HEIGHT);
        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(image);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(ICON_SIZE + BALOON_WIDTH, BALOON_HEIGHT));
        JLabel textLabel = new JLabel(text);
        layeredPane.add(textLabel);
        layeredPane.add(imageContainer);
        layeredPane.add(iconImageContainer);
        add(layeredPane);

        imageContainer.setBounds(ICON_SIZE, 0, image.getIconWidth(), image.getIconHeight());
        iconImageContainer.setBounds(0, 0, iconImage.getIconWidth(), iconImage.getIconHeight());
        textLabel.setBounds(
            ICON_SIZE + MARGIN_LR,
            MARGIN_UD,
            BALOON_WIDTH - MARGIN_LR,
            ICON_SIZE - MARGIN_UD
        );
    }

    private ImageIcon getImageIcon(String filename, int width, int height) {
        ImageIcon ii = new ImageIcon(filename);
        Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }
}
