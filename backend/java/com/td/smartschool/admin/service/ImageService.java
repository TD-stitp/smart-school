package com.td.smartschool.admin.service;

import com.td.smartschool.admin.pojo.Img;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author K8lyN
 */
@Service
public interface ImageService {

    Img select(String uuid);

    JSONObject queryImageUuid();

    JSONObject setImageIndex(int index, String uuid);

    JSONObject updateImageIndex(String images);

    JSONObject setImageTitle(String title, String uuid);

    JSONObject addImage(MultipartFile file);

}
