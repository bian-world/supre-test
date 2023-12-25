package com.supretest.commons.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

//    public static void main(String[] args) {
//        System.out.println(imgToBase64("/var/folders/ps/mv570cvd55l9_0_06nh87bjc0000gn/T/screenshot18342059367602949060.png"));
//        String path ="/Users/hj/Desktop/screenshot1834205936760294ddddd060.jpg";
//        String strBase64 = imgToBase64("/var/folders/ps/mv570cvd55l9_0_06nh87bjc0000gn/T/screenshot18342059367602949060.png");
//        base64ToImg(strBase64,path);
//    }

    //绝对路径
    public String imgToBase64(String url) {
        String s = null;
        String substring = url.substring(url.lastIndexOf('.')+1);
        try {
            //"E:/static-resource/ihospital/doctorSign/doctor_sign_img-1.png"
            File imgFile =  new File(url);
            byte[] data = null;
            FileInputStream fileInputStream = new FileInputStream(imgFile);
            int available = fileInputStream.available();
            data = new byte[available];
            fileInputStream.read(data);
            fileInputStream.close();
            s = Base64.encodeBase64String(data);
            s.replaceAll("\n", "").replaceAll("\r", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/" + substring + ";base64,"+ s;
    }

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param file base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */
    public String base64ToImg(String file, String path) {
        // 解密
        try {
            // 图片分类路径+图片名+图片后缀
//            String imgClassPath = path.concat(UUID.randomUUID().toString()).concat(".jpg");
            // 解密
//            Base64.Decoder decoder = Base64.getDecoder();
            // 去掉base64前缀 data:image/jpeg;base64,
            file = file.substring(file.indexOf(",", 1) + 1, file.length());
            byte[] b = Base64.decodeBase64(file);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 保存图片
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            // 返回图片的相对路径 = 图片分类路径+图片名+图片后缀
            return path;
        } catch (IOException e) {
            return null;
        }
    }


}
