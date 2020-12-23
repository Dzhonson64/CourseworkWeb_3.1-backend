package com.coursework.storage.service;

import com.coursework.storage.StorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {
  public static final String StorageFilesErrorInit = "STORAGE FILES FAILED";
  public static final String StorageFilesSuccessfullyInit = "STORAGE FILES SUCCESSFULLY INIT";

  private final Path rootLocation;

  @Autowired
  public FileSystemStorageService(StorageProperties properties) {
    this.rootLocation = Paths.get(properties.getLocation());
  }

  public void init() {
    String totalDirectory = rootLocation + "";
    try {
      Files.createDirectories(Paths.get(totalDirectory));
      log.info(StorageFilesSuccessfullyInit + ": " + totalDirectory);
    }
    catch (IOException e) {
      log.error(StorageFilesErrorInit + ": " + totalDirectory);
      throw new StorageException("Could not initialize storage", e);
    }
  }

  public void store(MultipartFile file, String filename) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + filename);
      }
      if (filename.contains("..")) {
        throw new StorageException(
          "Cannot store file with relative path outside current directory "
            + filename);
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, this.rootLocation.resolve(filename),
          StandardCopyOption.REPLACE_EXISTING);
      }
    }
    catch (IOException e) {
      throw new StorageException("Failed to store file " + filename, e);
    }
  }

  public void store(InputStream stream, String filename) {
    StringUtils.cleanPath(filename);
    try {
      if (filename.contains("..")) {
        // This is a security check
        throw new StorageException(
          "Cannot store file with relative path outside current directory "
            + filename);
      }

      Files.copy(stream, this.rootLocation.resolve(filename),
        StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException e) {
      throw new StorageException("Failed to store file " + filename, e);
    }
  }

  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      }
      else {
        throw new StorageFileNotFoundException(
          "Could not read file: " + filename);

      }
    }
    catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }
}
