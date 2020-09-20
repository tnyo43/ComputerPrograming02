import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = -3594659038545207881L;

    private JPanel messageBox;

    private JPanel messageInputPanel;
    private JTextField messageInput;
    private JButton messageSendButton;

    public MessageFrame() {
        super("メッセージフレーム");

        setLayout(new BorderLayout());
        setSize(300, 300);

        messageBox = new JPanel(new GridLayout(10, 1));
        messageBox.add(new MessagePanel("ここに"));
        messageBox.add(new MessagePanel("交換し合った"));
        messageBox.add(new MessagePanel("メッセージを"));
        messageBox.add(new MessagePanel("表示する"));
        messageBox.add(new MessagePanel("予定"));
        add(messageBox, BorderLayout.CENTER);

        messageInputPanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        messageSendButton = new JButton("送信");
        messageSendButton.addActionListener(this);
        messageInputPanel.add(messageInput, BorderLayout.CENTER);
        messageInputPanel.add(messageSendButton, BorderLayout.EAST);
        add(messageInputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messageSendButton) {
            String text = messageInput.getText();
            if (text.equals("")) return;
            messageBox.add(new MessagePanel(text));
            messageInput.setText("");
        }
        messageBox.revalidate();
        messageBox.repaint();
    }
}
