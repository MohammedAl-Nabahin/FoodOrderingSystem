/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author mohdo
 */
public class Encryption {

    private final static String salt = "DGE$5SGr@3VsHYUMas2323E4d57vfBfFSTRU@!DSH(*%FDSdfg13sgfsg";

    public static String md5Hash(String message) {
        String md5 = "";
        if (null == message) {
            return null;
        }

        message = message + salt;//adding a salt to the string before it gets hashed.
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");//Create MessageDigest object for MD5
            digest.update(message.getBytes(), 0, message.length());//Update input string in message digest
            md5 = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
