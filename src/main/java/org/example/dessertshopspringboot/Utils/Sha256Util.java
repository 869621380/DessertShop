package org.example.dessertshopspringboot.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Util {
    // 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Sha256Util.class.getName() + "初始化失败，MessageDigest不支持SHA-256。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 生成字符串的SHA - 256校验值
     *
     * @param s 要生成哈希值的字符串
     * @return SHA - 256哈希值字符串
     */
    public static String getSHA256String(String s) {
        return getSHA256String(s.getBytes());
    }

    /**
     * 判断字符串的SHA - 256校验码是否与一个已知的SHA - 256码相匹配
     *
     * @param password  要校验的字符串
     * @param sha256PwdStr 已知的SHA - 256校验码
     * @return 是否匹配
     */
    public static boolean checkPassword(String password, String sha256PwdStr) {
        String s = getSHA256String(password);
        return s.equals(sha256PwdStr);
    }

    public static String getSHA256String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}