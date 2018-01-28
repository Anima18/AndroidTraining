package com.example.chris.androidtraining.sharing.sharing_file_data;

/**
 * Created by Admin on 2018/1/28.
 */

public class FileInfo {
    private String filePath;
    private String fileName;

    public FileInfo(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
