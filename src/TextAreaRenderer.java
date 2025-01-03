import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

public class TextAreaRenderer extends JTextArea implements TableCellRenderer {
    public TextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(new Font("Arial", Font.PLAIN, 12));
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value == null ? "" : value.toString());
        int height = (int) getPreferredSize().getHeight();
        table.setRowHeight(row, Math.max(height, 40)); // Đảm bảo chiều cao tối thiểu là 40
        return this;
    }
}
