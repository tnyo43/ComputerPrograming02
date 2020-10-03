import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Image;

public abstract class MessagePanel extends JPanel {
    private static final long serialVersionUID = 3502217729174259608L;

    protected static final int ICON_SIZE = 40;
    protected static final int HEIGHT = 60;
    protected static final int WIDTH = 190;

    public static MessagePanel get(String user, String text) {
        StickerType type = StickerType.getType(text);
        return (type == null) ? new TextMessagePanel(user, text) : new StickerMessagePanel(user, type);
    }

    protected ImageIcon getImageIcon(String filename, int width, int height) {
        ImageIcon ii = new ImageIcon(filename);
        Image image = ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }
}
