package com.example.springboot.controller;
import com.arcsoft.face.FaceEngine;
import com.example.springboot.entity.Msg;
import com.example.springboot.service.ArcSoft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.codec.binary.Base64;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


@Controller
public class Camer {

    @Autowired
    ArcSoft arcSoft;
    FaceEngine faceEngine = arcSoft.faceEngine();

    @Autowired
    Msg msg;


    @CrossOrigin
    @ResponseBody
    @PostMapping("/img")
    public Msg img(@RequestBody String base64Img){

        String base64 = base64Img.replace("%2F", "/");
        byte[] data = new byte[0];
        data = Base64.decodeBase64(base64);
        for(int i =0 ; i < data.length ;i++) {
            if(data[i] < 0) {
                data[i] += 256;
            }
        }
        //写入保存成jpeg文件
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:\\Final year project\\Springboot\\Screenshot\\test2.jpeg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(data);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

//        test 照片为你本机存储的对比照片
        float v = arcSoft.faceRecognition("D:\\Final year project\\Springboot\\File\\48a74b75b7b142aaa3069eb1990ce5bf.jpg", "D:\\Final year project\\Springboot\\File\\8ee4c53631d14a1d918d3a5c3ce63803.jpg",faceEngine);
        System.out.println("相似度为" + v);
        if (v==0){
            msg.setCode(400);
            msg.setMsg("请放入正确的人脸");
            msg.setAcc(0);
        }else {
            msg.setCode(200);
            msg.setMsg("识别正确");
            msg.setAcc(v);
        }
        return msg;
    }

}