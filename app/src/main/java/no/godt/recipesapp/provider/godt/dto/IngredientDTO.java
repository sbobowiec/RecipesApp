/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.godt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientDTO {

    @JsonProperty(value = "elements")
    private List<ElementDTO> elements;

    IngredientDTO() { }

    public List<ElementDTO> getElements() {
        return elements;
    }

}
