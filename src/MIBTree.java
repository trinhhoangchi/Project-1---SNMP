import javax.swing.tree.DefaultMutableTreeNode;

public class MIBTree {

    // Tạo cây MIB
    public static DefaultMutableTreeNode createMIBTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("mib");

        // Tạo cây cho "system"
        DefaultMutableTreeNode system = new DefaultMutableTreeNode("system");
        root.add(system);
        system.add(new DefaultMutableTreeNode("sysDescr"));
        system.add(new DefaultMutableTreeNode("sysObjectID"));
        system.add(new DefaultMutableTreeNode("sysUpTime"));
        system.add(new DefaultMutableTreeNode("sysContact"));
        system.add(new DefaultMutableTreeNode("sysName"));
        system.add(new DefaultMutableTreeNode("sysLocation"));
        system.add(new DefaultMutableTreeNode("sysServices"));

        // Tạo cây cho "interface"
        DefaultMutableTreeNode interfaces = new DefaultMutableTreeNode("interfaces");
        root.add(interfaces);
        DefaultMutableTreeNode ifNumber = new DefaultMutableTreeNode("ifNumber");
        interfaces.add(ifNumber);
        DefaultMutableTreeNode ifTable = new DefaultMutableTreeNode("ifTable");
        interfaces.add(ifTable);
        DefaultMutableTreeNode ifEntry = new DefaultMutableTreeNode("ifEntry");
        ifTable.add(ifEntry);

        ifEntry.add(new DefaultMutableTreeNode("ifIndex"));
        ifEntry.add(new DefaultMutableTreeNode("ifDescr"));
        ifEntry.add(new DefaultMutableTreeNode("ifType"));
        ifEntry.add(new DefaultMutableTreeNode("ifMtu"));
        ifEntry.add(new DefaultMutableTreeNode("ifSpeed"));
        ifEntry.add(new DefaultMutableTreeNode("ifPhysAddress"));
        ifEntry.add(new DefaultMutableTreeNode("ifAdminStatus"));
        ifEntry.add(new DefaultMutableTreeNode("ifOperStatus"));
        ifEntry.add(new DefaultMutableTreeNode("ifLastChange"));
        ifEntry.add(new DefaultMutableTreeNode("ifInOctets"));
        ifEntry.add(new DefaultMutableTreeNode("ifInUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifInNUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifInDiscards"));
        ifEntry.add(new DefaultMutableTreeNode("ifInErrors"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutOctets"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutNUcastPkts"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutDiscards"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutErrors"));
        ifEntry.add(new DefaultMutableTreeNode("ifOutQLen"));
        ifEntry.add(new DefaultMutableTreeNode("ifSpecific"));

        // Tạo cây cho "at"
        DefaultMutableTreeNode at = new DefaultMutableTreeNode("at");
        root.add(at);

        DefaultMutableTreeNode atTable = new DefaultMutableTreeNode("atTable");
        at.add(atTable);

        DefaultMutableTreeNode atEntry = new DefaultMutableTreeNode("atEntry");
        atTable.add(atEntry);

        atEntry.add(new DefaultMutableTreeNode("atIfIndex"));
        atEntry.add(new DefaultMutableTreeNode("atPhysAddress"));
        atEntry.add(new DefaultMutableTreeNode("atNetAddress"));

        // Tạo cây cho "ip" 
        DefaultMutableTreeNode ip = new DefaultMutableTreeNode("ip");
        root.add(ip);

        DefaultMutableTreeNode ipForwarding = new DefaultMutableTreeNode("ipForwarding");
        ip.add(ipForwarding);

        DefaultMutableTreeNode ipDefaultTTL = new DefaultMutableTreeNode("ipDefaultTTL");
        ip.add(ipDefaultTTL);

        DefaultMutableTreeNode ipStats = new DefaultMutableTreeNode("ipStats");
        ip.add(ipStats);
        ipStats.add(new DefaultMutableTreeNode("ipInReceives"));
        ipStats.add(new DefaultMutableTreeNode("ipInHdrErrors"));
        ipStats.add(new DefaultMutableTreeNode("ipInAddrErrors"));
        ipStats.add(new DefaultMutableTreeNode("ipForwDatagrams"));
        ipStats.add(new DefaultMutableTreeNode("ipInUnknownProtos"));
        ipStats.add(new DefaultMutableTreeNode("ipInDiscards"));
        ipStats.add(new DefaultMutableTreeNode("ipInDelivers"));
        ipStats.add(new DefaultMutableTreeNode("ipOutRequests"));
        ipStats.add(new DefaultMutableTreeNode("ipOutDiscards"));
        ipStats.add(new DefaultMutableTreeNode("ipOutNoRoutes"));
        ipStats.add(new DefaultMutableTreeNode("ipReasmTimeout"));
        ipStats.add(new DefaultMutableTreeNode("ipReasmReqds"));
        ipStats.add(new DefaultMutableTreeNode("ipReasmOKs"));
        ipStats.add(new DefaultMutableTreeNode("ipReasmFails"));
        ipStats.add(new DefaultMutableTreeNode("ipFragOKs"));
        ipStats.add(new DefaultMutableTreeNode("ipFragFails"));
        ipStats.add(new DefaultMutableTreeNode("ipFragCreates"));

