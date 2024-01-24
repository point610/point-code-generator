package com.point.template;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.point.meta.Meta.ModelConfigDTO.ModelsDTO;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import cn.hutool.core.date.DateUtil;
import com.point.meta.Meta;
import com.point.utils.Utils;
import com.sun.javafx.binding.StringFormatter;
import sun.reflect.misc.FieldUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TemplateMaker {
    public static long makeTemplate(Meta meta, Long id, String searchString) {
        String projectPath = System.getProperty("user.dir");
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();

        // 创建隔离的空间,之后需要修改id为可选项,不是在这里生成
        if (id == null) {
            //long id = IdUtil.getSnowflakeNextId();
            id = 1750053546704556032L;
        }
        String tempDirPath = projectPath + File.separator + ".temp";
        String tempPath = tempDirPath + File.separator + id;
        if (!FileUtil.exist(tempPath)) {
            FileUtil.mkdir(tempPath);
            Utils.copyStaticDir(sourceRootPath, tempPath);
        }

        List<Meta.FileConfigDTO.FilesDTO> files = meta.getFileConfig().getFiles();
        for (Meta.FileConfigDTO.FilesDTO file : files) {
            // 获取文件的输入路径
            String fileinputpath = sourceRootPath + File.separator + file.getOutputPath();
            String fileoutputpath = meta.getFileConfig().getInputRootPath() + File.separator + file.getInputPath();
            // 获取文件的内容
            String filecontent;
            if (FileUtil.exist(fileoutputpath)) {
                filecontent = FileUtil.readUtf8String(fileinputpath);
            } else {
                filecontent = FileUtil.readUtf8String(fileinputpath);
            }
            // 开始修改文件
            String replacement = String.format("${%s}", meta.getModelConfig().getModels().get(0).getFieldName());
            String newfileconent = StrUtil.replace(filecontent, searchString, replacement);

            FileUtil.writeUtf8String(newfileconent, fileoutputpath);
        }

        String metaOutputDir = System.getProperty("user.dir") + File.separator + ".temp" + File.separator + id;
        String metaOutputPath = System.getProperty("user.dir") + File.separator + ".temp" + File.separator + id + File.separator + "meta.json";
        if (FileUtil.exist(metaOutputPath)) {
            // 获取存在的meta数据
            Meta oldmeta = JSONUtil.toBean(FileUtil.readUtf8String(metaOutputPath), Meta.class);
            List<Meta.FileConfigDTO.FilesDTO> oldFiles = oldmeta.getFileConfig().getFiles();
            List<ModelsDTO> oldModels = oldmeta.getModelConfig().getModels();

            // 加入追加的配置信息
            oldFiles.addAll(meta.getFileConfig().getFiles());
            oldModels.addAll(meta.getModelConfig().getModels());

            // 设置去重的配置信息
            oldmeta.getFileConfig().setFiles(Utils.distinctFiles(oldFiles));
            oldmeta.getModelConfig().setModels(Utils.distinctModels(oldModels));

            // 转化为json
            String metajson = JSONUtil.toJsonPrettyStr(oldmeta);
            FileUtil.writeUtf8String(metajson, metaOutputPath);
        } else {
            // 将各种信息注入meta中

            // 将对象转换为obj对象
            String metajson = JSONUtil.toJsonPrettyStr(meta);

            FileUtil.mkdir(metaOutputDir);

            FileUtil.writeUtf8String(metajson, metaOutputPath);
        }

        return id;
    }

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        String sourceRootPath = "E:/Java_Projection/point-code-generator/demo-project/nkw-demo-pro";

        // 创建隔离的空间,之后需要修改id为可选项,不是在这里生成
        //long id = IdUtil.getSnowflakeNextId();
        long id = 1750053546704556032L;
        String tempPath = projectPath + File.separator + ".temp" + File.separator + id;

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
        fileConfig.setInputRootPath(tempPath + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath)));
        fileConfig.setSourceRootPath(sourceRootPath);
        fileConfig.setOutputRootPath(".temp");
        fileConfig.setType("group");
        List<Meta.FileConfigDTO.FilesDTO> filesDTOList = new ArrayList<>();
        Meta.FileConfigDTO.FilesDTO filesDTOone = new Meta.FileConfigDTO.FilesDTO();
        filesDTOone.setInputPath("src/main/java/com/point/Main.java.ftl");
        filesDTOone.setOutputPath("src/main/java/com/point/Main.java");
        filesDTOone.setGenerateType("dynamic");
        filesDTOone.setType("file");
        filesDTOList.add(filesDTOone);
        fileConfig.setFiles(filesDTOList);

        // 编写各种meta信息
        String name = "base-code-generator-nkw";
        String description = "牛客网java代码模板生成器";
        String basePackage = "com.point";
        String version = "1.0";
        String author = "point";
        String createTime = DateUtil.now();
        Meta meta = new Meta();
        meta.setName(name);
        meta.setDescription(description);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setCreateTime(createTime);
        meta.setFileConfig(fileConfig);
        meta.setModelConfig(modelConfig);

        // 开始制作
        //makeTemplate(meta, sourceRootPath, id);
        makeTemplate(meta, id, "Main");
    }
}
