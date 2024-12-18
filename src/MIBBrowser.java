import java.awt.*;
import java.io.IOException;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.*;

public class MIBBrowser extends JFrame {
    private JTree mibTree;
    private JTable infoTable, resultTable;
    private JTextField oidField;  // TextField for Root OID input
    private JLabel oidDisplayLabel;  // Label to display the entered Root OID
    private JTextArea resultArea;  // Area to display the SNMP walk result
    private JFrame resultFrame;   // New frame to display the SNMP walk results

    public MIBBrowser() {
        setTitle("MIB-2 Browser");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // North Panel - Root OID input and control button
        JPanel oidPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        oidPanel.setPreferredSize(new Dimension(1000, 60));  // Height of the OID input panel

        JLabel oidLabel = new JLabel("Enter Root OID: ");
        oidField = new JTextField(15);  // A text field to input the Root OID
        JButton walkButton = new JButton("Walk");

        // Label to display the entered Root OID
        oidDisplayLabel = new JLabel("Root OID: Not connected");

        // Add components to the panel
        oidPanel.add(oidLabel);
        oidPanel.add(oidField);
        oidPanel.add(walkButton);
        oidPanel.add(oidDisplayLabel);  // Display the Root OID label

        // Create MIB tree and info table
        DefaultMutableTreeNode root = createMIBTree();
        mibTree = new JTree(root);
        mibTree.addTreeSelectionListener(e -> displayNodeInfo(e.getPath()));

        JScrollPane treeScrollPane = new JScrollPane(mibTree);
        treeScrollPane.setPreferredSize(new Dimension(250, 300));  // Adjust height for the tree

        String[] infoColumns = {"Attribute", "Value"};
        infoTable = new JTable(new DefaultTableModel(infoColumns, 0));
        JScrollPane infoScroll = new JScrollPane(infoTable);

        // Thay đổi tỉ lệ giữa các cột
        TableColumn column1 = infoTable.getColumnModel().getColumn(0); // Cột "Attribute"
        TableColumn column2 = infoTable.getColumnModel().getColumn(1); // Cột "Value"
        column1.setPreferredWidth(150);  // Chiều rộng cột "Attribute"
        column2.setPreferredWidth(400);  // Chiều rộng cột "Value"

        // Left Panel - Tree and Info Table
        JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treeScrollPane, infoScroll);
        leftSplit.setDividerLocation(300);

        // Create Result Table
        String[] resultColumns = {"Name/OID", "Value", "Type", "Name", "IP:Port"};
        resultTable = new JTable(new DefaultTableModel(resultColumns, 0));
        JScrollPane resultScroll = new JScrollPane(resultTable);

