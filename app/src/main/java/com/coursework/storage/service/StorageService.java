package com.coursework.storage.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;

public interface StorageService {
  void init();

  void store(MultipartFile file, String filename);

  void store(InputStream stream, String filename);

  Path load(String filename);

  Resource loadAsResource(String filename);

  void deleteAll();
}
