import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageFrame extends JFrame implements ActionListener, MessageReceiver {
    private static final long serialVersionUID = -3594659038545207881L;

    private JPanel messageBox;
    private GridLayout gridLayout;

    private JPanel messageInputPanel;
    private JTextField messageInput;
    private JButton messageSendButton;

    private JScrollPane scroll;

    private static final int MINIMAM_MESSAGE_NUMBER = 7;
    private int messageCount = 0;
    private String username;

    private Client client;

    public MessageFrame(String username) {
        super("メッセージフレーム");

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 560));
        gridLayout = new GridLayout(MINIMAM_MESSAGE_NUMBER, 1);
        messageBox = new JPanel(gridLayout);
        scroll = new JScrollPane(
                    messageBox,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                );
        add(scroll, BorderLayout.CENTER);

        messageInputPanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        messageSendButton = new JButton("送信");
        messageSendButton.addActionListener(this);
        messageInputPanel.add(messageInput, BorderLayout.CENTER);
        messageInputPanel.add(messageSendButton, BorderLayout.EAST);
        add(messageInputPanel, BorderLayout.SOUTH);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        this.username = username;

        // チャットクライアントとしての機能
        this.client = new Client(this);
        this.client.start();
    }

    private void addMessage(String text, String username) {
        messageCount++;
        if (messageCount > MINIMAM_MESSAGE_NUMBER) {
            gridLayout.setRows(messageCount);
        }
        messageBox.add(new MessagePanel(text, username));

        // スクロールの調整
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JScrollBar scrollBar = scroll.getVerticalScrollBar();
                scrollBar.setValue(scrollBar.getMaximum());
            }
        });

        // 新しいメッセージの再表示
        messageBox.revalidate();
        messageBox.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messageSendButton) {
            String text = messageInput.getText();
            if (text.equals("")) return;
            addMessage(text, this.username);
            this.client.send(text, this.username);
            messageInput.setText("");
        }
    }

    @Override
    public void received(String original) {
        String strs[] = original.split(";", 2);
        for (String s : strs) {
            System.out.println(s);
        }
        if (strs.length != 2) {
            System.out.println("invalid message: cannot find username");
            return;
        }
        addMessage(strs[1], strs[0]);
    }
}
