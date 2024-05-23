package org.example.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static void main(String[] args) {
        String input = "你要加密的数据"; // 替换为你需要加密的数据
        String sha256Hash = getSHA256Hash(input);
        System.out.println("SHA-256 Hash: " + sha256Hash);
    }

    public static String getSHA256Hash(String input) {
        try {
            // 获取SHA-256 MessageDigest实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 更新需要加密的数据
            byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // 完成加密操作，并返回16进制表示的哈希值
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // 不应该发生，因为SHA-256是Java标准库的一部分
        }
    }
}
