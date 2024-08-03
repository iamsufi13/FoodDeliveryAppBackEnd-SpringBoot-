package com.whizFortuneRestaurant.Banner;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping("/api/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

//    @Value("${upload.folder:src/main/resources/static/}")
//    private String uploadFolder;

    @GetMapping
    public ResponseEntity<List<ApiResponse<Banner>>> getAllBanners() {
        List<Banner> list = bannerService.getAllBanners();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

    @GetMapping("/general")
    public List<Banner> getBannerGeneral() {
        return bannerService.getAllBannersGeneral();
    }

    @Value("${upload.folder:src/main/resources/static/}")
    private String uploadFolder;

    @PostMapping("/add")
    public ResponseEntity<List<ApiResponse<Banner>>> addBanner(
            @RequestParam("status") int status,
            @RequestParam("platformType") String platformType,
            @RequestParam("imageFile") MultipartFile imageFile) {

        // Process image file
        String imageName = null;
        if (!imageFile.isEmpty()) {
            imageName = saveFile(imageFile, "banner"); // Save the file with a specified folder
        }

        // Create a new Banner object
        Banner banner = new Banner();
        banner.setStatus(status);
        banner.setPlatformtype(platformType);
        banner.setImage(imageName); // Store only the image name

        // Add the banner using the service
        bannerService.addBanner(banner);

        // Fetch the updated list of banners
        List<Banner> list = bannerService.getAllBanners();

        // Return response
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

    // Utility method to save the file
    private String saveFile(MultipartFile file, String subFolder) {
        if (file.isEmpty()) {
            System.out.println("No file uploaded");
            return null;
        }

        try {
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                throw new IOException("Original file name is null");
            }

            // Generate a safe file name
            String safeFileName = generateUniqueFileName(originalFileName);

            // Create the target location
            Path targetLocation = Paths.get(uploadFolder + File.separator + subFolder + File.separator + safeFileName);

            // Create directories if they do not exist
            Files.createDirectories(targetLocation.getParent());

            // Copy the file to the target location
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return only the file name
            return safeFileName;
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return UUID.randomUUID().toString() + fileExtension;
    }

    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<byte[]> serveImage(@PathVariable String filename) {
        Path file = Paths.get(uploadFolder + File.separator + "banner" + File.separator + filename);

        if (Files.exists(file)) {
            try {
                byte[] content = Files.readAllBytes(file);
                String mimeType = Files.probeContentType(file);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, mimeType)
                        .body(content);
            } catch (IOException e) {
                System.err.println("Error serving file: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}")
    public List<HashMap> getBannerById(@PathVariable long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Banner banner = bannerService.getBannerById(id);
        hashMap.put("data", banner);
        hashMap.put("message", "SUCCESS");
        hashMap.put("status", "true");
        return Collections.singletonList(hashMap);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateBanner(
            @PathVariable long id,
            @RequestParam("status") int status,
            @RequestParam("platformtype") String platformtype,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        System.out.println(status);
        System.out.println(platformtype);
        try {
            // Retrieve existing banner
            Banner banner = bannerService.getBannerById(id);

            // Update banner fields
            banner.setStatus(status);
            banner.setPlatformtype(platformtype);

            // Handle image update
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageName = saveFile(imageFile, "banner");
                banner.setImage(imageName);
            }

            // Save updated banner
            bannerService.updateBanner(id, banner);

            // Prepare response
            HashMap<String, Object> response = new HashMap<>();
            response.put("data", bannerService.getBannerById(id));
            response.put("message", "SUCCESS");
            response.put("status", "true");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the error details
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Failed to update banner"));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Banner>>> deleteBannerById(@PathVariable long id) {
        bannerService.deleteBannerById(id);
        List<Banner> list = bannerService.getAllBanners();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

//    private String saveFile(MultipartFile file, String subFolder) {
//        if (file.isEmpty()) {
//            System.out.println("No file uploaded");
//            return null;
//        }
//
//        try {
//            String originalFileName = file.getOriginalFilename();
//            if (originalFileName == null) {
//                throw new IOException("Original file name is null");
//            }
//
//            // Generate a safe file name
//            String safeFileName = generateUniqueFileName(originalFileName);
//
//            // Create the target location
//            Path targetLocation = Paths.get(uploadFolder + File.separator + subFolder + File.separator + safeFileName);
//
//            // Create directories if they do not exist
//            Files.createDirectories(targetLocation.getParent());
//
//            // Copy the file to the target location
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            // Return only the file name
//            return safeFileName;
//        } catch (IOException e) {
//            System.err.println("Error saving file: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String generateUniqueFileName(String originalFileName) {
//        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
//        return UUID.randomUUID().toString() + fileExtension;
//    }
//
//    @GetMapping("/image/{filename:.+}")
//    public ResponseEntity<byte[]> serveImage(@PathVariable String filename) {
//        Path file = Paths.get(uploadFolder + File.separator + "banner" + File.separator + filename);
//
//        if (Files.exists(file)) {
//            try {
//                byte[] content = Files.readAllBytes(file);
//                String mimeType = Files.probeContentType(file);
//
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_TYPE, mimeType)
//                        .body(content);
//            } catch (IOException e) {
//                System.err.println("Error serving file: " + e.getMessage());
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
