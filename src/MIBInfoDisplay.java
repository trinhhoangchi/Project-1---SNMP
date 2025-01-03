import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class MIBInfoDisplay {
    public static final String[][] MIB_INFO = {
        {"system", "1.3.6.1.2.1.1", "", ""},
        {"sysDescr", "1.3.6.1.2.1.1.1.0", "DisplayString", "Mô tả hệ thống (tên hệ điều hành, phiên bản kernel)"},
        {"sysObjectID", "1.3.6.1.2.1.1.2.0", "ObjectIdentifier", "Định danh của đối tượng hệ thống"},
        {"sysUpTime", "1.3.6.1.2.1.1.3.0", "TimeTicks", "Thời gian hệ thống đã hoạt động kể từ lần khởi động cuối cùng"},
        {"sysContact", "1.3.6.1.2.1.1.4.0", "DisplayString", "Thông tin liên hệ của quản trị viên hệ thống"},
        {"sysName", "1.3.6.1.2.1.1.5.0", "DisplayString", "Tên của hệ thống"},
        {"sysLocation", "1.3.6.1.2.1.1.6.0", "DisplayString", "Vị trí của hệ thống"},
        {"sysServices", "1.3.6.1.2.1.1.7.0", "Integer", "Các dịch vụ được cung cấp bởi hệ thống"},
        
        {"interfaces", "1.3.6.1.2.1.2", "", ""},
        {"ifNumber", "1.3.6.1.2.1.2.1", "Integer", "Số lượng interfaces mạng trên thiết bị"},
        {"ifTable", "1.3.6.1.2.1.2.2", "Table", "List of interface entries"},
        {"ifEntry", "1.3.6.1.2.1.2.2.1", "", "An interface entry containing objects at the subnetwork layer and below for a particular interface."},
        {"ifIndex", "1.3.6.1.2.1.2.2.1.1", "Integer", "Chỉ số duy nhất xác định mỗi interfaces mạng"},
        {"ifDescr", "1.3.6.1.2.1.2.2.1.2", "DisplayString", "Mô tả về interfaces, như tên của interfaces (vd: eth0, lo)"},
        {"ifType", "1.3.6.1.2.1.2.2.1.3", "Integer", "Loại interfaces (vd: Ethernet, loopback)"},
        {"ifMtu", "1.3.6.1.2.1.2.2.1.4", "Integer", "Kích thước đơn vị truyền tải tối đa (MTU) của interfaces"},
        {"ifSpeed", "1.3.6.1.2.1.2.2.1.5", "Gauge32", "Tốc độ truyền tải của interfaces (tính bằng bits trên giây)"},
        {"ifPhysAddress", "1.3.6.1.2.1.2.2.1.6", "PhysAddress", "Địa chỉ vật lý (MAC) của interfaces"},
        {"ifAdminStatus", "1.3.6.1.2.1.2.2.1.7", "Integer", "Trạng thái hành chính của interfaces (up, down, testing)"},
        {"ifOperStatus", "1.3.6.1.2.1.2.2.1.8", "Integer", "Trạng thái hoạt động của interfaces (up, down, testing)"},
        {"ifLastChange", "1.3.6.1.2.1.2.2.1.9", "TimeTicks", "Thời điểm cuối cùng khi interfaces thay đổi trạng thái"},
        {"ifInOctets", "1.3.6.1.2.1.2.2.1.10", "Counter32", "Số lượng octet đã nhận trên interfaces"},
        {"ifInUcastPkts", "1.3.6.1.2.1.2.2.1.11", "Counter32", "Số lượng gói unicast đã nhận trên interfaces"},
        {"ifInNUcastPkts", "1.3.6.1.2.1.2.2.1.12", "Counter32", "Số lượng gói multicast và broadcast đã nhận trên interfaces"},
        {"ifInDiscards", "1.3.6.1.2.1.2.2.1.13", "Counter32", "Số lượng gói nhận bị bỏ qua"},
        {"ifInErrors", "1.3.6.1.2.1.2.2.1.14", "Counter32", "Số lượng gói nhận gặp lỗi"},
        {"ifOutOctets", "1.3.6.1.2.1.2.2.1.16", "Counter32", "Số lượng octet đã gửi từ interfaces"},
        {"ifOutUcastPkts", "1.3.6.1.2.2.1.17", "Counter32", "Số lượng gói unicast đã gửi từ interfaces"},
        {"ifOutNUcastPkts", "1.3.6.1.2.2.1.18", "Counter32", "Số lượng gói multicast và broadcast đã gửi từ interfaces"},
        {"ifOutDiscards", "1.3.6.1.2.2.1.19", "Counter32", "Số lượng gói gửi bị bỏ qua"},
        {"ifOutErrors", "1.3.6.1.2.2.1.20", "Counter32", "Số lượng gói gửi gặp lỗi"},
        {"ifOutQLen", "1.3.6.1.2.2.1.21", "Gauge32", "Độ dài hàng đợi đầu ra của interfaces (số lượng gói tin đang chờ được gửi)"},
        {"ifSpecific", "1.3.6.1.2.2.1.22", "Object Identifier", "Xác định giao diện cụ thể"},

        {"at", "1.3.6.1.2.1.3", "", "Address Translation MIB"},
        {"atTable", "1.3.6.1.2.1.3.1", "Table", "Bảng chứa các mục ánh xạ địa chỉ"},
        {"atEntry", "1.3.6.1.2.1.3.1.1", "Entry", "Một mục ánh xạ địa chỉ cụ thể trong bảng ánh xạ"},
        {"atIfIndex", "1.3.6.1.2.1.3.1.1.1", "Integer", "Chỉ số giao diện ánh xạ; kết nối ánh xạ với một giao diện cụ thể"},
        {"atPhysAddress", "1.3.6.1.2.1.3.1.1.2", "PhysAddress", "Địa chỉ vật lý (MAC) của giao diện được ánh xạ"},
        {"atNetAddress", "1.3.6.1.2.1.3.1.1.3", "IpAddress", "Địa chỉ mạng (IP) của giao diện được ánh xạ"},        

        {"ip", "1.3.6.1.2.1.4", "", "Internet Protocol MIB"},
        {"ipForwarding", "1.3.6.1.2.1.4.1", "Integer", "Trạng thái chuyển tiếp IP (1: enabled, 2: disabled)"},
        {"ipDefaultTTL", "1.3.6.1.2.1.4.2", "Integer", "Thời gian sống (TTL) mặc định của gói IP"},
        {"ipInReceives", "1.3.6.1.2.1.4.3", "Counter32", "Tổng số gói IP nhận được từ các giao thức dưới tầng mạng"},
        {"ipInHdrErrors", "1.3.6.1.2.1.4.4", "Counter32", "Số lượng gói IP nhận có lỗi tiêu đề"},
        {"ipInAddrErrors", "1.3.6.1.2.1.4.5", "Counter32", "Số lượng gói nhận có địa chỉ đích không hợp lệ"},
        {"ipForwDatagrams", "1.3.6.1.2.1.4.6", "Counter32", "Số lượng gói IP được chuyển tiếp"},
        {"ipInUnknownProtos", "1.3.6.1.2.1.4.7", "Counter32", "Số lượng gói nhận có giao thức không xác định"},
        {"ipInDiscards", "1.3.6.1.2.1.4.8", "Counter32", "Số lượng gói IP bị loại bỏ do lỗi xử lý"},
        {"ipInDelivers", "1.3.6.1.2.1.4.9", "Counter32", "Số lượng gói IP được phân phối đến giao thức trên tầng mạng"},
        {"ipOutRequests", "1.3.6.1.2.1.4.10", "Counter32", "Số lượng gói IP được gửi từ giao thức tầng trên"},
        {"ipOutDiscards", "1.3.6.1.2.1.4.11", "Counter32", "Số lượng gói bị loại bỏ trước khi truyền do lỗi"},
        {"ipOutNoRoutes", "1.3.6.1.2.1.4.12", "Counter32", "Số lượng gói bị loại bỏ do không tìm thấy tuyến đường"},
        {"ipReasmTimeout", "1.3.6.1.2.1.4.13", "Integer", "Thời gian chờ cho quá trình tái lắp gói IP (tính bằng giây)"},
        {"ipReasmReqds", "1.3.6.1.2.1.4.14", "Counter32", "Số lượng gói IP được yêu cầu tái lắp"},
        {"ipReasmOKs", "1.3.6.1.2.1.4.15", "Counter32", "Số lượng gói IP được tái lắp thành công"},
        {"ipReasmFails", "1.3.6.1.2.1.4.16", "Counter32", "Số lượng gói IP tái lắp thất bại"},
        {"ipFragOKs", "1.3.6.1.2.1.4.17", "Counter32", "Số lượng gói IP được phân mảnh thành công"},
        {"ipFragFails", "1.3.6.1.2.1.4.18", "Counter32", "Số lượng gói IP phân mảnh thất bại"},
        {"ipFragCreates", "1.3.6.1.2.1.4.19", "Counter32", "Số lượng mảnh IP được tạo ra"},
        {"ipAddrTable", "1.3.6.1.2.1.4.20", "Table", "Bảng chứa các địa chỉ IP được cấu hình trên thiết bị"},
        {"ipRouteTable", "1.3.6.1.2.1.4.21", "Table", "Bảng định tuyến IP"},
        {"ipNetToMediaTable", "1.3.6.1.2.1.4.22", "Table", "Bảng ánh xạ địa chỉ IP sang địa chỉ vật lý (MAC)"},
        {"ipRoutingDiscards", "1.3.6.1.2.1.4.23", "Counter32", "Số lượng gói IP bị loại bỏ do lỗi định tuyến"},
    
        {"ipRouteTable", "1.3.6.1.2.1.4.21", "Table", "Bảng định tuyến IP"},
        {"ipRouteEntry", "1.3.6.1.2.1.4.21.1", "Entry", "Một mục cụ thể trong bảng định tuyến IP"},
        {"ipRouteDest", "1.3.6.1.2.1.4.21.1.1", "IpAddress", "Đích của tuyến đường trong bảng định tuyến"},
        {"ipRouteIfIndex", "1.3.6.1.2.1.4.21.1.2", "Integer", "Chỉ số giao diện sử dụng cho tuyến đường"},
        {"ipRouteMetric1", "1.3.6.1.2.1.4.21.1.3", "Integer", "Chi phí đo đạc đầu tiên của tuyến đường"},
        {"ipRouteMetric2", "1.3.6.1.2.1.4.21.1.4", "Integer", "Chi phí đo đạc thứ hai của tuyến đường"},
        {"ipRouteMetric3", "1.3.6.1.2.1.4.21.1.5", "Integer", "Chi phí đo đạc thứ ba của tuyến đường"},
        {"ipRouteMetric4", "1.3.6.1.2.1.4.21.1.6", "Integer", "Chi phí đo đạc thứ tư của tuyến đường"},
        {"ipRouteNextHop", "1.3.6.1.2.1.4.21.1.7", "IpAddress", "Địa chỉ của hop tiếp theo trong tuyến đường"},
        {"ipRouteType", "1.3.6.1.2.1.4.21.1.8", "Integer", "Loại tuyến đường (1: other, 2: reject, 3: local, 4: remote)"},
        {"ipRouteProto", "1.3.6.1.2.1.4.21.1.9", "Integer", "Giao thức tạo ra tuyến đường này (ví dụ: local, netmgmt)"},
        {"ipRouteAge", "1.3.6.1.2.1.4.21.1.10", "Integer", "Thời gian tuyến đường đã tồn tại (tính bằng giây)"},
        {"ipRouteMask", "1.3.6.1.2.1.4.21.1.11", "IpAddress", "Subnet mask liên quan đến tuyến đường"},
        {"ipRouteMetric5", "1.3.6.1.2.1.4.21.1.12", "Integer", "Chi phí đo đạc thứ năm của tuyến đường"},
        {"ipRouteInfo", "1.3.6.1.2.1.4.21.1.13", "Object Identifier", "Thông tin bổ sung liên quan đến tuyến đường"},
        {"ipNetToMediaTable", "1.3.6.1.2.1.4.22", "Table", "Bảng ánh xạ địa chỉ IP sang địa chỉ vật lý (MAC)"},
        {"ipNetToMediaEntry", "1.3.6.1.2.1.4.22.1", "Entry", "Một mục cụ thể trong bảng ánh xạ"},
        {"ipNetToMediaIfIndex", "1.3.6.1.2.1.4.22.1.1", "Integer", "Chỉ số giao diện sử dụng trong ánh xạ"},
        {"ipNetToMediaPhysAddress", "1.3.6.1.2.1.4.22.1.2", "Octet String", "Địa chỉ vật lý (MAC) ánh xạ với địa chỉ IP"},
        {"ipNetToMediaNetAddress", "1.3.6.1.2.1.4.22.1.3", "IpAddress", "Địa chỉ IP ánh xạ với địa chỉ vật lý (MAC)"},
        {"ipNetToMediaType", "1.3.6.1.2.1.4.22.1.4", "Integer", "Loại ánh xạ (ví dụ: other, invalid, dynamic, static)"},
        {"ipRoutingDiscards", "1.3.6.1.2.1.4.23", "Counter32", "Số lượng gói IP bị loại bỏ do lỗi định tuyến"},
        
        {"icmp", "1.3.6.1.2.1.5", "", "Internet Control Message Protocol (ICMP) MIB"},
        {"icmpInMsgs", "1.3.6.1.2.1.5.1", "Counter32", "Tổng số thông điệp ICMP nhận được"},
        {"icmpInErrors", "1.3.6.1.2.1.5.2", "Counter32", "Số lượng thông điệp ICMP nhận được nhưng có lỗi"},
        {"icmpInDestUnreachs", "1.3.6.1.2.1.5.3", "Counter32", "Số lượng thông điệp ICMP đích không thể tiếp cận được nhận"},
        {"icmpInTimeExcds", "1.3.6.1.2.1.5.4", "Counter32", "Số lượng thông điệp ICMP hết thời gian nhận được"},
        {"icmpInParmProbs", "1.3.6.1.2.1.5.5", "Counter32", "Số lượng thông điệp ICMP báo lỗi tham số nhận được"},
        {"icmpInSrcQuenchs", "1.3.6.1.2.1.5.6", "Counter32", "Số lượng thông điệp ICMP giảm tải nhận được"},
        {"icmpInRedirects", "1.3.6.1.2.1.5.7", "Counter32", "Số lượng thông điệp ICMP chuyển hướng nhận được"},
        {"icmpInEchos", "1.3.6.1.2.1.5.8", "Counter32", "Số lượng thông điệp ICMP echo (ping request) nhận được"},
        {"icmpInEchoReps", "1.3.6.1.2.1.5.9", "Counter32", "Số lượng thông điệp ICMP echo reply (ping reply) nhận được"},
        {"icmpInTimestamps", "1.3.6.1.2.1.5.10", "Counter32", "Số lượng thông điệp ICMP timestamp request nhận được"},
        {"icmpInTimestampReps", "1.3.6.1.2.1.5.11", "Counter32", "Số lượng thông điệp ICMP timestamp reply nhận được"},
        {"icmpInAddrMasks", "1.3.6.1.2.1.5.12", "Counter32", "Số lượng thông điệp ICMP address mask request nhận được"},
        {"icmpInAddrMaskReps", "1.3.6.1.2.1.5.13", "Counter32", "Số lượng thông điệp ICMP address mask reply nhận được"},
        {"icmpOutMsgs", "1.3.6.1.2.1.5.14", "Counter32", "Tổng số thông điệp ICMP được gửi"},
        {"icmpOutErrors", "1.3.6.1.2.1.5.15", "Counter32", "Số lượng thông điệp ICMP không thể gửi do lỗi"},
        {"icmpOutDestUnreachs", "1.3.6.1.2.1.5.16", "Counter32", "Số lượng thông điệp ICMP đích không thể tiếp cận được gửi"},
        {"icmpOutTimeExcds", "1.3.6.1.2.1.5.17", "Counter32", "Số lượng thông điệp ICMP hết thời gian gửi"},
        {"icmpOutParmProbs", "1.3.6.1.2.1.5.18", "Counter32", "Số lượng thông điệp ICMP báo lỗi tham số gửi đi"},
        {"icmpOutSrcQuenchs", "1.3.6.1.2.1.5.19", "Counter32", "Số lượng thông điệp ICMP giảm tải gửi đi"},
        {"icmpOutRedirects", "1.3.6.1.2.1.5.20", "Counter32", "Số lượng thông điệp ICMP chuyển hướng gửi đi"},
        {"icmpOutEchos", "1.3.6.1.2.1.5.21", "Counter32", "Số lượng thông điệp ICMP echo (ping request) gửi đi"},
        {"icmpOutEchoReps", "1.3.6.1.2.1.5.22", "Counter32", "Số lượng thông điệp ICMP echo reply (ping reply) gửi đi"},
        {"icmpOutTimestamps", "1.3.6.1.2.1.5.23", "Counter32", "Số lượng thông điệp ICMP timestamp request gửi đi"},
        {"icmpOutTimestampReps", "1.3.6.1.2.1.5.24", "Counter32", "Số lượng thông điệp ICMP timestamp reply gửi đi"},
        {"icmpOutAddrMasks", "1.3.6.1.2.1.5.25", "Counter32", "Số lượng thông điệp ICMP address mask request gửi đi"},
        {"icmpOutAddrMaskReps", "1.3.6.1.2.1.5.26", "Counter32", "Số lượng thông điệp ICMP address mask reply gửi đi"},

        {"tcp", "1.3.6.1.2.1.6", "", "Transmission Control Protocol (TCP) MIB"},
        {"tcpRtoAlgorithm", "1.3.6.1.2.1.6.1", "Integer", "Thuật toán ước tính lại thời gian chờ RTO (1: other, 2: constant, 3: rsre, 4: vanj)"},
        {"tcpRtoMin", "1.3.6.1.2.1.6.2", "Integer", "Thời gian chờ lại RTO nhỏ nhất (mili giây)"},
        {"tcpRtoMax", "1.3.6.1.2.1.6.3", "Integer", "Thời gian chờ lại RTO lớn nhất (mili giây)"},
        {"tcpMaxConn", "1.3.6.1.2.1.6.4", "Integer", "Số kết nối TCP tối đa mà thiết bị hỗ trợ (-1: không giới hạn)"},
        {"tcpActiveOpens", "1.3.6.1.2.1.6.5", "Counter32", "Số lần kết nối TCP được khởi tạo thành công từ phía thiết bị"},
        {"tcpPassiveOpens", "1.3.6.1.2.1.6.6", "Counter32", "Số lần thiết bị chấp nhận kết nối TCP từ bên ngoài"},
        {"tcpAttemptFails", "1.3.6.1.2.1.6.7", "Counter32", "Số lượng kết nối TCP thất bại trong giai đoạn thiết lập"},
        {"tcpEstabResets", "1.3.6.1.2.1.6.8", "Counter32", "Số lượng kết nối TCP bị reset sau khi thiết lập"},
        {"tcpCurrEstab", "1.3.6.1.2.1.6.9", "Gauge32", "Số lượng kết nối TCP đang hoạt động hoặc đã thiết lập"},
        {"tcpInSegs", "1.3.6.1.2.1.6.10", "Counter32", "Tổng số segment TCP nhận được, bao gồm cả lỗi"},
        {"tcpOutSegs", "1.3.6.1.2.1.6.11", "Counter32", "Tổng số segment TCP được gửi đi"},
        {"tcpRetransSegs", "1.3.6.1.2.1.6.12", "Counter32", "Số lượng segment TCP được gửi lại (do mất hoặc lỗi)"},
        {"tcpInErrs", "1.3.6.1.2.1.6.14", "Counter32", "Số lượng segment TCP bị loại bỏ do lỗi"},
        {"tcpOutRsts", "1.3.6.1.2.1.6.15", "Counter32", "Số lượng segment TCP chứa cờ RST được gửi đi"},
        {"tcpConnTable", "1.3.6.1.2.1.6.13", "Table", "Bảng quản lý các kết nối TCP"},
        {"tcpConnEntry", "1.3.6.1.2.1.6.13.1", "Entry", "Một mục trong bảng kết nối TCP"},
        {"tcpConnState", "1.3.6.1.2.1.6.13.1.1", "Integer", "Trạng thái của kết nối TCP (1: closed, 2: listen, 3: synSent, 4: synReceived, 5: established, 6: finWait1, 7: finWait2, 8: closeWait, 9: lastAck, 10: closing, 11: timeWait, 12: deleteTCB)"},
        {"tcpConnLocalAddress", "1.3.6.1.2.1.6.13.1.2", "IpAddress", "Địa chỉ IP cục bộ của kết nối TCP"},
        {"tcpConnLocalPort", "1.3.6.1.2.1.6.13.1.3", "Integer", "Cổng TCP cục bộ của kết nối"},
        {"tcpConnRemAddress", "1.3.6.1.2.1.6.13.1.4", "IpAddress", "Địa chỉ IP từ xa của kết nối TCP"},
        {"tcpConnRemPort", "1.3.6.1.2.1.6.13.1.5", "Integer", "Cổng TCP từ xa của kết nối"},
        {"tcpListenerTable", "1.3.6.1.2.1.6.19", "Table", "Bảng chứa thông tin các cổng TCP đang lắng nghe kết nối"},
        {"tcpListenerEntry", "1.3.6.1.2.1.6.19.1", "Entry", "Một mục trong bảng lắng nghe TCP"},
        {"tcpListenerLocalAddress", "1.3.6.1.2.1.6.19.1.1", "IpAddress", "Địa chỉ IP cục bộ của cổng TCP lắng nghe"},
        {"tcpListenerLocalPort", "1.3.6.1.2.1.6.19.1.2", "Integer", "Cổng TCP cục bộ đang lắng nghe"},

        {"udpInDatagrams", "1.3.6.1.2.1.7.1", "Counter32", "Số lượng datagram UDP nhận được"},
        {"udpNoPorts", "1.3.6.1.2.1.7.2", "Counter32", "Số lượng gói UDP đến mà không có cổng đích"},
        {"udpInErrors", "1.3.6.1.2.1.7.3", "Counter32", "Số lượng lỗi trong khi nhận các datagram UDP"},
        {"udpOutDatagrams", "1.3.6.1.2.1.7.4", "Counter32", "Số lượng datagram UDP đã gửi"},
        {"udpTable", "1.3.6.1.2.1.7.5", "Table", "Bảng chứa thông tin về các kết nối UDP"},
        {"udpEntry", "1.3.6.1.2.1.7.5.1", "Entry", "Một mục trong bảng kết nối UDP"},
        {"udpLocalAddress", "1.3.6.1.2.1.7.5.1.1", "IpAddress", "Địa chỉ IP cục bộ của kết nối UDP"},
        {"udpLocalPort", "1.3.6.1.2.1.7.5.1.2", "Integer", "Cổng UDP cục bộ của kết nối"},

        {"egpInMsgs", "1.3.6.1.2.1.8.1", "Counter32", "Số lượng thông điệp EGP nhận được"},
        {"egpInErrors", "1.3.6.1.2.1.8.2", "Counter32", "Số lượng lỗi trong khi nhận các thông điệp EGP"},
        {"egpOutMsgs", "1.3.6.1.2.1.8.3", "Counter32", "Số lượng thông điệp EGP gửi đi"},
        {"egpOutErrors", "1.3.6.1.2.1.8.4", "Counter32", "Số lượng lỗi khi gửi các thông điệp EGP"},
        {"egpNeighTable", "1.3.6.1.2.1.8.5", "Table", "Bảng chứa thông tin về các láng giềng EGP"},
        {"egpNeighEntry", "1.3.6.1.2.1.8.5.1", "Entry", "Một mục trong bảng láng giềng EGP"},
        {"egpNeighState", "1.3.6.1.2.1.8.5.1.1", "Integer", "Trạng thái của láng giềng EGP"},
        {"egpNeighAddr", "1.3.6.1.2.1.8.5.1.2", "IpAddress", "Địa chỉ IP của láng giềng EGP"},
        {"egpNeighAs", "1.3.6.1.2.1.8.5.1.3", "Integer", "Mã AS của láng giềng EGP"},
        {"egpNeighInMsgs", "1.3.6.1.2.1.8.5.1.4", "Counter32", "Số lượng thông điệp EGP nhận được từ láng giềng"},
        {"egpNeighInErrs", "1.3.6.1.2.1.8.5.1.5", "Counter32", "Số lượng lỗi khi nhận thông điệp EGP từ láng giềng"},
        {"egpNeighOutMsgs", "1.3.6.1.2.1.8.5.1.6", "Counter32", "Số lượng thông điệp EGP gửi đi đến láng giềng"},
        {"egpNeighOutErrs", "1.3.6.1.2.1.8.5.1.7", "Counter32", "Số lượng lỗi khi gửi thông điệp EGP đến láng giềng"},
        {"egpNeighInErrMsgs", "1.3.6.1.2.1.8.5.1.8", "Counter32", "Số lượng lỗi khi nhận thông điệp EGP từ láng giềng"},
        {"egpNeighOutErrMsgs", "1.3.6.1.2.1.8.5.1.9", "Counter32", "Số lượng lỗi khi gửi thông điệp EGP đến láng giềng"},
        {"egpNeighStateUps", "1.3.6.1.2.1.8.5.1.10", "Counter32", "Số lần trạng thái của láng giềng EGP chuyển lên"},
        {"egpNeighStateDowns", "1.3.6.1.2.1.8.5.1.11", "Counter32", "Số lần trạng thái của láng giềng EGP chuyển xuống"},
        {"egpNeighIntervalHello", "1.3.6.1.2.1.8.5.1.12", "Integer", "Khoảng thời gian giữa các thông điệp Hello từ láng giềng EGP"},
        {"egpNeighIntervalPoll", "1.3.6.1.2.1.8.5.1.13", "Integer", "Khoảng thời gian giữa các thông điệp Poll từ láng giềng EGP"},
        {"egpNeighMode", "1.3.6.1.2.1.8.5.1.14", "Integer", "Chế độ giao tiếp của láng giềng EGP"},
        {"egpNeighEventTrigger", "1.3.6.1.2.1.8.5.1.15", "Integer", "Sự kiện kích hoạt của láng giềng EGP"},
        {"egpAs", "1.3.6.1.2.1.8.6", "Integer", "Mã AS (Autonomous System) của EGP"},
        {"transmissionType", "1.3.6.1.2.1.8.10", "Integer", "Loại truyền thông của EGP (ví dụ: UDP, TCP, v.v.)"}
    };


    public static void displayNodeInfo(TreePath path, JTable infoTable, JTextField oidField) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        String nodeName = selectedNode.toString();

        DefaultTableModel tableModel = (DefaultTableModel) infoTable.getModel();
        tableModel.setRowCount(0);


        for (String[] info : MIB_INFO) {
            if (info[0].equals(nodeName)) {
                tableModel.addRow(new Object[]{"Name", info[0]});
                tableModel.addRow(new Object[]{"OID", info[1]});
                tableModel.addRow(new Object[]{"Syntax", info[2]});
                tableModel.addRow(new Object[]{"Description", info[3]});
                oidField.setText(info[1]); // Gán giá trị OID vào trường nhập liệu
                break;
            }
        }
    }

    // Tìm tên dựa vào oid
    public static String lookupName(String oid) {
        if ( oid == null) {
            return "Unknown";
        }
        for (String[] info : MIB_INFO) {
                if(oid.equals(info[1]))
                return info[0];
            }
        return "Unknown";
    }
}
