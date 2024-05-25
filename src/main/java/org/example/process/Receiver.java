package org.example.process;

import org.example.classes.Envelope;
import org.example.classes.InfoString;
import org.example.util.Hash;
import org.example.util.HybridEncrypt;
import org.example.util.JsonControl;
import org.example.util.Keys;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;

public class Receiver {

    public static boolean verify(PrivateKey privateKey, Envelope envelope,byte[] challenge) throws Exception {
        // 确保是响应了目标请求
        if (!Objects.equals(Keys.encodeToString(envelope.getChallenge()), Keys.encodeToString(challenge))){
            System.out.println("Challenge id not match!");
            return false;
        }

        // 使用RSA解密AES密钥
        byte[] decryptedAesKey = HybridEncrypt.rsaDecrypt(envelope.getEncryptedAesKey(), privateKey);

        // 使用AES密钥解密数据
        byte[] decryptedData = HybridEncrypt.decryptAES(envelope, decryptedAesKey);

        // 分别获取 info 中的 publicKey content signature
        InfoString receiveInfoString = JsonControl.jsonToInfo(new String(decryptedData, StandardCharsets.UTF_8));
        System.out.println("ReceiveInfo: " + receiveInfoString.toString());


        //  public key  String -> PublicKey
        PublicKey verifyPublicKey = Keys.stringToPublickey(receiveInfoString.getPublicKey());

        // verify  .getBytes() and base64 ???? why ????
        String verifyAbstract = Hash.getSHA256Hash(receiveInfoString.getContent());
        boolean isSignatureValid = Keys.verify(verifyAbstract.getBytes(), Keys.encodeToByte(receiveInfoString.getSignature()),verifyPublicKey);
        System.out.println("Verify: "+ isSignatureValid);

        return isSignatureValid;
    }
}
