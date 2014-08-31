/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unibaveopencode.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;

/**
 *
 * @author Stéfani
 */
public class QrCodeUtil {
    
    public BufferedImage gerarQRCode(int width, int height, String content) throws WriterException{
        BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE,width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    
}
