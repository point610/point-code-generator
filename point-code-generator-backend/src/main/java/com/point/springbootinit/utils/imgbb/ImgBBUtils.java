package com.point.springbootinit.utils.imgbb;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.point.springbootinit.model.freeimg.imgbb.ImgBBUpload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ImgBBUtils {
    public static String upload(File img, String api) {

        // 构造表单
        Map<String, Object> map = new HashMap<>();
        map.put("key", api);
        map.put("image", img);

        // 发送请求
        String josn = HttpUtil.createPost("https://api.imgbb.com/1/upload")
                .form(map).execute().body();

        ImgBBUpload imgBBUpload = JSONUtil.toBean(josn, ImgBBUpload.class);

        // 上传失败
        if (imgBBUpload.getStatus() != 200) {
            return "";
        }

        // 返回地址
        return imgBBUpload.getData().getUrl();
    }
}
