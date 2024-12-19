import java.util.HashMap;
import java.util.Map;

public class SNMPWalk {
    public static Map<String, String> walk(String rootOid) throws Exception {
        Map<String, String> result = new HashMap<>();

        // Thực hiện SNMP Walk (cần tích hợp thư viện SNMP tương ứng)
        // Ví dụ giả lập kết quả:
        result.put(rootOid + ".1", "Value 1");
        result.put(rootOid + ".2", "Value 2");
        result.put(rootOid + ".3", "Value 3");

        return result;
    }
}
