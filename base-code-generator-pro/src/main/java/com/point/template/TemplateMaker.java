package com.point.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;

import com.point.meta.Meta.ModelConfigDTO.ModelsDTO;

import java.io.File;
import java.util.ArrayList;

import cn.hutool.core.date.DateUtil;
import com.point.meta.Meta;

import java.util.List;

public class TemplateMaker {
    public static void main(String[] args) {
        // 编写各种meta信息
        String name = "base-code-generator-nkw";
        String description = "牛客网java代码模板生成器";
        String basePackage = "com.point";
        String version = "1.0";
        String author = "point";
        String createTime = DateUtil.now();

        // 编写model信息
        Meta.ModelConfigDTO modelConfig = new Meta.ModelConfigDTO();
        List<ModelsDTO> modelsDTOList = new ArrayList<>();
        ModelsDTO modelsDTOone = new ModelsDTO();
        modelsDTOone.setFieldName("needGit");
        modelsDTOone.setType("boolean");
        modelsDTOone.setDescription("是否生成.gitignore文件");
        modelsDTOone.setDefaultValue(true);
        modelsDTOone.setAbbr("g");
        modelsDTOone.setCondition("needGit");
        modelsDTOList.add(modelsDTOone);

        ModelsDTO modelsDTOtwo = new ModelsDTO();
        modelsDTOtwo.setFieldName("loop");
        modelsDTOtwo.setType("boolean");
        modelsDTOtwo.setDescription("是否生成循环");
        modelsDTOtwo.setDefaultValue(false);
        modelsDTOtwo.setAbbr("l");
        modelsDTOList.add(modelsDTOtwo);
        modelConfig.setModels(modelsDTOList);

        // 编写files信息
        Meta.FileConfigDTO fileConfig = new Meta.FileConfigDTO();
        List<Meta.FileConfigDTO.FilesDTO> filesDTOList = new ArrayList<>();
        Meta.FileConfigDTO.FilesDTO filesDTOone = new Meta.FileConfigDTO.FilesDTO();
        filesDTOone.setInputPath("src/main/java/com/point/Main.java.ftl");
        filesDTOone.setOutputPath("src/main/java/com/point/Main.java");
        filesDTOone.setGenerateType("dynamic");
        filesDTOone.setType("file");
        filesDTOList.add(filesDTOone);
        fileConfig.setFiles(filesDTOList);

        // 将各种信息注入meta中
        Meta meta = new Meta();
        meta.setName(name);
        meta.setDescription(description);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setCreateTime(createTime);
        meta.setFileConfig(fileConfig);
        meta.setModelConfig(modelConfig);

        // 将对象转换为obj对象
        String metajson = JSONUtil.toJsonPrettyStr(meta);
        String metaOutput = System.getProperty("user.dir") + File.separator + ".temp" + File.separator;
        if (new File(metaOutput).exists()) {
            new File(metaOutput).mkdirs();
        }

        FileUtil.writeUtf8String(metajson, metaOutput + "meta.json");

    }
}