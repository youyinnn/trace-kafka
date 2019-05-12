package com.github.youyinnn.tracekafkaapi.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author youyinnn
 * Date 4/30/2019
 */
public class CodecStringUtil {

    public static long uniqueId() {
        return ThreadLocalRandom.current().nextLong();
    }

    private static String keyOne;
    private static String keyTwo;

    public static void setKey(String keyOne, String keyTwo) {
        CodecStringUtil.keyOne = keyOne;
        CodecStringUtil.keyTwo = keyTwo;
    }

    public static String generateToken() {
        return generateToken(0);
    }

    private static String generateToken(int offset){
        LocalTime offsetTime;
        if (offset > 0) {
            offsetTime = LocalTime.now().plusMinutes(offset);
        } else if (offset < 0) {
            offsetTime = LocalTime.now().minusMinutes(-offset);
        } else {
            offsetTime = LocalTime.now();
        }
        return SHA256.encrypt(offsetTime.getHour() + ":" + offsetTime.getMinute() + keyOne);
    }

    public static boolean verifyToken(String token) {
        return token.equals(generateToken(0)) ||
                token.equals(generateToken(-1)) ||
                token.equals(generateToken(1));
    }

    public static String encryptMessage(String msg) {
        return TDES.string2EncryptedB64(msg);
    }

    public static String decryptMessage(String cipher) {
        return TDES.encryptedB642String(cipher);
    }

    public static class TDES{

        private static String desKey;

        static {
            desKey = keyTwo == null ? "989d64tg69a8bi9c89dq6bpwkbkmdjxuwbvmgo679j935f5g5m59m59fd9" : keyTwo;
        }

        private static byte[] hex(String key){
            String f = DigestUtils.md5Hex(key);
            byte[] kbs = f.getBytes();
            byte[] enk = new byte[24];
            System.arraycopy(kbs, 0, enk, 0, 24);
            return enk;
        }

        static String encode(String key, String srcStr){
            byte[] keyByte = hex(key);
            byte[] src = srcStr.getBytes();
            try {
                //生成密钥
                SecretKey desKey = new SecretKeySpec(keyByte, "DESede");
                //加密
                Cipher c1 = Cipher.getInstance("DESede");
                c1.init(Cipher.ENCRYPT_MODE, desKey);
                return Base64.encodeBase64String(c1.doFinal(src));
            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        static String decode(String key, String desStr){
            Base64 base64 = new Base64();
            byte[] keyByte = hex(key);
            byte[] src = base64.decode(desStr);

            try {
                //生成密钥
                SecretKey desKey = new SecretKeySpec(keyByte, "DESede");
                //解密
                Cipher c1 = Cipher.getInstance("DESede");
                c1.init(Cipher.DECRYPT_MODE, desKey);
                return new String(c1.doFinal(src));
            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        static String encode(String text) {
            return encode(desKey, text);
        }

        static String decode(String text) {
            return decode(desKey, text);
        }

        public static String encryptedB642String(String b64) {
            byte[] bytes = Base64.decodeBase64(b64);
            String encode = new String(bytes, StandardCharsets.UTF_8);
            return decode(encode);
        }

        public static String string2EncryptedB64(String msg) {
            String encodeForId = encode(msg);
            byte[] bytes = Base64.encodeBase64(encodeForId.getBytes(StandardCharsets.UTF_8));
            return new String(bytes);
        }
    }

    public static class SHA256 {

        private static String byte2Hex(byte[] bytes){
            StringBuilder stringBuffer = new StringBuilder();
            String temp;
            for (byte aByte : bytes) {
                temp = Integer.toHexString(aByte & 0xFF);
                if (temp.length() == 1) {
                    //1得到一位的进行补0操作
                    stringBuffer.append("0");
                }
                stringBuffer.append(temp);
            }
            return stringBuffer.toString();
        }

        public static String encrypt(String str){
            MessageDigest messageDigest;
            String encodeStr = "";
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
                encodeStr = byte2Hex(messageDigest.digest());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return encodeStr;
        }
    }
}
