package com.hyperhire.whatsappapiserver.entity;

public enum ContentType {
    TEXT(null),
    VIDEO("/root/video/"),
    FILE("/root/attachment/"),
    PICTURE("/root/picture/");

    private final String rootPath;

    ContentType(String fileRootPath) {
        this.rootPath = fileRootPath;
    }

    public String getRootPath() {
        return this.rootPath;
    }
}
