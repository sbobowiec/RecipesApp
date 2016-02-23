/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.godt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDTO {

    @JsonProperty(value = "url")
    private String url;

    ImageDTO() { }

    public String getUrl() {
        return url;
    }

}
