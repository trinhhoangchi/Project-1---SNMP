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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Walk {

    public static class SnmpResult {
        private final String oid;
        private final String name;
        private final String type;
        private final String value;

        public SnmpResult(String oid, String name, String type, String value) {
            this.oid = oid;
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public String getOid() {
            return oid;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("OID: %s, Name: %s, Type: %s, Value: %s", oid, name, type, value);
        }
    }

    public static List<SnmpResult> doWalk(String tableOid, String ip) throws IOException {
        String address = "udp:" + ip + "/161";
        String community = "public";

        CommunityTarget target = new CommunityTarget();
        target.setAddress(GenericAddress.parse(address));
        target.setCommunity(new OctetString(community));
        target.setTimeout(1000);
        target.setVersion(SnmpConstants.version2c);

        List<SnmpResult> result = new ArrayList<>();
        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();

        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.getSubtree(target, new OID(tableOid));

        if (events == null || events.isEmpty()) {
            System.out.println("Error: Unable to read table...");
            return result;
        }

        for (TreeEvent event : events) {
            if (event == null || event.isError()) {
                System.out.println("Error: table OID [" + tableOid + "] " + (event != null ? event.getErrorMessage() : "Unknown error"));
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
                String oid = "." + varBinding.getOid().toString();
                String name = varBinding.getOid().toString(); // Placeholder for actual name lookup
                String type = varBinding.getVariable().getSyntaxString();
                String value = varBinding.getVariable().toString();

                result.add(new SnmpResult(oid, name, type, value));
            }
        }
        snmp.close();
        return result;
    }
}
 
