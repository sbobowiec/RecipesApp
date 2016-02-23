/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.godt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDTO {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "ingredients")
    private List<IngredientDTO> ingredients;

    @JsonProperty(value = "images")
    private List<ImageDTO> images;

    RecipeDTO() { }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<ElementDTO> getElements() {
        return ingredients != null ? ingredients.get(0).getElements() : null;
    }

    public String getImageUrl() {
        return images != null ? images.get(0).getUrl() : null;
    }

}
