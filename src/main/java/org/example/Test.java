package org.example;

import org.example.QRcode.QRCodeGenerator;
import org.example.QRcode.QRCodeReader;
import org.example.classes.Envelope;
import org.example.classes.Person;
import org.example.process.Sender;
import org.example.process.Receiver;
import org.example.util.JsonControl;
import org.example.util.Keys;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class Test {
    public static void main(String args[]) throws Exception {
        // generate the key pair
        Person alice=  new Person("0x1a", Keys.generateKeyPair());
        PublicKey publicKeyA = alice.getKeyPair().getPublic();
        PrivateKey privateKeyA = alice.getKeyPair().getPrivate();

        Person bob=  new Person("0x2b", Keys.generateKeyPair());
        PublicKey publicKeyB = bob.getKeyPair().getPublic();
        System.out.println(Keys.encodeToString(publicKeyB.getEncoded()));
        PrivateKey privateKeyB = bob.getKeyPair().getPrivate();

        // creat challenge id
        byte[] challenge = "1111".getBytes();

        // creat content
        String content = "hello world!fdafafdafadfafagdasgdasfewfa";

        // send envelope
        Envelope envelope= Sender.creatEnvelope(alice, publicKeyB, content,challenge);

        // generate a QRCode with envelope
        String filePath = "QRCodeImg/1.png";
        QRCodeGenerator.generatorQRCode(envelope,filePath);

        // read QRCode
        String readEnvelopeString =  QRCodeReader.readQRCode(filePath);
        Envelope readEnvelope = JsonControl.jsonToEnvelope(readEnvelopeString);

        // verify
        boolean isValid = Receiver.verify(privateKeyB,readEnvelope,challenge);

    }
}
