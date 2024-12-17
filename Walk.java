import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
 
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
 
public class Walk {
    public static Map<String, String> doWalk(String tableOid, String ip) throws IOException {
 
        String address = "udp:" + ip + "/161";
        String community = "public";
 
        CommunityTarget target = new CommunityTarget();
        target.setAddress(GenericAddress.parse(address));
        target.setCommunity(new OctetString(community));
        target.setTimeout(1000);
        target.setVersion(SnmpConstants.version2c);
 
        Map<String, String> result = new TreeMap<>();
        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();
 
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.getSubtree(target, new OID(tableOid));
        if (events == null || events.size() == 0) {
            System.out.println("Error: Unable to read table...");
            return result;
        }
 
        for (TreeEvent event : events) {
            if (event == null) {
                continue;
            }
            if (event.isError()) {
                System.out.println("Error: table OID [" + tableOid + "] " + event.getErrorMessage());
                continue;
            }
 
            VariableBinding[] varBindings = event.getVariableBindings();
            if (varBindings == null || varBindings.length == 0) {
                continue;
            }
            for (VariableBinding varBinding : varBindings) {
                if (varBinding == null) {
                    continue;
                }
 
                result.put("." + varBinding.getOid().toString(), varBinding.getVariable().toString());
            }
        }
        snmp.close();
 
        return result;
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        try {
            Map<String, String> result = doWalk(input, "localhost");
            result.forEach((oid, value) -> System.out.println(oid + " : " + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}