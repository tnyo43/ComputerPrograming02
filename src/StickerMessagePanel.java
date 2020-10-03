import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Dimension;

public class StickerMessagePanel extends MessagePanel {
    private static final long serialVersionUID = -1165985541606749744L;

    private static final int MARGIN_LR = 15;

    private static final int STICKER_HEIGHT = HEIGHT;
    private static final int STICKER_WIDTH = Math.min(WIDTH - ICON_SIZE - MARGIN_LR, HEIGHT);

    public StickerMessagePanel(String user, StickerType type) {
        super();

        ImageIcon iconImage = getImageIcon(String.format("../img/%s.png", user), ICON_SIZE, ICON_SIZE);
        JLabel iconImageContainer = new JLabel();
        iconImageContainer.setIcon(iconImage);

        ImageIcon stickerImage = getImageIcon(String.format("../img/%s.png", type), STICKER_WIDTH, STICKER_HEIGHT);
        JLabel stickerImageContainer = new JLabel();
        stickerImageContainer.setIcon(stickerImage);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layeredPane.add(stickerImageContainer);
        layeredPane.add(iconImageContainer);
        add(layeredPane);

        stickerImageContainer.setBounds(ICON_SIZE + MARGIN_LR, 0, stickerImage.getIconWidth(), stickerImage.getIconHeight());
        iconImageContainer.setBounds(0, 0, iconImage.getIconWidth(), iconImage.getIconHeight());
    }
}
