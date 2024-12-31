    import org.snmp4j.CommunityTarget;
    import org.snmp4j.Snmp;
    import org.snmp4j.TransportMapping;
    import org.snmp4j.smi.OID;
    import org.snmp4j.smi.UdpAddress;
    import org.snmp4j.smi.VariableBinding;
    import org.snmp4j.transport.DefaultUdpTransportMapping;
    import org.snmp4j.util.DefaultPDUFactory;
    import org.snmp4j.util.TreeEvent;
    import org.snmp4j.util.TreeUtils;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

    public class Walk extends SNMPPrepare {

        public  List<SnmpResult> doWalk(String tableOid, String ip, String community) throws IOException {
            List<SnmpResult> results = new ArrayList<>();
  

            TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();

            CommunityTarget target = createTarget(ip, community);
            TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
            List<TreeEvent> events = treeUtils.getSubtree(target, new OID(tableOid));

            if (events != null) {
                for (TreeEvent event : events) {
                    if (event != null && !event.isError()) {
                        VariableBinding[] varBindings = event.getVariableBindings();
                        if (varBindings != null) {
                            for (VariableBinding vb : varBindings) {
                                String oid = vb.getOid().toString();
                                String name = MIBInfoDisplay.lookupName(oid);
                                String value = vb.getVariable().toString();
                                results.add(new SnmpResult(oid, name, value));
                            }
                        }
                    }
                }
            } else {
                System.out.println("Error: Unable to retrieve subtree.");
            }
            snmp.close();
            return results;
        }
    }
