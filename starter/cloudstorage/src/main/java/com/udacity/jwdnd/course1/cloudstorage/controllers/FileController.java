package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.ibatis.datasource.DataSourceException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@ControllerAdvice
@RequestMapping("/files")
public class FileController {
    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }
    @PostMapping
    public String post(
            Principal principal,
            @ModelAttribute("fileForm") FileForm fileForm
    ){

        User currentUser = userService.getUser(principal.getName());
        MultipartFile multipartFile = fileForm.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        long fileSize = multipartFile.getSize();
        Integer userId = currentUser.getUserId();
        byte[] fileData;
        try {
            fileData  = multipartFile.getBytes();
            if (multipartFile.isEmpty()){
                String message = "Please provide a file that is not empty.";
                return "redirect:/result?isSuccessful=" + false + "&message=" + message;
            }
            fileService.createFile(new File(null, fileName, contentType, fileSize, userId, fileData));
        } catch (IOException e) {
            String message = "Something went wrong with file upload.";
            return "redirect:/result?isSuccessful=" + false + "&message=" + message;
        } catch (DuplicateKeyException exception) {
            String message = "This file already exists";
            return "redirect:/result?isSuccessful=" + false + "&message=" + message;
        }
        String message = "The file has been uploaded successfully.";
        return "redirect:/result?isSuccessful=" + true + "&message=" + message;
    }

    @GetMapping("/{id}/delete")
    public String delete(
            Principal principal,
            @PathVariable int id
    ){
        File file = fileService.getFileForUserByFileId(principal.getName(), id);
        if(file == null){
            String message = "You dont have access to this file or it doesn't exist.";
            return "redirect:/result?isSuccessful=" + false + "&message=" + message;
        }
        fileService.deleteFileById(id);
        String message = "The file has been deleted successfully";
        return "redirect:/result?isSuccessful=" + true + "&message=" + message;
    }

    @GetMapping("/{id}/download")
    public void view(
            Principal principal,
            @PathVariable int id,
            HttpServletResponse response,
            Model model) throws Exception {
        File file = fileService.getFileForUserByFileId(principal.getName(), id);
        if(file == null){
            String message = "You dont have access to this file or it doesn't exist.";
            response.sendRedirect("/result?isSuccessful=" + false + "&message=" + message);
            return;
        }
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getFileName();
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(file.getFileData());
        outputStream.close();

    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleFileTooLargeError(){
        String message = "File is too large.";
        return "redirect:/result?isSuccessful=" + false + "&message=" + message;
    }
}
