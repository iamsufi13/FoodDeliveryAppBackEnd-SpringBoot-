package com.whizFortuneRestaurant.Utils;

import java.util.Collections;
import java.util.List;

public class ResponseUtils {

    public static <T> List<ApiResponse<T>> createResponse(Object response_data, String message, String status) {
        T responseData = (T) response_data;
        ApiResponse<T> apiResponse = new ApiResponse<>(status,message, responseData);
        return Collections.singletonList(apiResponse);
    }

}