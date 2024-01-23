package ${basePackage}.picocli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.model.NKWConfig;
import ${basePackage}.utils.Utils;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable
<Integer> {

    <#list modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
            /**
            * ${modelInfo.groupName}
            */
            static NKWConfig.${modelInfo.type} ${modelInfo.groupKey} = new NKWConfig.${modelInfo.type}();

            @Command(name = "${modelInfo.groupKey}")
            @Data
            public static class ${modelInfo.type}Command implements Runnable {
            <#list modelInfo.models as subModelInfo>
                @Option(names = {<#if subModelInfo.abbr??>"-${subModelInfo.abbr}", </#if>"--${subModelInfo.fieldName}"}, arity = "0..1", <#if subModelInfo.description??>description = "${subModelInfo.description}", </#if>interactive = true, echo = true)
                private ${subModelInfo.type} ${subModelInfo.fieldName}<#if subModelInfo.defaultValue??> = ${subModelInfo.defaultValue?c}</#if>;
            </#list>

            @Override
            public void run() {
            <#list modelInfo.models as subModelInfo>
                ${modelInfo.groupKey}.${subModelInfo.fieldName} = ${subModelInfo.fieldName};
            </#list>
            }
            }

        <#else>
            @Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}", </#if>"--${modelInfo.fieldName}"}, arity = "0..1", <#if modelInfo.description??>description = "${modelInfo.description}", </#if>interactive = true, echo = true)
            private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
        </#if>
    </#list>

    public Integer call() throws Exception {

    // 将文件目录都复制到对应的位置
    String fromDir = "${fileConfig.inputRootPath}";
    String toDir ="${fileConfig.outputRootPath}";
    File toDirPath = new File(toDir);
    if (toDirPath.exists()) {
    toDirPath.mkdirs();
    }

    System.out.println(fromDir);
    System.out.println(toDir);
    // Utils.copyStaticDir(fromDir, toDir);

    // 替换动态的模板文件
    String fromFile;
    String toFile;


    <#list modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
            <#if modelInfo.condition??>
                if (${modelInfo.condition}) {
                CommandLine commandLine = new CommandLine(${modelInfo.type}Command.class);
                commandLine.execute(${modelInfo.allArgsStr});
                }
            <#else>
                CommandLine commandLine = new CommandLine(${modelInfo.type}Command.class);
                commandLine.execute(${modelInfo.allArgsStr});
            </#if>
        </#if>
    </#list>


    // 生成配置文件
    NKWConfig model = new NKWConfig();
    BeanUtil.copyProperties(this, model);
    <#list modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
            model.${modelInfo.groupKey} = ${modelInfo.groupKey};
        </#if>
    </#list>
    System.out.println("配置信息：" + model);
    // Utils.doGenerate(fromFile, toFile, model);

    <#-- 获取模型变量 -->
    <#list modelConfig.models as modelInfo>
    <#-- 有分组 -->
        <#if modelInfo.groupKey??>
            <#list modelInfo.models as subModelInfo>
                ${subModelInfo.type} ${subModelInfo.fieldName} = model.${modelInfo.groupKey}.${subModelInfo.fieldName};
            </#list>
        <#else>
            ${modelInfo.type} ${modelInfo.fieldName} = model.${modelInfo.fieldName};
        </#if>
    </#list>


    <#list fileConfig.files as fileInfo>
        <#if fileInfo.groupKey??>
            <#if fileInfo.condition??>
                if (${fileInfo.condition}){
                <#list fileInfo.files as file>
                    fromFile = new File(fromDir, "${file.inputPath}").getAbsolutePath();
                    toFile = new File(toDir, "${file.outputPath}").getAbsolutePath();
                    <#if file.generateType == "static">
                        Utils.copyStaticFiles(fromFile, toFile);
                    <#else>
                        Utils.doGenerate(fromFile, toFile, model);
                    </#if>
                </#list>
                }
            <#else >
                <#list fileInfo.files as file>
                    fromFile = new File(fromDir, "${file.inputPath}").getAbsolutePath();
                    toFile = new File(toDir, "${file.outputPath}").getAbsolutePath();
                    <#if file.generateType == "static">
                        Utils.copyStaticFiles(fromFile, toFile);
                    <#else>
                        Utils.doGenerate(fromFile, toFile, model);
                    </#if>
                </#list>
            </#if>
        <#else>
            <#if fileInfo.condition??>
                if (${fileInfo.condition}){
                fromFile = new File(fromDir, "${fileInfo.inputPath}").getAbsolutePath();
                toFile = new File(toDir, "${fileInfo.outputPath}").getAbsolutePath();
                <#if fileInfo.generateType == "static">
                    Utils.copyStaticFiles(fromFile, toFile);
                <#else>
                    Utils.doGenerate(fromFile, toFile, model);
                </#if>
                }
            <#else>
                fromFile = new File(fromDir, "${fileInfo.inputPath}").getAbsolutePath();
                toFile = new File(toDir, "${fileInfo.outputPath}").getAbsolutePath();
                <#if fileInfo.generateType == "static">
                    Utils.copyStaticFiles(fromFile, toFile);
                <#else>
                    Utils.doGenerate(fromFile, toFile, model);
                </#if>

            </#if>
        </#if>

    </#list>

    return 0;
    }
    }