package com.whizFortuneRestaurant.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

    @RestController
    public class  FileUploadController{

        @Value("${upload.folder}")
        private String uploadFolder;

        @PostMapping("/upload/banner")
        public String uploadBannerFile(@RequestParam("file") MultipartFile file) {
            return uploadFile(file, "banner");
        }

        @PostMapping("/upload/icon")
        public String uploadIconFile(@RequestParam("file") MultipartFile file) {
            return uploadFile(file, "icon");
        }

        private String uploadFile(MultipartFile file, String subFolder) {
            if (file.isEmpty()) {
                return "Please select a file to upload.";
            }

            try {
                // Get the file's original name
                String originalFileName = file.getOriginalFilename();
                System.out.println("Original file name " + originalFileName);
                // Define the target location
                Path targetLocation = Paths.get(uploadFolder + File.separator + "catalog" + File.separator + subFolder + File.separator + originalFileName);
                System.out.println("Target file name " + targetLocation);

                // Create directories if they do not exist
                Files.createDirectories(targetLocation.getParent());

                // Save the file to the target location
                Files.copy(file.getInputStream(), targetLocation);

                return "File uploaded successfully: " + originalFileName;
            } catch (IOException e) {
                e.printStackTrace();
                return "Could not upload the file: " + e.getMessage();
            }
        }
    }


