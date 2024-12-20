import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultFrame extends JFrame {
    private JTable resultTable;

    public ResultFrame() {
        setTitle("SNMP Walk Results");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"OID", "Value", "Type", "Name"};
        resultTable = new JTable(new DefaultTableModel(columns, 0));
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void showResult(List<Walk.SnmpResult> result) {
        DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();
        tableModel.setRowCount(0); // Clear previous results

        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Walk.SnmpResult entry : result) {
                tableModel.addRow(new Object[]{entry.getOid(), entry.getValue(), entry.getType(), entry.getName()});
            }
        }
        setVisible(true); // Show the result frame
    }
}  