        DefaultMutableTreeNode ipAddrTable = new DefaultMutableTreeNode("ipAddrTable");
        ip.add(ipAddrTable);

        DefaultMutableTreeNode ipAddrEntry = new DefaultMutableTreeNode("ipAddrEntry");
        ipAddrTable.add(ipAddrEntry);
        ipAddrEntry.add(new DefaultMutableTreeNode("ipAdEntAddr"));
        ipAddrEntry.add(new DefaultMutableTreeNode("ipAdEntIfIndex"));
        ipAddrEntry.add(new DefaultMutableTreeNode("ipAdEntNetMask"));
        ipAddrEntry.add(new DefaultMutableTreeNode("ipAdEntBcastAddr"));
        ipAddrEntry.add(new DefaultMutableTreeNode("ipAdEntReasmMaxSize"));

        DefaultMutableTreeNode ipRouteTable = new DefaultMutableTreeNode("ipRouteTable");
        ip.add(ipRouteTable);
        DefaultMutableTreeNode ipRouteEntry = new DefaultMutableTreeNode("ipRouteEntry");
        ipRouteTable.add(ipRouteEntry);

        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteDest"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteIfIndex"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteMetric1"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteMetric2"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteMetric3"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteMetric4"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteNextHop"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteType"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteProto"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteAge"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteMask"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteMetric5"));
        ipRouteEntry.add(new DefaultMutableTreeNode("ipRouteInfo"));

        DefaultMutableTreeNode ipNetToMediaTable = new DefaultMutableTreeNode("ipNetToMediaTable");
        ip.add(ipNetToMediaTable);
        DefaultMutableTreeNode ipNetToMediaEntry = new DefaultMutableTreeNode("ipNetToMediaEntry");
        ipNetToMediaTable.add(ipNetToMediaEntry);

        ipNetToMediaEntry.add(new DefaultMutableTreeNode("ipNetToMediaIfIndex"));
        ipNetToMediaEntry.add(new DefaultMutableTreeNode("ipNetToMediaPhysAddress"));
        ipNetToMediaEntry.add(new DefaultMutableTreeNode("ipNetToMediaNetAddress"));
        ipNetToMediaEntry.add(new DefaultMutableTreeNode("ipNetToMediaType"));

        DefaultMutableTreeNode ipRoutingDiscards = new DefaultMutableTreeNode("ipRoutingDiscards");
        ip.add(ipRoutingDiscards);

        // Tạo cây cho "icmp"
        DefaultMutableTreeNode icmp = new DefaultMutableTreeNode("icmp");
        root.add(icmp);
        icmp.add(new DefaultMutableTreeNode("icmpInMsgs"));
        icmp.add(new DefaultMutableTreeNode("icmpInErrors"));
        icmp.add(new DefaultMutableTreeNode("icmpInDestUnreachs"));
        icmp.add(new DefaultMutableTreeNode("icmpInTimeExcds"));
        icmp.add(new DefaultMutableTreeNode("icmpInParmProbs"));
        icmp.add(new DefaultMutableTreeNode("icmpInSrcQuenchs"));
        icmp.add(new DefaultMutableTreeNode("icmpInRedirects"));
        icmp.add(new DefaultMutableTreeNode("icmpInEchos"));
        icmp.add(new DefaultMutableTreeNode("icmpInEchoReps"));
        icmp.add(new DefaultMutableTreeNode("icmpInTimestamps"));
        icmp.add(new DefaultMutableTreeNode("icmpInTimestampReps"));
        icmp.add(new DefaultMutableTreeNode("icmpInAddrMasks"));
        icmp.add(new DefaultMutableTreeNode("icmpInAddrMaskReps"));
        icmp.add(new DefaultMutableTreeNode("icmpOutMsgs"));
        icmp.add(new DefaultMutableTreeNode("icmpOutErrors"));
        icmp.add(new DefaultMutableTreeNode("icmpOutDestUnreachs"));
        icmp.add(new DefaultMutableTreeNode("icmpOutTimeExcds"));
        icmp.add(new DefaultMutableTreeNode("icmpOutParmProbs"));
        icmp.add(new DefaultMutableTreeNode("icmpOutSrcQuenchs"));
        icmp.add(new DefaultMutableTreeNode("icmpOutRedirects"));
        icmp.add(new DefaultMutableTreeNode("icmpOutEchos"));
        icmp.add(new DefaultMutableTreeNode("icmpOutEchoReps"));
        icmp.add(new DefaultMutableTreeNode("icmpOutTimestamps"));
        icmp.add(new DefaultMutableTreeNode("icmpOutTimestampReps"));
        icmp.add(new DefaultMutableTreeNode("icmpOutAddrMasks"));
        icmp.add(new DefaultMutableTreeNode("icmpOutAddrMaskReps"));

        // Tạo cây cho "tcp"
        DefaultMutableTreeNode tcp = new DefaultMutableTreeNode("tcp");
        root.add(tcp);
        tcp.add(new DefaultMutableTreeNode("tcpRtoAlgorithm"));
        tcp.add(new DefaultMutableTreeNode("tcpRtoMin"));
        tcp.add(new DefaultMutableTreeNode("tcpRtoMax"));
        tcp.add(new DefaultMutableTreeNode("tcpMaxConn"));
        tcp.add(new DefaultMutableTreeNode("tcpActiveOpens"));
        tcp.add(new DefaultMutableTreeNode("tcpPassiveOpens"));
        tcp.add(new DefaultMutableTreeNode("tcpAttemptFails"));
        tcp.add(new DefaultMutableTreeNode("tcpEstabResets"));
        tcp.add(new DefaultMutableTreeNode("tcpCurrEstab"));
        tcp.add(new DefaultMutableTreeNode("tcpInSegs"));
        tcp.add(new DefaultMutableTreeNode("tcpOutSegs"));
        tcp.add(new DefaultMutableTreeNode("tcpRetransSegs"));
        DefaultMutableTreeNode tcpConnTable = new DefaultMutableTreeNode("tcpConnTable");
        tcp.add(tcpConnTable);
        // Thêm các mục con của "tcpConnTable"
        DefaultMutableTreeNode tcpConnEntry = new DefaultMutableTreeNode("tcpConnEntry");
        tcpConnTable.add(tcpConnEntry);
        tcpConnEntry.add(new DefaultMutableTreeNode("tcpConnState"));
        tcpConnEntry.add(new DefaultMutableTreeNode("tcpConnLocalAddress"));
        tcpConnEntry.add(new DefaultMutableTreeNode("tcpConnLocalPort"));
        tcpConnEntry.add(new DefaultMutableTreeNode("tcpConnRemAddress"));
        tcpConnEntry.add(new DefaultMutableTreeNode("tcpConnRemPort"));

        tcp.add(new DefaultMutableTreeNode("tcpInErrs"));
        tcp.add(new DefaultMutableTreeNode("tcpOutRsts"));

        // Tạo cây cho "udp"
        DefaultMutableTreeNode udp = new DefaultMutableTreeNode("udp");
        root.add(udp);
        udp.add(new DefaultMutableTreeNode("udpInDatagrams"));
        udp.add(new DefaultMutableTreeNode("udpNoPorts"));
        udp.add(new DefaultMutableTreeNode("udpInErrors"));
        udp.add(new DefaultMutableTreeNode("udpOutDatagrams"));
        DefaultMutableTreeNode udpTable = new DefaultMutableTreeNode("udpTable");
        udp.add(udpTable);
        // Thêm các mục con của "tcpConnTable"
        DefaultMutableTreeNode udpEntry = new DefaultMutableTreeNode("udpEntry");
        udpTable.add(udpEntry);
        udpEntry.add(new DefaultMutableTreeNode("udpLocalAddress"));
        udpEntry.add(new DefaultMutableTreeNode("udpLocalPort"));

        // Tạo cây cho "egp"
        DefaultMutableTreeNode egp = new DefaultMutableTreeNode("egp");
        root.add(egp);

        egp.add(new DefaultMutableTreeNode("egpInMsgs"));
        egp.add(new DefaultMutableTreeNode("egpInErrors"));
        egp.add(new DefaultMutableTreeNode("egpOutMsgs"));
        egp.add(new DefaultMutableTreeNode("egpOutErrors"));

        // Tạo cây cho bảng "egpNeighTable"
        DefaultMutableTreeNode egpNeighTable = new DefaultMutableTreeNode("egpNeighTable");
        egp.add(egpNeighTable);

        // Tạo mục con cho "egpNeighTable"
        DefaultMutableTreeNode egpNeighEntry = new DefaultMutableTreeNode("egpNeighEntry");
        egpNeighTable.add(egpNeighEntry);

        // Thêm các mục con của "egpNeighEntry"
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighState"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighAddr"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighAs"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighInMsgs"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighInErrs"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighOutMsgs"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighOutErrs"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighInErrMsgs"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighOutErrMsgs"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighStateUps"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighStateDowns"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighIntervalHello"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighIntervalPoll"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighMode"));
        egpNeighEntry.add(new DefaultMutableTreeNode("egpNeighEventTrigger"));

        egp.add(new DefaultMutableTreeNode("egpAs"));
        egp.add(new DefaultMutableTreeNode("transmission"));
        return root;
    }
}
