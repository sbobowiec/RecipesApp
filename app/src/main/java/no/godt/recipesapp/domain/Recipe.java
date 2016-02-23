/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import no.godt.recipesapp.provider.godt.dto.RecipeDTO;

@DatabaseTable(tableName = "recipe")
public class Recipe {

    public static final String UNIQUE_ID_FIELD_NAME = "uniqueId";
    public static final String TITLE_FIELD_NAME = "title";
    public static final String DESCRIPTION_FIELD_NAME = "description";
    public static final String IMAGE_URL_FIELD_NAME = "image_url";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = UNIQUE_ID_FIELD_NAME)
    private long uniqueId;

    @DatabaseField(columnName = TITLE_FIELD_NAME)
    private String title;

    @DatabaseField(columnName = DESCRIPTION_FIELD_NAME)
    private String description;

    @DatabaseField(columnName = IMAGE_URL_FIELD_NAME)
    private String imageUrl;

    Recipe() {
        // needed by ORM lite
    }

    public Recipe(RecipeDTO recipeDTO) {
        uniqueId = recipeDTO.getId();
        init(recipeDTO);
    }

    public void update(RecipeDTO recipeDTO) {
        init(recipeDTO);
    }

    private void init(RecipeDTO recipeDTO) {
        title = recipeDTO.getTitle();
        description = recipeDTO.getDescription();
        imageUrl = recipeDTO.getImageUrl();
    }

    public long getId() {
        return id;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
