package org.example.util;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Keys {

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024); // 可以设置密钥长度
        return keyGen.generateKeyPair();
    }

    // String 和 byte[] (base64) 互转
    public static String encodeToString (byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
    public static byte[] encodeToByte(String string){
        return Base64.getDecoder().decode(string);
    }

    public static PublicKey stringToPublickey(String publickeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            // 1. 解码Base64字符串
            byte[] decodedKey = Base64.getDecoder().decode(publickeyString);

            // 2. 读取公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);

            // 3. 生成PublicKey
            KeyFactory kf = KeyFactory.getInstance("RSA"); // 或者其他算法，如 "EC"
            PublicKey publicKey = kf.generatePublic(keySpec);
            return publicKey;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (InvalidKeySpecException e){
            e.printStackTrace();
        }
            return null;
    }

    public static byte[] sign(byte[] data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(data);
        return privateSignature.sign();
    }

    public static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) {
        try {
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(data);
            return publicSignature.verify(signature);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
