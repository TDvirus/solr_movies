package com.limp.framework.boss;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class test {
    public static String getMD5(String src)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("md5");

            byte[] bs = digest.digest(src.getBytes("utf-8"));

            String hexString = "";
            for (byte b : bs)
            {
                int temp = b & 0xFF;
                if ((temp < 16) && (temp >= 0)) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args)
    {
        String md5str = getMD5("123456");
        System.out.println(md5str);
    }
}
