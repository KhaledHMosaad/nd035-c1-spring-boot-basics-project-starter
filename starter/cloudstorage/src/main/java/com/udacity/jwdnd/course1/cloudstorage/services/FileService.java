package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    public int createFile(File file){
        return fileMapper.addFile(file);
    }
    public List<File> getAllFiles(){
        return fileMapper.getAllFiles();
    }
    public List<File> getFilesByUserId(int userId){
        return fileMapper.getFilesByUserId(userId);
    }
    public List<File> getFilesByUserName(String username){
        return fileMapper.getAllFilesByUserName(username);
    }
    public int deleteFileById(int fileId){
        return fileMapper.deleteFileById(fileId);
    }
    public File getFileForUserByFileId(String username, Integer fildId){
        return fileMapper.getFileForUserByFileId(username, fildId);
    }
    public File getFileById(int id) {
        return fileMapper.getFileById(id);
    }
}
