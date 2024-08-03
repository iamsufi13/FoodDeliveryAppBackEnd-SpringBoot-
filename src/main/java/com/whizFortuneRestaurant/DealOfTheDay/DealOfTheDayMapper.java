package com.whizFortuneRestaurant.DealOfTheDay;

public class DealOfTheDayMapper {
    public static DealOfTheDayDto toDealOfTheDayDto(DealOfTheDay dealOfTheDay) {
        if (dealOfTheDay == null) {
            return null;
        }

        return new DealOfTheDayDto(
                dealOfTheDay.getId(),
                dealOfTheDay.getOffer_price(),
                dealOfTheDay.getEnd_date(),
                dealOfTheDay.getStatus(),
                dealOfTheDay.getProduct().getId()
        );
    }
}
