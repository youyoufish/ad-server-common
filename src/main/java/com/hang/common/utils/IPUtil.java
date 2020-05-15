package com.hang.common.utils;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
    /**
     * Convert the IP address to long
     * The value is just for Java, because the IP-details file is different from C#.
     *
     * @param ip
     * @return the long(Uint32) number.
     * @throws UnknownHostException
     * @version 2013/06/17
     */
    public static long convertIPToLong(String ip) throws UnknownHostException {
        InetAddress IPAddr;
        IPAddr = InetAddress.getByName(ip);

        if (IPAddr == null)
            return 0;
        byte[] bytes = IPAddr.getAddress();

        if (bytes.length < 4) {
            return 0;
        }
        long l0 = bytes[0] & 0xFF;
        long l1 = bytes[1] & 0xFF;
        long l2 = bytes[2] & 0xFF;
        long l3 = bytes[3] & 0xFF;
        return (l0 << 24) | (l1 << 16) | (l2 << 8) | l3;
    }

    public static String convertLongToIP(long ipInJava) throws UnknownHostException {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((ipInJava >> 24) % 256);
        bytes[1] = (byte) ((ipInJava >> 16) % 256);
        bytes[2] = (byte) ((ipInJava >> 8) % 256);
        bytes[3] = (byte) (ipInJava % 256);
        InetAddress IPAddr = InetAddress.getByAddress(bytes);
        return IPAddr.getHostAddress();
    }

    /**
     * Just for compatible IP log
     *
     * @param ip
     * @return
     * @throws UnknownHostException
     */
    public static int convertIPToLongForCSharp(String ip) throws UnknownHostException {
        InetAddress IPAddr;
        IPAddr = InetAddress.getByName(ip);

        if (IPAddr == null)
            return 0;
        byte[] bytes = IPAddr.getAddress();

        if (bytes.length < 4) {
            return 0;
        }
        int l0 = bytes[0] & 0xFF;
        int l1 = bytes[1] & 0xFF;
        int l2 = bytes[2] & 0xFF;
        int l3 = bytes[3] & 0xFF;
        return (l3 << 24) | (l2 << 16) | (l1 << 8) | l0;
    }

    public static String convertLongToIPForCSharp(long ipInCSharp) throws UnknownHostException {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (ipInCSharp % 256);
        bytes[1] = (byte) ((ipInCSharp >> 8) % 256);
        bytes[2] = (byte) ((ipInCSharp >> 16) % 256);
        bytes[3] = (byte) ((ipInCSharp >> 24) % 256);
        InetAddress IPAddr = InetAddress.getByAddress(bytes);
        return IPAddr.getHostAddress();
    }

    /**
     * Get the ip address string from http request
     *
     * @param request The HttpServletRequest instance
     * @return The IP Address string
     * @throws UnknownHostException
     */
    public static String getIpAddress(HttpServletRequest request) throws UnknownHostException {
        String ip = getFirstXForwardedForIp(request); // x-forward-for
        if (IsLocalIPOrNull(ip)) {
            ip = getXRealIp(request); // x-real-ip
        }
        if (IsLocalIPOrNull(ip)) {
            ip = request.getRemoteAddr(); // remote_addr
        }
        if (ip == null || ip.length() == 0) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * @param request     request
     * @param specialName special the name of ip in the http header
     * @return string of ip
     * @throws UnknownHostException
     */
    public static String getIpAddress(HttpServletRequest request, String specialName) throws UnknownHostException {
        String ip = getIpAddress(request);
        if (IsLocalIPOrNull(ip)) {
            ip = getFirstSpecialForIp(request, specialName);
        }
        if (ip == null || ip.length() == 0) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public static String getXForwardedForIp(HttpServletRequest request) {
        return request.getHeader("x-forwarded-for");
    }

    public static String getFirstXForwardedForIp(HttpServletRequest request) {
        String xffIp = null;
        String xff = request.getHeader("x-forwarded-for");
        if (xff == null || xff.length() == 0) {
            return xffIp;
        }
        String[] ips = xff.split("[,\\s]");
        for (int i = 0; i < ips.length; i++) {
            if (IsLocalIPOrNull(ips[i])) {
                continue;
            }
            xffIp = ips[i];
            break;
        }
        return xffIp;
    }

    public static String getXRealIp(HttpServletRequest request) {
        String xriIp = null;
        String xri = request.getHeader("x-real-ip");
        if (xri == null || xri.length() == 0) {
            return xriIp;
        }
        String[] ips = xri.split("[,\\s]");
        for (int i = 0; i < ips.length; i++) {
            if (IsLocalIPOrNull(ips[i])) {
                continue;
            }
            xriIp = ips[i];
            break;
        }
        return xriIp;
    }

    public static String getSpecialForIp(HttpServletRequest request, String specialName) {
        return request.getHeader(specialName);
    }

    public static String getFirstSpecialForIp(HttpServletRequest request, String specialName) {
        String ip = null;
        String specialIp = request.getHeader(specialName);
        if (specialIp == null || specialIp.length() == 0) {
            return ip;
        }
        String[] ips = specialIp.split("[,\\s]");
        for (int i = 0; i < ips.length; i++) {
            if (IsLocalIPOrNull(ips[i])) {
                continue;
            }
            ip = ips[i];
            break;
        }
        return ip;
    }

    public static boolean IsLocalIPOrNull(String ip) {
        if (ip == null || ip.length() == 0) {
            return true;
        }
        return ip.startsWith("192.168.")
                || ip.startsWith("10.")
                || ip.equals("127.0.0.1")
                || ip.equalsIgnoreCase("unknown");
    }

    /**
     * get current machine's ip address
     * @return ip address
     */
    public static String getLocalIp() throws IOException {

        ParseIpRoute ipRoute= ParseIpRoute.getInstance();
        return ipRoute.getLocalIPAddress();
    }

    /**
     * parse route table to get local ip address
     */
    private static class ParseIpRoute {

        //current machine ip address
        private static String _ip;

        private static ParseIpRoute _instance;


        private ParseIpRoute() throws IOException {
            parse();
        }

        public String getLocalIPAddress() {
            return _ip;
        }


        public static synchronized ParseIpRoute getInstance() throws IOException {

            if(_instance==null){
                _instance= new ParseIpRoute();
            }
            return _instance;
        }

        private void parse() throws IOException {
            if (isWindows()) {
                parseWindows();
            } else if (isLinux()) {
                parseLinux();
            }
        }

        private boolean isWindows() {
            String os = System.getProperty("os.name").toUpperCase();
            return os.contains("WINDOWS");
        }

        private boolean isLinux() {
            String os = System.getProperty("os.name").toUpperCase();
            return os.contains("LINUX");
        }


        private List<String> split(String source, String delim, boolean removeEmpty) {

            if (StringUtil.isNullOrWhitespace(source)) {
                return null;
            }
            if (delim==null || Objects.equals(delim, "")) {
                return Arrays.asList(source);
            }

            String[] tokens = source.split(delim);
            if (!removeEmpty) {
                return Arrays.asList(tokens);
            }
            ArrayList<String> newTokens = new ArrayList<>();
            for (String token : tokens) {
                if (token == null || Objects.equals(token, "")) {
                    continue;
                }
                newTokens.add(token);
            }
            return newTokens;
        }

        /**
         * retrieve windows machine ip address
         */
        private void parseWindows() throws IOException {

            Process pro = Runtime.getRuntime().exec("cmd.exe /c route print");
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();
                    List<String> tokens = split(line, " ", true);
                    if (tokens == null) {
                        continue;
                    }
                    if (tokens.size() == 5 && tokens.get(0).equals("0.0.0.0")) {
                        _ip = tokens.get(3);
                        break;
                    }
                }
            }
            pro.destroy();

        }

        /**
         * retrieve linux machine ip address
         */
        private void parseLinux() throws IOException {

            try (BufferedReader reader = new BufferedReader(new FileReader("/proc/net/route"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    String[] tokens = line.split("\t");
                    if (tokens.length > 1 && tokens[1].equals("00000000")) {
                        String iface = tokens[0];
                        NetworkInterface nif = NetworkInterface.getByName(iface);
                        Enumeration addrs = nif.getInetAddresses();
                        while (addrs.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress)addrs.nextElement();
                            if (inetAddress instanceof Inet4Address) {
                                _ip=inetAddress.getHostAddress();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

}
