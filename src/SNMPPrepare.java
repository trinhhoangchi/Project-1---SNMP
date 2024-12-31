import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.mp.SnmpConstants;

public class SNMPPrepare {
    protected CommunityTarget createTarget(String ip, String community) {
        CommunityTarget target = new CommunityTarget();
        target.setAddress(GenericAddress.parse("udp:" + ip + "/161"));
        target.setCommunity(new OctetString(community));
        target.setTimeout(1000);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }
}
