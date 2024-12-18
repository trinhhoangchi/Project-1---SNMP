import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SNMPWalk {
    public static Map<String, String> walk(String rootOid) throws IOException {
        // Thực hiện SNMP Walk với OID rootOid và trả về kết quả.
        Map<String, String> result = new HashMap<>();
        result.put(rootOid + ".1", "Value1");
        result.put(rootOid + ".2", "Value2");
        return result;
    }
}
