import org.snmp4j.Snmp;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Get extends SNMPPrepare {

    public List<SnmpResult> doGet(List<String> oids, String ip) throws IOException {
        List<SnmpResult> results = new ArrayList<>();
        String community = "public";

        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();

        CommunityTarget target = createTarget(ip, community);
        PDU pdu = new PDU();
        for (String oid : oids) {
            pdu.add(new VariableBinding(new OID(oid)));
        }
        pdu.setType(PDU.GET);

        ResponseEvent response = snmp.send(pdu, target);
        if (response.getResponse() != null) {
            for (VariableBinding vb : response.getResponse().getVariableBindings()) {
                String oid = vb.getOid().toString();
                String name = MIBInfoDisplay.lookupName(oid);
                String value = vb.getVariable().toString();
                results.add(new SnmpResult(oid, name, value));
            }
        } else {
            System.out.println("Error: No response from SNMP agent.");
        }
        snmp.close();
        return results;
    }
}
