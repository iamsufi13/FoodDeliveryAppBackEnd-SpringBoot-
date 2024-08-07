package com.whizFortuneRestaurant.Product;

import com.whizFortuneRestaurant.ReviewsRating.ReviewRating;
import com.whizFortuneRestaurant.ReviewsRating.ReviewRatingDto;
import com.whizFortuneRestaurant.WishList.WishList;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setFoodType(product.getFoodType());
        dto.setMrp(product.getMrp());
        dto.setCustomizationAvailable(product.getCustomizationAvailable());
        dto.setImage(product.getImage());
        dto.setServeFor(product.getServeFor());
        dto.setSellerTags(product.getSellerTags());
        dto.setCustomizationStatus(product.getCustomizationStatus());
        dto.setNutritionStatus(product.getNutritionStatus());
        dto.setSpicyStatus(product.getSpicyStatus());
        dto.setFreeDeliveryStatus(product.getFreeDeliveryStatus());
        dto.setSellPrice(product.getSellPrice());
        dto.setOnionGarlicStatus(product.getOnionGarlicStatus());
        dto.setCatlogs(product.getCatlogs());

        // Convert reviews to DTOs
        List<ReviewRatingDto> reviewDtos = product.getReviewRatings().stream()
                .map(review -> new ReviewRatingDto(
                        review.getId(),
                        review.getUsers().getUserid(), // Assuming `getUser()` method exists in `ReviewRating`
                        review.getProduct().getId(),
                        review.getRating(),
                        review.getReviewmessage(),
                        review.getDate()
                ))
                .collect(Collectors.toList());
        dto.setReviews(reviewDtos);

        // Calculate average rating
        dto.calculateAverageRating();

        return dto;
    }
    public static ProductListingDto toProductListingDto(Product product){
        if (product==null){
            return null;
        }
        return new ProductListingDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getFoodType(),
                product.getSellerTags(),
                product.getMrp(),
                product.getImage(),
                product.getSpicyStatus(),
                product.getServeFor(),
                product.getFavorites()
        );
    }
    public static ProductDetailsDto toProductDetailsDto(Product product){
        if (product==null){
            return null;
        }

        ProductDetailsDto dto = new ProductDetailsDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getFoodType(),
                product.getMrp(),
                product.getImage(),
                product.getServeFor(),
                product.getSellerTags(),
                product.getSpicyStatus(),
                product.getFreeDeliveryStatus(),
                product.getSellPrice(),
                product.getOnionGarlicStatus(),
                product.getNutritionData(), // Assuming NutritionData is directly set
                null, // Placeholder for reviews which will be set separately
                0.0 // Placeholder for average rating which will be calculated later
        );

        // Convert reviews to ReviewRatingDto
        List<ReviewRatingDto> reviewDtos = product.getReviewRatings().stream()
                .map(review -> new ReviewRatingDto(
                        review.getId(),
                        review.getUsers().getUserid(), // Assuming `getUser()` method exists in `ReviewRating`
                        review.getProduct().getId(),
                        review.getRating(),
                        review.getReviewmessage(),
                        review.getDate()
                ))
                .collect(Collectors.toList());

        dto.setReviews(reviewDtos);

        // Calculate average rating
        dto.calculateAverageRating();

        return dto;
    }
    public static ProductByTagDto toProductByTagDto(Product product){
        if (product==null){
            return null;
        }

        ProductByTagDto dto = new ProductByTagDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getFoodType(),
                product.getImage(),
                product.getMrp(),
                null,
                0.0
        );

        // Convert reviews to ReviewRatingDto
        List<ReviewRatingDto> reviewDtos = product.getReviewRatings().stream()
                .map(review -> new ReviewRatingDto(
                        review.getId(),
                        review.getUsers().getUserid(), // Assuming `getUser()` method exists in `ReviewRating`
                        review.getProduct().getId(),
                        review.getRating(),
                        review.getReviewmessage(),
                        review.getDate()
                ))
                .collect(Collectors.toList());

        dto.setReviews(reviewDtos);

        // Calculate average rating
        dto.calculateAverageRating();

        return dto;
    }
    }

