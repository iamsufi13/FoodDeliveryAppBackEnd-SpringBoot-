package com.whizFortuneRestaurant.Product;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.whizFortuneRestaurant.Catlog1.CatlogService;
import com.whizFortuneRestaurant.Catlog1.Catlogs;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CatlogService catlogService;

    @Value("${upload.folder:src/main/resources/static/}")
    private String uploadFolder;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        System.out.println(products);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProductsTest() {
        List<Product> products = productService.getAllProducts();
        System.out.println(products);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/general")
    public List<ProductDto> getAllProductsExcludeOtherEntities() {
        return productService.getAllProductsExcludeOtherEntities();
    }
    @GetMapping("/by-tag")
    public ResponseEntity<List<ApiResponse<Product>>> getProductsByTag(@RequestParam("tag") String tag) {
        List<Product> products = productService.getProductsByTag(tag);
        if (products.isEmpty()) {
            return ResponseEntity.ok().body(ResponseUtils.createResponse(products,"SUCCESS","ok"));
        } else {
            return ResponseEntity.ok().body(ResponseUtils.createResponse(products,"SUCCESS","ok"));

        }
    }
    @GetMapping("/specialty")
    public List<ProductDto> getProductsBySpeciality() {
        return productService.getProductsBySpeciality();
    }

    @GetMapping("/productdetails/{id}")
    public ResponseEntity<List<HashMap<String, Object>>> getProductDetailsPage(@PathVariable long id) {
        // Retrieve the product from the service
        Product product = productService.getProductById(id);

        // Create a HashMap for the response
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "true");
        hashMap.put("data", product);
        hashMap.put("message", "SUCCESS");

        // Wrap the HashMap in a list
        List<HashMap<String, Object>> responseList = Collections.singletonList(hashMap);

        // Return the response list wrapped in ResponseEntity
        return ResponseEntity.ok(responseList);
    }



//    @GetMapping("/productdetailspage")
//    public ResponseEntity<List<ApiResponse<ProductDto>>>


    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("foodType") String foodType,
            @RequestParam("mrp") int mrp,
            @RequestParam("customizationAvailable") int customizationAvailable,
            @RequestParam("serveFor") long serveFor,
            @RequestParam("sellerTags") String sellerTags,
            @RequestParam("customizationStatus") int customizationStatus,
            @RequestParam("nutritionStatus") int nutritionStatus,
            @RequestParam("spicyStatus") int spicyStatus,
            @RequestParam("freeDeliveryStatus") int freeDeliveryStatus,
            @RequestParam("sellPrice") int sellPrice,
            @RequestParam("onionGarlicStatus") int onionGarlicStatus,
            @RequestParam("catlogId") long catlogId,
            @RequestParam("imageFile") MultipartFile imageFile) {

        String imageName = null;
        if (!imageFile.isEmpty()) {
            imageName = saveFile(imageFile, "product");
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setFoodType(FoodType.valueOf(foodType)); // Adjust if FoodType is different
        product.setMrp(mrp);
        product.setCustomizationAvailable(customizationAvailable);
        product.setServeFor(serveFor);
        product.setSellerTags(sellerTags);
        product.setCustomizationStatus(customizationStatus);
        product.setNutritionStatus(nutritionStatus);
        product.setSpicyStatus(spicyStatus);
        product.setFreeDeliveryStatus(freeDeliveryStatus);
        product.setSellPrice(sellPrice);
        product.setOnionGarlicStatus(onionGarlicStatus);
        product.setImage(imageName); // Store only the image name

        Catlogs catlogs = catlogService.getCatlogById(catlogId);
        product.setCatlogs(catlogs);

        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }


    //    private String saveFile(MultipartFile file, String subFolder) {
//        if (file.isEmpty()) {
//            return null;
//        }
//
//        try {
//            String originalFileName = file.getOriginalFilename();
//            if (originalFileName == null) {
//                throw new IOException("Original file name is null");
//            }
//
//            String safeFileName = generateUniqueFileName(originalFileName);
//            Path targetLocation = Paths.get(uploadFolder, subFolder, safeFileName);
//
//            // Ensure the directory exists
//            Files.createDirectories(targetLocation.getParent());
//
//            // Save the file to the target location
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            // Return the relative URL path to the file
//            return "/uploads/" + subFolder + "/" + safeFileName;
//        } catch (IOException e) {
//            // Use a logger for error reporting
//            System.err.println("Error saving file: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
   // }
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





//    private String generateUniqueFileName(String originalFileName) {
//        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
//        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
//        String uniqueId = java.util.UUID.randomUUID().toString();
//        return baseName + "_" + uniqueId + extension;
//    }


    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<byte[]> serveImage(@PathVariable String filename) {
        Path file = Paths.get(uploadFolder + File.separator + "product" + File.separator + filename);

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
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        System.out.println("updating product " + product);
        Product updatedProduct = productService.updateProduct(id, product);
        System.out.println("updated");
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        System.out.println("Received request to delete product with ID: " + id);
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error occurred while deleting product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllProdcut(){
        System.out.println("deleting all products");
        productService.deleteAllProducts();
        return ResponseEntity.noContent().build();

    }
}
