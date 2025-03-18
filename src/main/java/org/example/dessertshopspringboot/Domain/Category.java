package org.example.dessertshopspringboot.Domain;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @NotNull
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private Double price;
    private String imgURL;
    private String description;
    @NotNull
    private Integer remain;
    @NotEmpty
    private String seller;


}
