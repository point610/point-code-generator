package com.point;

import cn.hutool.core.io.resource.ClassPathResource;
import com.point.meta.Meta;
import com.point.meta.MetaManager;
import com.point.template.GenerateCode;
import com.point.utils.Utils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        GenerateCode generateCode = new GenerateCode();
        generateCode.generate();
    }
}