package com.teste.apptestelogin.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ApiUtil {

    public static String gerarMD5Hash(String password) {

        String retorno = "";

        if(!password.isEmpty()) {

            retorno = "falhou";

            try {
                // Create MD5 Hash
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(password.getBytes());
                byte messageDigest[] = digest.digest();

                StringBuffer MD5Hash = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++) {
                    String h = Integer.toHexString(0xFF & messageDigest[i]);
                    while (h.length() < 2)
                        h = "0" + h;
                    MD5Hash.append(h);
                }

                return MD5Hash.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
        return retorno;
    }
}
