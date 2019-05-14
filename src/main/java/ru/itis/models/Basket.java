package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    private Long basketID;
    private Long userID;
//
//    private BasketState state;
//    private String confirmString;
//
//    public boolean isEnabled() {
//        return this.getState().equals(BasketState.CONFIRMED);
//    }

    private User user;
    private List<Product> products;
}
