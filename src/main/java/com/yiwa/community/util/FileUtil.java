package com.yiwa.community.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileUtil {
    public static byte[] fileConvertToByteArray(File file) {
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray();

            baos.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