        // Main Split Panel - Left Panel and Result Panel
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplit, resultScroll);
        mainSplit.setDividerLocation(300);

        // Add components to the frame
        add(oidPanel, BorderLayout.NORTH);  // Add the top panel
        add(mainSplit, BorderLayout.CENTER);  // Add the main split panel

        setVisible(true);

        // Add ActionListener to the walkButton
        walkButton.addActionListener(e -> onWalkButtonPressed());
    }

    private void onWalkButtonPressed() {
        String rootOid = oidField.getText();

        if (rootOid.isEmpty()) {
            oidDisplayLabel.setText("Root OID: Please enter a valid OID.");
            return;
        } else {
            oidDisplayLabel.setText("Root OID: " + rootOid);
        }

        try {
            Map<String, String> result = Walk.Walk(rootOid);  // Using rootOid as input for Walk

            // Show results in a new frame
            showResultFrame(result);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error performing SNMP walk: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showResultFrame(Map<String, String> result) {
        if (resultFrame == null) {
            resultFrame = new JFrame("SNMP Walk Results");
            resultFrame.setSize(600, 400);
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            resultArea = new JTextArea();
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);

            resultFrame.add(scrollPane);
        }

        // Clear previous results
        resultArea.setText("");

        // Display new results
        if (result.isEmpty()) {
            resultArea.append("No data found.\n");
        } else {
            for (Map.Entry<String, String> entry : result.entrySet()) {
                resultArea.append(entry.getKey() + " : " + entry.getValue() + "\n");
            }
        }

        resultFrame.setVisible(true);
    }

    private DefaultMutableTreeNode createMIBTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("mib-2");
        DefaultMutableTreeNode system = new DefaultMutableTreeNode("system");
        root.add(system);
        
        // Thêm các mục cho system
        system.add(new DefaultMutableTreeNode("sysDescr"));
        system.add(new DefaultMutableTreeNode("sysObjectID"));
        system.add(new DefaultMutableTreeNode("sysUpTime"));
        system.add(new DefaultMutableTreeNode("sysContact"));
        system.add(new DefaultMutableTreeNode("sysName"));
        system.add(new DefaultMutableTreeNode("sysLocation"));
        system.add(new DefaultMutableTreeNode("sysServices"));
        
        DefaultMutableTreeNode interfaces = new DefaultMutableTreeNode("interfaces");
        root.add(interfaces);
        
        // Thêm các mục cho interfaces
        interfaces.add(new DefaultMutableTreeNode("ifDescr"));
        interfaces.add(new DefaultMutableTreeNode("ifType"));
        interfaces.add(new DefaultMutableTreeNode("ifMtu"));
        interfaces.add(new DefaultMutableTreeNode("ifSpeed"));
        interfaces.add(new DefaultMutableTreeNode("ifPhysAddress"));
        interfaces.add(new DefaultMutableTreeNode("ifAdminStatus"));
        interfaces.add(new DefaultMutableTreeNode("ifOperStatus"));
        interfaces.add(new DefaultMutableTreeNode("ifLastChange"));
        interfaces.add(new DefaultMutableTreeNode("ifInOctets"));
        interfaces.add(new DefaultMutableTreeNode("ifInUcastPkts"));
        interfaces.add(new DefaultMutableTreeNode("ifInNUcastPkts"));
        interfaces.add(new DefaultMutableTreeNode("ifInDiscards"));
        interfaces.add(new DefaultMutableTreeNode("ifInErrors"));
        interfaces.add(new DefaultMutableTreeNode("ifOutOctets"));
        interfaces.add(new DefaultMutableTreeNode("ifOutUcastPkts"));
        interfaces.add(new DefaultMutableTreeNode("ifOutNUcastPkts"));
        interfaces.add(new DefaultMutableTreeNode("ifOutDiscards"));
        interfaces.add(new DefaultMutableTreeNode("ifOutErrors"));
        interfaces.add(new DefaultMutableTreeNode("ifOutQLen"));
        
        return root;
    }

    private void displayNodeInfo(TreePath path) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        String nodeName = selectedNode.toString();

        // MIB data for nodes
        String[][] mibInfo = {
            // Dữ liệu hệ thống (sys)
            {"system", "1.3.6.1.2.1.1", "", ""},
            {"sysDescr", "1.3.6.1.2.1.1.1.0", "DisplayString", "Mô tả hệ thống (tên hệ điều hành, phiên bản kernel)"},
            {"sysObjectID", "1.3.6.1.2.1.1.2.0", "ObjectIdentifier", "Định danh của đối tượng hệ thống"},
            {"sysUpTime", "1.3.6.1.2.1.1.3.0", "TimeTicks", "Thời gian hệ thống đã hoạt động kể từ lần khởi động cuối cùng"},
            {"sysContact", "1.3.6.1.2.1.1.4.0", "DisplayString", "Thông tin liên hệ của quản trị viên hệ thống"},
            {"sysName", "1.3.6.1.2.1.1.5.0", "DisplayString", "Tên của hệ thống"},
            {"sysLocation", "1.3.6.1.2.1.1.6.0", "DisplayString", "Vị trí của hệ thống"},
            {"sysServices", "1.3.6.1.2.1.1.7.0", "Integer", "Các dịch vụ được cung cấp bởi hệ thống"},
            
            // Dữ liệu giao diện
            {"Interfaces", "1.3.6.1.2.1.2", "", ""},
            {"ifNumber", "1.3.6.1.2.1.2.1", "Integer", "Số lượng interfaces mạng trên thiết bị"},
            {"ifTable", "1.3.6.1.2.1.2.2", "Table", "List of interface entries"},
            {"ifIndex", "1.3.6.1.2.1.2.2.1.1", "Integer", "Chỉ số duy nhất xác định mỗi interfaces mạng"},
            {"ifDescr", "1.3.6.1.2.1.2.2.1.2", "DisplayString", "Mô tả về interfaces, như tên của interfaces (vd: eth0, lo)"},
            {"ifType", "1.3.6.1.2.1.2.2.1.3", "Integer", "Loại interfaces (vd: Ethernet, loopback)"},
            {"ifMtu", "1.3.6.1.2.1.2.2.1.4", "Integer", "Kích thước đơn vị truyền tải tối đa (MTU) của interfaces"},
            {"ifSpeed", "1.3.6.1.2.1.2.2.1.5", "Gauge32", "Tốc độ truyền tải của interfaces (tính bằng bits trên giây)"},
            {"ifPhysAddress", "1.3.6.1.2.1.2.2.1.6", "PhysAddress", "Địa chỉ vật lý (MAC) của interfaces"},
            {"ifAdminStatus", "1.3.6.1.2.1.2.2.1.7", "Integer", "Trạng thái hành chính của interfaces (up, down, testing)"},
            {"ifOperStatus", "1.3.6.1.2.1.2.2.1.8", "Integer", "Trạng thái hoạt động của interfaces (up, down, testing)"},
            {"ifLastChange", "1.3.6.1.2.1.2.2.1.9", "TimeTicks", "Thời điểm cuối cùng khi interfaces thay đổi trạng thái"},
            {"ifInOctets", "1.3.6.1.2.1.2.2.1.10", "Counter32", "Số lượng octet đã nhận trên interfaces"},
            {"ifInUcastPkts", "1.3.6.1.2.1.2.2.1.11", "Counter32", "Số lượng gói unicast đã nhận trên interfaces"},
            {"ifInNUcastPkts", "1.3.6.1.2.1.2.2.1.12", "Counter32", "Số lượng gói multicast và broadcast đã nhận trên interfaces"},
            {"ifInDiscards", "1.3.6.1.2.1.2.2.1.13", "Counter32", "Số lượng gói nhận bị bỏ qua"},
            {"ifInErrors", "1.3.6.1.2.1.2.2.1.14", "Counter32", "Số lượng gói nhận gặp lỗi"},
            {"ifOutOctets", "1.3.6.1.2.1.2.2.1.16", "Counter32", "Số lượng octet đã gửi từ interfaces"},
            {"ifOutUcastPkts", "1.3.6.1.2.1.2.2.1.17", "Counter32", "Số lượng gói unicast đã gửi từ interfaces"},
            {"ifOutNUcastPkts", "1.3.6.1.2.1.2.2.1.18", "Counter32", "Số lượng gói multicast và broadcast đã gửi từ interfaces"},
            {"ifOutDiscards", "1.3.6.1.2.1.2.2.1.19", "Counter32", "Số lượng gói gửi bị bỏ qua"},
            {"ifOutErrors", "1.3.6.1.2.1.2.2.1.20", "Counter32", "Số lượng gói gửi gặp lỗi"},
            {"ifOutQLen", "1.3.6.1.2.1.2.2.1.21", "Gauge32", "Độ dài hàng đợi đầu ra của interfaces (số lượng gói tin đang chờ được gửi)"}
            
        };

        // Lấy DefaultTableModel của infoTable
        DefaultTableModel tableModel = (DefaultTableModel) infoTable.getModel();
        
        // Xóa dữ liệu cũ trong bảng
        tableModel.setRowCount(0);

        // Tìm thông tin cho node hiện tại và hiển thị nó theo chiều ngang
        for (String[] info : mibInfo) {
            if (info[0].equals(nodeName)) {
                // Thêm các thông tin vào bảng, mỗi dòng là một cột với thông tin khác nhau
                tableModel.addRow(new Object[]{"Name", info[0]});
                tableModel.addRow(new Object[]{"OID", info[1]});
                tableModel.addRow(new Object[]{"Syntax", info[2]});
                tableModel.addRow(new Object[]{"Description", info[3]});
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MIBBrowser::new);
    }
}
