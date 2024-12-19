import java.awt.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;

public class MIBBrowser extends JFrame {
    private JTree mibTree;          // Cây MIB hiển thị thông tin cấu trúc MIB
    private JTable infoTable, resultTable;  // Bảng thông tin MIB và bảng kết quả từ SNMP
    private JTextField oidField;    // Trường văn bản để nhập OID
    private JLabel oidDisplayLabel; // Nhãn hiển thị OID hiện tại
    private ResultFrame resultFrame; // Cửa sổ kết quả SNMP

    public MIBBrowser() {
        setTitle("MIB-2 Browser");
        setSize(1000, 600);  // Kích thước cửa sổ
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Đóng ứng dụng khi cửa sổ bị đóng
        setLayout(new BorderLayout()); // Thiết lập Layout chính của cửa sổ

        // Tạo panel chứa các nút và trường nhập OID
        JPanel oidPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        oidPanel.setPreferredSize(new Dimension(1000, 60)); // Đặt kích thước cho panel

        JLabel oidLabel = new JLabel("Enter OID: ");
        oidField = new JTextField(15);
        JButton walkButton = new JButton("Walk");   // Nút Walk
        JButton getButton = new JButton("Get");    // Nút Get
        oidDisplayLabel = new JLabel("OID: Not connected"); // Nhãn hiển thị OID hiện tại

        // Thêm các thành phần vào panel
        oidPanel.add(oidLabel);
        oidPanel.add(oidField);
        oidPanel.add(walkButton);
        oidPanel.add(getButton);
        oidPanel.add(oidDisplayLabel);

        // Tạo cây MIB và lắng nghe sự kiện chọn nút trong cây
        DefaultMutableTreeNode root = MIBTree.createMIBTree();
        mibTree = new JTree(root);
        mibTree.addTreeSelectionListener(e -> MIBInfoDisplay.displayNodeInfo(e.getPath(), infoTable, oidField));

        // Tạo thanh cuộn cho cây MIB
        JScrollPane treeScrollPane = new JScrollPane(mibTree);
        treeScrollPane.setPreferredSize(new Dimension(250, 300));

        // Tạo bảng thông tin MIB
        String[] infoColumns = {"Attribute", "Value"};
        infoTable = new JTable(new DefaultTableModel(infoColumns, 0));
        JScrollPane infoScroll = new JScrollPane(infoTable);

        // Tạo split pane cho cây và bảng thông tin
        JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treeScrollPane, infoScroll); 
        leftSplit.setDividerLocation(300);

        // Tạo bảng kết quả từ SNMP
        String[] resultColumns = {"OID", "Value", "Type", "Name"};
        resultTable = new JTable(new DefaultTableModel(resultColumns, 0));
        JScrollPane resultScroll = new JScrollPane(resultTable);

        // Tạo split pane cho toàn bộ cửa sổ
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplit, resultScroll);
        mainSplit.setDividerLocation(300);

        // Thêm panel và split pane vào cửa sổ chính
        add(oidPanel, BorderLayout.NORTH);
        add(mainSplit, BorderLayout.CENTER);

        // Khởi tạo ResultFrame
        resultFrame = new ResultFrame();

        setVisible(true); // Hiển thị cửa sổ

        // Gán sự kiện cho nút Walk và Get
        walkButton.addActionListener(e -> onWalkButtonPressed());
        getButton.addActionListener(e -> onGetButtonPressed());
    }

    // Xử lý sự kiện khi nhấn nút Walk
    private void onWalkButtonPressed() {
        String rootOid = oidField.getText(); // Lấy OID người dùng nhập
        if (rootOid.isEmpty()) {
            oidDisplayLabel.setText("OID: Please enter a valid OID.");
            return;
        } else {
            oidDisplayLabel.setText("Root OID: " + rootOid); // Hiển thị OID đã nhập
        }
    
        try {
            // Thực hiện SNMP Walk và lấy kết quả
            Map<String, String> result = SNMPWalk.walk(rootOid);
    
            // Hiển thị kết quả trên bảng resultTable
            DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();
            tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
    
            for (Map.Entry<String, String> entry : result.entrySet()) {
                tableModel.addRow(new Object[]{entry.getKey(), entry.getValue(), "Type Placeholder", "Name Placeholder"});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error performing SNMP walk: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE); // Hiển thị thông báo lỗi nếu có
        }
    }
    

    // Xử lý sự kiện khi nhấn nút Get
    private void onGetButtonPressed() {
        String oid = oidField.getText(); // Lấy OID người dùng nhập
        if (oid.isEmpty()) {
            oidDisplayLabel.setText("OID: Please enter a valid OID.");
            return;
        } else {
            oidDisplayLabel.setText("OID: " + oid); // Hiển thị OID đã nhập
        }
    
        try {
            // Thực hiện SNMP Get và lấy kết quả
            Map<String, String> result = SNMPGet.get(oid);
    
            // Hiển thị kết quả trên bảng resultTable
            DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();
            tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
    
            for (Map.Entry<String, String> entry : result.entrySet()) {
                tableModel.addRow(new Object[]{entry.getKey(), entry.getValue(), "Type Placeholder", "Name Placeholder"});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error performing SNMP get: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE); // Hiển thị thông báo lỗi nếu có
        }
    }
    

    // Phương thức main để chạy ứng dụng
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MIBBrowser::new); // Chạy chương trình trong một luồng sự kiện Swing
    }
}
