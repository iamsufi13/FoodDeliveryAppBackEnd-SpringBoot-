package com.whizFortuneRestaurant.Catlog1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/catlog")
public class CatlogController {

    @Autowired
    private CatlogService catlogService;

    @Value("${upload.folder}")
    private String uploadFolder;

    @PostMapping("/add")
    public ResponseEntity<Catlogs> addCatlog(
            @RequestParam("name") String name,
            @RequestParam("status") int status,
            @RequestParam("bannerFile") MultipartFile bannerFile,
            @RequestParam("iconFile") MultipartFile iconFile) {

        // Save files and get file names
        String bannerFileName = saveFile(bannerFile, "banner");
        String iconFileName = saveFile(iconFile, "icon");

        // Create and populate Catlogs object
        Catlogs catlogs = new Catlogs();
        catlogs.setName(name);
        catlogs.setStatus(status);
        if (bannerFileName != null) {
            catlogs.setBannerImage(bannerFileName);
        }
        if (iconFileName != null) {
            catlogs.setIcon(iconFileName);
        }

        // Save Catlogs object
        Catlogs savedCatlogs = catlogService.addCatlog(catlogs);

        return ResponseEntity.ok(savedCatlogs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Catlogs> getCatlogById(@PathVariable long id) {
        Catlogs catlogs = catlogService.getCatlogById(id);
        if (catlogs != null) {
            return ResponseEntity.ok(catlogs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/general")
    public ResponseEntity<List<Catlogs>> getAllcatlogsWithoutOtherInfo() {
        List<Catlogs> catlogsList = catlogService.getAllCatlog();
        return catlogsList != null ? ResponseEntity.ok(catlogsList) : ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Catlogs>> getAllCatlogs() {
        List<Catlogs> catlogsList = catlogService.getAllCatlog();
        return ResponseEntity.ok(catlogsList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Catlogs> updateCatlog(
            @PathVariable long id,
            @RequestBody Catlogs updatedCatlog) {
        Catlogs catlogs = catlogService.updateCatlog(id, updatedCatlog);
        if (catlogs != null) {
            return ResponseEntity.ok(catlogs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCatlogById(@PathVariable long id) {
        boolean deleted = catlogService.deleteCatlogById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String saveFile(MultipartFile file, String subFolder) {
        if (file.isEmpty()) {
            return null;
        }

        try {
            // Generate a unique file name
            String originalFileName = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFileName != null && originalFileName.lastIndexOf('.') > 0) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            }

            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            Path targetLocation = Paths.get(uploadFolder + File.separator + subFolder + File.separator + uniqueFileName);

            Files.createDirectories(targetLocation.getParent());
            Files.copy(file.getInputStream(), targetLocation);

            // Return the file name only
            return uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/deleteall")
    public void deleteAll() {
        catlogService.deleteAll();
    }

    @GetMapping("/image/{subFolder}/{filename:.+}")
    public ResponseEntity<byte[]> serveImage(
            @PathVariable String subFolder,
            @PathVariable String filename) {
        Path file = Paths.get(uploadFolder + File.separator + subFolder + File.separator + filename);

        if (Files.exists(file)) {
            try {
                byte[] content = Files.readAllBytes(file);
                String mimeType = Files.probeContentType(file);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, mimeType)
                        .body(content);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
