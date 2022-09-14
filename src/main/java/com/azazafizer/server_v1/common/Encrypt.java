package com.azazafizer.server_v1.common;

import com.azazafizer.server_v1.api.token.controller.exception.EncryptException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class Encrypt {

    public String sha512(String data) throws EncryptException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(data.getBytes());
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new EncryptException("SHA-512 암호화 중 오류가 발생함");
        }
    }
}
