package org.example.util;

import org.example.classes.Envelope;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class HybridEncrypt {

    private static final String RSA = "RSA";
    private static final String AES = "AES";
    private static final int RSA_KEY_SIZE = 2048;
    private static final int AES_KEY_SIZE = 128;



    public static byte[] rsaEncrypt(byte[] plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // RSA加密的数据块大小有限制，需要分块加密（这里简化处理，仅加密小块数据）
        if (plainText.length > RSA_KEY_SIZE / 8 - 11) {
            throw new IllegalArgumentException("Plaintext too large for RSA");
        }

        return cipher.doFinal(plainText);
    }

    public static byte[] rsaDecrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(cipherText);
    }

    public static byte[] generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(AES_KEY_SIZE);
        return keyGenerator.generateKey().getEncoded();
    }


    // encrypt text and creat Envelope
    public static Envelope encryptAES(byte[] plainText, byte[] aesKey,byte[] encryptedAesKey,byte[] challenge) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, AES);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()]; // 生成一个随机的IV
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] cipherText = cipher.doFinal(plainText);

        // 通常，你会将IV与密文一起存储或传输
        // 例如，可以将IV和密文连接起来，或者将IV作为单独的字段存储
        return new Envelope(cipherText,iv,encryptedAesKey,challenge);
    }

    public static byte[] decryptAES(Envelope envelope, byte[] aesKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, AES);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(envelope.getIv()); // use iv
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return cipher.doFinal(envelope.getEncryptedInfo());
    }
}
