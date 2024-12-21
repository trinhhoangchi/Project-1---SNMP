import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;



public class MIBBrowser extends JFrame {
    private JTree mibTree;
    private JTable infoTable, resultTable;
    private JTextField oidField;
    private JLabel oidDisplayLabel;

    public MIBBrowser() {
        setTitle("MIB-2 Browser");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel oidPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        oidPanel.setPreferredSize(new Dimension(1000, 60));

        JLabel oidLabel = new JLabel("Enter OID: ");
        oidField = new JTextField(15);
        JButton walkButton = new JButton("Walk");
        JButton getButton = new JButton("Get");
        oidDisplayLabel = new JLabel("OID: Not connected");

        oidPanel.add(oidLabel);
        oidPanel.add(oidField);
        oidPanel.add(walkButton);
        oidPanel.add(getButton);
        oidPanel.add(oidDisplayLabel);

        DefaultMutableTreeNode root = MIBTree.createMIBTree();
        mibTree = new JTree(root);
        mibTree.addTreeSelectionListener(e -> MIBInfoDisplay.displayNodeInfo(e.getPath(), infoTable, oidField));
        JScrollPane treeScrollPane = new JScrollPane(mibTree);
        treeScrollPane.setPreferredSize(new Dimension(250, 300));

        String[] infoColumns = {"Attribute", "Value"};
        infoTable = new JTable(new DefaultTableModel(infoColumns, 0));
        JScrollPane infoScroll = new JScrollPane(infoTable);

        JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treeScrollPane, infoScroll); 
        leftSplit.setDividerLocation(300);

        String[] resultColumns = {"OID", "Value", "Type", "Name"};
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
        if (rootOid.isEmpty()) {
            oidDisplayLabel.setText("OID: Please enter a valid OID.");
            return;
        }
        oidDisplayLabel.setText("Root OID: " + rootOid);

        try {
            Walk walk = new Walk();
            List<SnmpResult> result = walk.doWalk(rootOid, "localhost"); // Use Walk's doWalk method
            DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();
            tableModel.setRowCount(0);

            for (SnmpResult entry : result) {
                tableModel.addRow(new Object[]{entry.getOid(), entry.getValue(), entry.getType(), entry.getName()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error performing SNMP walk: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onGetButtonPressed() {
        String oid = oidField.getText();
        if (oid.isEmpty()) {
            oidDisplayLabel.setText("OID: Please enter a valid OID.");
            return;
        }
        oidDisplayLabel.setText("OID: " + oid);

        try {
            Get get = new Get(); // Create an instance of Get
            List<SnmpResult> result = get.doGet(List.of(oid), "localhost"); // Use Get's doGet method
            DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();
            tableModel.setRowCount(0);

            for (SnmpResult entry : result) {
                tableModel.addRow(new Object[]{entry.getOid(), entry.getValue(), entry.getType(), entry.getName()});
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
