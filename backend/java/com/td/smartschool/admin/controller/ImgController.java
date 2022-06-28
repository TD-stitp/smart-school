package com.td.smartschool.admin.controller;

import com.td.smartschool.admin.pojo.Img;
import com.td.smartschool.admin.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @version 2.0
 * @author: XX
 * @date: 2021/11/4 14:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImgController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        return imageService.addImage(file).toString();
    }

    @GetMapping("/getImageUUID")
    public String getImageList() {
        return imageService.queryImageUuid().toString();

    }

    @GetMapping("/setImageTitle")
    public String setImageTitle(@RequestParam("title") String title, @RequestParam("uuid") String uuid) {
        return imageService.setImageTitle(title, uuid).toString();
    }

    @PostMapping("/updateImageIndex")
    public String updateImageIndex(@RequestBody String images) {
        return imageService.updateImageIndex(images).toString();
    }

    @RequestMapping("/getImage")
    public void getImage(String uuid, HttpServletResponse response) {
        response.setContentType("image/jpg");
        response.setCharacterEncoding("UTF-8");

        Img img = imageService.select(uuid);
        byte[] image = img.getImage();
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = new ByteArrayInputStream(image);
        int len;
        byte[] buffer = new byte[2048];
        try {
            while((len = inputStream.read(buffer, 0, 1024)) != -1) {
                assert outputStream != null;
                outputStream.write(buffer, 0, len);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
