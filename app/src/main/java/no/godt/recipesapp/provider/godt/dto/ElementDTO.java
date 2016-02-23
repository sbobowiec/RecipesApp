/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.godt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementDTO {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "amount")
    private int amount;

    @JsonProperty(value = "hint")
    private String hint;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "symbol")
    private String symbol;

    @JsonProperty(value = "unitName")
    private String unitName;

    ElementDTO() { }

    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getHint() {
        return hint;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUnitName() {
        return unitName;
    }

}
