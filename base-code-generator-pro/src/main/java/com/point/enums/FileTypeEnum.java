package com.point.enums;

public enum FileTypeEnum {

    DIR("目录", "dir"),
    FILE("文件", "file"),
    GROUP("组别", "group");

    private final String text;

    private final String value;

    FileTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}