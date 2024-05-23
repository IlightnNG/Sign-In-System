package org.example.QRcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.example.classes.Envelope;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeReader {

    public static String readQRCode(String filePath) {
        File qrCodeImage = new File(filePath);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(qrCodeImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

        Result result = null;
        try {
            result = new MultiFormatReader().decode(binaryBitmap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        if (result != null) {
            System.out.println("QRCode Decoded text: " + result.getText());
            return result.getText();
        }
        return null;
    }
}