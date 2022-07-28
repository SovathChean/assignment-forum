package com.example.springassignmentforum.core.constant;

public enum BasicTestUri {
    BASIC_TEST_URI("http://localhost:%s/api/"),
    POST_URI("posts"),
    FILE_URI("file"),
    LIKE_URI("like"),
    REGISER_URI("register"),
    COMMENT("comments"),
    USER_URI("users");
    public final String label;
    private BasicTestUri(String label) {
        this.label = label;
    }
}