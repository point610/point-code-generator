package com.point;

import com.point.utils.Utils;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String from = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWang/Main.java";
        String to = System.getProperty("user.dir") + File.separator + ".temp";
        System.out.println(from);
        System.out.println(to);
        Utils.copyStaticFiles(from, to);
    }
}