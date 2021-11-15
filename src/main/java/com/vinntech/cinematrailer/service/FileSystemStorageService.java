package com.vinntech.cinematrailer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystemStorageService implements StorageService{

    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct
    @Override
    public void init() {
        try{
            Files.createDirectories(Paths.get(storageLocation));
        }catch (IOException e){
            throw new RuntimeException("Error al inicializar la ubicación del almacén de archivos.");
        }

    }

    @Override
    public String storage(MultipartFile file) {
        String filename =file.getOriginalFilename();

        if(file.isEmpty()){
            throw new RuntimeException("No se puede almacenar un archivo vacio.");
        }

        try{
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Error al almacenar el archivo " + filename, e);
        }
        return filename;
    }

    @Override
    public Path load(String filename) {
        return Paths.get(storageLocation).resolve(filename);
    }

    @Override
    public Resource loadResource(String filename) {
        try{
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() ||)
        } catch (MalformedURLException e){
            throw new RuntimeException("No se pudo encontrar el archivo: " + filename, e);
        }

    }

    @Override
    public void delete(String filename) {
        Path file=load(filename);
        try{
            FileSystemUtils.deleteRecursively(file);
        }catch (IOException e){

        }

    }
}
