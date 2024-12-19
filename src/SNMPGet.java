import java.util.HashMap;
import java.util.Map;

public class SNMPGet {
    public static Map<String, String> get(String oid) throws Exception {
        Map<String, String> result = new HashMap<>();

        // Thực hiện SNMP Get (cần tích hợp thư viện SNMP tương ứng)
        // Ví dụ giả lập kết quả:
        result.put(oid, "Single Value");

        return result;
    }
}
