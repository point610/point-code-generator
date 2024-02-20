package com.point.springbootinit.model.freeimg.imgbb;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ImgBBUpload {
    private int status;

    private String success;

    private Data data;

    @NoArgsConstructor
    @lombok.Data
    public static class Data {
        private String id;
        private String title;
        private String url_viewer;
        private String url;
        private String display_url;
        private String width;
        private String height;
        private String size;
        private String time;
        private String expiration;
        private Image image;
        private Thumb thumb;
        private Medium medium;
        private String delete_url;
    }

    @NoArgsConstructor
    @lombok.Data
    public static class Image {
        private String filename;
        private String name;
        private String mime;
        private String extension;
        private String url;
    }

    @NoArgsConstructor
    @lombok.Data
    public static class Thumb {
        private String filename;
        private String name;
        private String mime;
        private String extension;
        private String url;
    }

    @NoArgsConstructor
    @lombok.Data
    public static class Medium {
        private String filename;
        private String name;
        private String mime;
        private String extension;
        private String url;
    }
}
