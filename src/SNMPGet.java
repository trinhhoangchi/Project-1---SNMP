import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SNMPGet {
    public static Map<String, String> get(String oid) throws IOException {
        // Thực hiện SNMP Get với OID và trả về kết quả
        Map<String, String> result = new HashMap<>();
        result.put(oid, "Value from SNMP GET");
        return result;
    }
}
