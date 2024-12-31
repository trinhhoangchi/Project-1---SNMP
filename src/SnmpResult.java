

public class SnmpResult {

    private final String oid;
    private final String name;
    private final String value;

    public SnmpResult(String oid, String name, String value) {
        this.oid = oid;
        this.name = name;
        this.value = value;
    }

    public String getOid() {
        return oid;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("OID: %s, Name: %s, Value: %s", oid, name, value);
    }
}