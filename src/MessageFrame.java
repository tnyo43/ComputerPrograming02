import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MessageFrame extends Frame {
    private static final long serialVersionUID = -3594659038545207881L;

    private JPanel messageBox;

    private JPanel messageInputPanel;
    private JTextField messageInput;
    private JButton messageSendButton;

    public MessageFrame() {
        super("メッセージフレーム");

        setLayout(new BorderLayout());
        setSize(300, 300);

        messageBox = new JPanel(new GridLayout(20, 1));
        messageBox.add(new JLabel("ここに"));
        messageBox.add(new JLabel("交換し合った"));
        messageBox.add(new JLabel("メッセージを"));
        messageBox.add(new JLabel("表示する"));
        messageBox.add(new JLabel("予定"));
        add(messageBox, BorderLayout.CENTER);

        messageInputPanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        messageSendButton = new JButton("送信");
        messageInputPanel.add(messageInput, BorderLayout.CENTER);
        messageInputPanel.add(messageSendButton, BorderLayout.EAST);
        add(messageInputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
