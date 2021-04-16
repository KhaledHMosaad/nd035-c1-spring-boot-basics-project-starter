package com.udacity.jwdnd.course1.cloudstorage.forms;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {
    MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public FileForm(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
