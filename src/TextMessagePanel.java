import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Dimension;

public class TextMessagePanel extends MessagePanel {
    private static final long serialVersionUID = 2520120508283600808L;

    private static final int BALOON_WIDTH = WIDTH - ICON_SIZE;
    private static final int BALOON_HEIGHT = HEIGHT;

    private static final int MARGIN_LR = 15;
    private static final int MARGIN_UD = 10;

    public TextMessagePanel(String user, String text) {
        super();

        ImageIcon iconImage = getImageIcon(String.format("../img/%s.png", user), ICON_SIZE, ICON_SIZE);
        JLabel iconImageContainer = new JLabel();
        iconImageContainer.setIcon(iconImage);

        ImageIcon image = getImageIcon("../img/balloon.png", BALOON_WIDTH, BALOON_HEIGHT);
        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(image);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
}
