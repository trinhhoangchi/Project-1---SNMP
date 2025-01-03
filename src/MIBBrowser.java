import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class MIBBrowser extends JFrame {
    private JTree mibTree;
    private JTable infoTable, resultTable;
    private JTextField oidField;
    private JTextField ipField;
    private JTextField csField;
    private JLabel oidDisplayLabel;


    public MIBBrowser() {
        setTitle("MIB-2 Browser");
        setSize(1000, 600);
        setLocation(140 ,40);
//-----------------
        JPanel oidPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        oidPanel.setPreferredSize(new Dimension(1000, 50));
        JLabel oidLabel = new JLabel("  OID: ");
        oidField = new JTextField(15);

        JLabel ipLabel = new JLabel("IP: ");
        ipField = new JTextField(15);

        JLabel csLabel = new JLabel("Community String: ");
        csField = new JTextField(20);

        JButton walkButton = new JButton("Walk");
        JButton getButton = new JButton("Get");
        oidDisplayLabel = new JLabel("OID: Not connected");

        oidPanel.add(oidLabel);
        oidPanel.add(oidField);
        oidPanel.add(ipLabel);
        oidPanel.add(ipField);
        oidPanel.add(csLabel);
        oidPanel.add(csField);
        oidPanel.add(walkButton);
        oidPanel.add(getButton);
        oidPanel.add(oidDisplayLabel);
//--------------- Tạo cây MIB
        DefaultMutableTreeNode root = MIBTree.createMIBTree();
        mibTree = new JTree(root);

        //Lắng nghe người dùng chọn 1 nút trên cây 
        mibTree.addTreeSelectionListener(event -> MIBInfoDisplay.displayNodeInfo(event.getPath(), infoTable, oidField));
        JScrollPane treeScrollPane = new JScrollPane(mibTree);
        treeScrollPane.setPreferredSize(new Dimension(250, 300));

//---------------- Tạo bảng infoTable hiển thị thông tin của nút
    String[] infoColumns = {"Attribute", "Value"};
        infoTable = new JTable(new DefaultTableModel(infoColumns, 0));
        JScrollPane infoScroll = new JScrollPane(infoTable);

        JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treeScrollPane, infoScroll); 
        leftSplit.setDividerLocation(300);

        //tự động xuống dòng
        infoTable.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
        infoTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

//----------------- Tạo bảng hiển thị kết quả         
        String[] resultColumns = {"OID", "Name", "Value"};
        resultTable = new JTable(new DefaultTableModel(resultColumns, 0));
        JScrollPane resultScroll = new JScrollPane(resultTable);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplit, resultScroll);
        mainSplit.setDividerLocation(300);

        add(oidPanel, BorderLayout.NORTH);
        add(mainSplit, BorderLayout.CENTER);

        setVisible(true);

        walkButton.addActionListener(e -> onWalkButtonPressed());
        getButton.addActionListener(e -> onGetButtonPressed());
    }

    private void onWalkButtonPressed() {
        String rootOid = oidField.getText();
        String ip = ipField.getText();
        String community = csField.getText();
        if (rootOid.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter a valid OID.",
                                        "Input Error",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ip.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid IP.",
                                         "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (community.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Community String.", 
                                            "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        oidDisplayLabel.setText("Root OID: " + rootOid);

        try {
            Walk walk = new Walk();
            List<SnmpResult> result = walk.doWalk(rootOid, ip, community); 
            // hiển thị lỗi nếu kết quả trống
            if (result.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No data returned. Check your OID, IP and Community String.",
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();
            tableModel.setRowCount(0);

            for (SnmpResult entry : result) {
                tableModel.addRow(new Object[]{entry.getOid(), entry.getName(), entry.getValue()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error performing SNMP walk: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onGetButtonPressed() {
        String oid = oidField.getText();
        String ip = ipField.getText();
        String community = csField.getText();
        if (oid.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter a valid OID.",
                                            "Input Error",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ip.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid IP.", 
                                            "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (community.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Community String.", 
                                            "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        oidDisplayLabel.setText("OID: " + oid);

        try {
            Get get = new Get(); 
            List<SnmpResult> result = get.doGet(List.of(oid), ip,community);
            // hiển thị lỗi nếu kết quả trống
            if (result.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No data returned. Check your OID,IP and Community String.",
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }           

            DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();
            tableModel.setRowCount(0);

            for (SnmpResult entry : result) {
                tableModel.addRow(new Object[]{entry.getOid(), entry.getName(), entry.getValue()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error performing SNMP get: " + e.getMessage(),
                                            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MIBBrowser::new);
    }
}
