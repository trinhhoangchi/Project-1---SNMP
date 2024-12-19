import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class ResultFrame extends JFrame {
    private JTextArea resultArea;

    public ResultFrame() {
        setTitle("SNMP Walk Results");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void showResult(Map<String, String> result) {
        resultArea.setText(""); // Xóa nội dung cũ
    
        if (result.isEmpty()) {
            resultArea.append("No data found.\n");
        } else {
            for (Map.Entry<String, String> entry : result.entrySet()) {
                resultArea.append(entry.getKey() + " : " + entry.getValue() + "\n");
            }
        }
        setVisible(true); // Hiển thị cửa sổ
    }
    
}
