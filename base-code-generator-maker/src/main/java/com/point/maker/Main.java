package com.point.maker;

import com.point.maker.generator.main.ZipGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        //GenerateCode generateCode = new GenerateCode();
        ZipGenerator generateCode = new ZipGenerator();
        generateCode.doGenerate();
    }
}