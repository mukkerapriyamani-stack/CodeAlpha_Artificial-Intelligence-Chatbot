import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChatbotApp extends JFrame implements ActionListener {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Map<String, String> faq;

    public ChatbotApp() {
        setTitle("Java AI Chatbot");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        faq = new HashMap<>();
        faq.put("hello", "Hello! How can I help you?");
        faq.put("hi", "Hi there!");
        faq.put("your name", "I am a Java chatbot.");
        faq.put("what is java", "Java is an object-oriented programming language.");
        faq.put("what can you do", "I can answer basic FAQs using rule-based NLP logic.");
        faq.put("bye", "Goodbye! Have a nice day.");

        chatArea.append("Bot: Hello! I am your Java chatbot.\n");
        chatArea.append("Bot: Type 'bye' to exit.\n\n");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");

        String normalized = userText.toLowerCase();
        String response = "Sorry, I didn't understand that.";

        for (String key : faq.keySet()) {
            if (normalized.contains(key)) {
                response = faq.get(key);
                break;
            }
        }

        chatArea.append("Bot: " + response + "\n\n");
        inputField.setText("");

        if (normalized.equals("bye") || normalized.equals("exit")) {
            JOptionPane.showMessageDialog(this, "Goodbye!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatbotApp::new);
    }
}