import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.mp.SnmpConstants;



  class SnmpResult {
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
