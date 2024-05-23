package org.example.QRcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.classes.Envelope;

public class QRCodeGenerator {
    public static void generatorQRCode(Envelope envelope,String filePath){
        // convert envelope to json
        String serializedEnvelope = envelope.serialize();
        System.out.println("Serialized Envelope: "+ serializedEnvelope);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(serializedEnvelope, BarcodeFormat.QR_CODE, 1000, 1000, hints);
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", new File(filePath));
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
