package com.td.smartschool.admin.service.Impl;

import com.td.smartschool.admin.mapper.ImageMapper;
import com.td.smartschool.admin.pojo.Img;
import com.td.smartschool.admin.service.ImageService;
import com.td.smartschool.customer.common.utils.UUIDUtil;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public  class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Override
    public Img select(String uuid) {
        return imageMapper.select(uuid);
    }

    @Override
    public JSONObject queryImageUuid() {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        List<Img> list = imageMapper.getUUIDs();
        result.put("code", "200");
        result.put("total", list.size());
        for (Img img : list) {
            JSONObject object = new JSONObject();
            object.put("subscript", img.getSubscript());
            object.put("uuid", img.getUuid());
            object.put("imageName", img.getImagename());
            object.put("title", img.getTitle());
            array.put(object);
        }
        result.put("UUIDs", array);
        return result;
    }

    @Override
    public JSONObject setImageIndex(int index, String uuid) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "修改成功");
        imageMapper.setSubscript(new Img().setUuid(uuid).setSubscript(index));
        return result;
    }

    @Override
    public JSONObject updateImageIndex(String images) {
        images = images.replace("\\", "")
                .replace("\"[", "[")
                .replace("]\"", "]");
        JSONArray array = new JSONObject(images).getJSONArray("images");
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "修改成功");
        for (int index = 0; index < array.length(); index++) {
            JSONObject temp = array.getJSONObject(index);
            imageMapper.setSubscript(new Img().setUuid(temp.getString("uuid")).setSubscript(temp.getInt("index")));
        }
        return result;
    }

    @Override
    public JSONObject setImageTitle(String title, String uuid) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "修改成功");
        imageMapper.setTitle(new Img().setUuid(uuid).setTitle(title));
        return result;
    }

    @Override
    public JSONObject addImage(MultipartFile file) {
        JSONObject result = new JSONObject();
        result.put("code", "200");
        result.put("msg", "上传成功");
        Img img = null;
        try {
            InputStream stream = file.getInputStream();
            byte[] image = new byte[(int) file.getSize()];
            stream.read(image);
            img = new Img(imageMapper.getMaxSubscript() + 1, file.getOriginalFilename(), String.valueOf(System.currentTimeMillis()), image, UUIDUtil.getImageUUID());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageMapper.insert(img);
        if (img != null) {
            result.put("subscript", img.getSubscript());
            result.put("title", img.getTitle());
            result.put("uuid", img.getUuid());
        } else {
            result.put("code", "202");
            result.put("msg", "上传失败");
        }
        return result;
    }
}
