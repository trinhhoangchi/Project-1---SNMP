import javax.swing.tree.DefaultMutableTreeNode;

public class MIBTree {

    // Tạo cây MIB
    public static DefaultMutableTreeNode createMIBTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("mib");

        DefaultMutableTreeNode system = new DefaultMutableTreeNode("system");
        root.add(system);
        system.add(new DefaultMutableTreeNode("sysDescr"));
        system.add(new DefaultMutableTreeNode("sysObjectID"));
        system.add(new DefaultMutableTreeNode("sysUpTime"));
        system.add(new DefaultMutableTreeNode("sysContact"));
        system.add(new DefaultMutableTreeNode("sysName"));
        system.add(new DefaultMutableTreeNode("sysLocation"));
        system.add(new DefaultMutableTreeNode("sysServices"));

        DefaultMutableTreeNode interfaces = new DefaultMutableTreeNode("interfaces");
        root.add(interfaces);
        DefaultMutableTreeNode ifNumber = new DefaultMutableTreeNode("ifNumber");
        interfaces.add(ifNumber);
        DefaultMutableTreeNode ifTable = new DefaultMutableTreeNode("ifTable");
        interfaces.add(ifTable);
        DefaultMutableTreeNode ifEntry = new DefaultMutableTreeNode("ifEntry");
        ifTable.add(ifEntry);

        ifEntry.add(new DefaultMutableTreeNode("ifDescr"));
        ifEntry.add(new DefaultMutableTreeNode("ifType"));
        ifEntry.add(new DefaultMutableTreeNode("ifMtu"));
        ifEntry.add(new DefaultMutableTreeNode("ifSpeed"));
        ifEntry.add(new DefaultMutableTreeNode("ifPhysAddress"));
        ifEntry.add(new DefaultMutableTreeNode("ifAdminStatus"));
        ifEntry.add(new DefaultMutableTreeNode("ifOperStatus"));
        ifEntry.add(new DefaultMutableTreeNode("ifLastChange"));
        ifEntry.add(new DefaultMutableTreeNode("ifInOctets"));
        ifEntry.add(new DefaultMutableTreeNode("ifInUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifInNUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifInDiscards"));
        ifEntry.add(new DefaultMutableTreeNode("ifInErrors"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutOctets"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutNUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutDiscards"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutErrors"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutQLen"));
        ifEntry.add(new DefaultMutableTreeNode("ifSpecific"));

        return root;
    }

    // Tìm tên nút tương ứng với OID
    public static String lookupName(DefaultMutableTreeNode node, String oid) {
        if (node == null || oid == null) {
            return "Unknown";
        }

        // Kiểm tra nếu OID khớp với tên của node (không chỉ so sánh tên, mà so sánh đúng với phần OID)
        if (oid.contains(node.toString())) {
            return node.toString();
        }

        // Kiểm tra các nút con
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            String result = lookupName(child, oid);
            if (!result.equals("Unknown")) {
                return result;
            }
        }

        return "Unknown";
    }
}
