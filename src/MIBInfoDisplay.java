import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class MIBInfoDisplay {
    public static final String[][] MIB_INFO = {
        {"system", "1.3.6.1.2.1.1", "", ""},
        {"sysDescr", "1.3.6.1.2.1.1.1.0", "DisplayString", "Mô tả hệ thống (tên hệ điều hành, phiên bản kernel)"},
        {"sysObjectID", "1.3.6.1.2.1.1.2.0", "ObjectIdentifier", "Định danh của đối tượng hệ thống"},
        {"sysUpTime", "1.3.6.1.2.1.1.3.0", "TimeTicks", "Thời gian hệ thống đã hoạt động kể từ lần khởi động cuối cùng"},
        {"sysContact", "1.3.6.1.2.1.1.4.0", "DisplayString", "Thông tin liên hệ của quản trị viên hệ thống"},
        {"sysName", "1.3.6.1.2.1.1.5.0", "DisplayString", "Tên của hệ thống"},
        {"sysLocation", "1.3.6.1.2.1.1.6.0", "DisplayString", "Vị trí của hệ thống"},
        {"sysServices", "1.3.6.1.2.1.1.7.0", "Integer", "Các dịch vụ được cung cấp bởi hệ thống"},
        
        {"interfaces", "1.3.6.1.2.1.2", "", ""},
        {"ifNumber", "1.3.6.1.2.1.2.1", "Integer", "Số lượng interfaces mạng trên thiết bị"},
        {"ifTable", "1.3.6.1.2.1.2.2", "Table", "List of interface entries"},
        {"ifEntry", "1.3.6.1.2.1.2.2.1", "", "An interface entry containing objects at the subnetwork layer and below for a particular interface."},
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
        {"ifOutUcastPkts", "1.3.6.1.2.2.1.17", "Counter32", "Số lượng gói unicast đã gửi từ interfaces"},
        {"ifOutNUcastPkts", "1.3.6.1.2.2.1.18", "Counter32", "Số lượng gói multicast và broadcast đã gửi từ interfaces"},
        {"ifOutDiscards", "1.3.6.1.2.2.1.19", "Counter32", "Số lượng gói gửi bị bỏ qua"},
        {"ifOutErrors", "1.3.6.1.2.2.1.20", "Counter32", "Số lượng gói gửi gặp lỗi"},
        {"ifOutQLen", "1.3.6.1.2.2.1.21", "Gauge32", "Độ dài hàng đợi đầu ra của interfaces (số lượng gói tin đang chờ được gửi)"},
        {"ifSpecific", "1.3.6.1.2.2.1.22", "Object Identifier", "Xác định giao diện cụ thể"}
    };


    public static void displayNodeInfo(TreePath path, JTable infoTable, JTextField oidField) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        String nodeName = selectedNode.toString();

        DefaultTableModel tableModel = (DefaultTableModel) infoTable.getModel();
        tableModel.setRowCount(0);


        for (String[] info : MIB_INFO) {
            if (info[0].equals(nodeName)) {
                tableModel.addRow(new Object[]{"Name", info[0]});
                tableModel.addRow(new Object[]{"OID", info[1]});
                tableModel.addRow(new Object[]{"Syntax", info[2]});
                tableModel.addRow(new Object[]{"Description", info[3]});
                oidField.setText(info[1]); // Gán giá trị OID vào trường nhập liệu
                break;
            }
        }
    }

    // Tìm tên dựa vào oid
    public static String lookupName(String oid) {
        if ( oid == null) {
            return "Unknown";
        }
        for (String[] info : MIB_INFO) {
                if(oid.equals(info[1]))
                return info[0];
            }
        return "Unknown";
    }
}
