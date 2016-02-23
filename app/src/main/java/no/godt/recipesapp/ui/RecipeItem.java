/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.ui;

import java.util.List;
import java.util.Locale;

import no.godt.recipesapp.domain.Element;
import no.godt.recipesapp.domain.Recipe;

public class RecipeItem {

    private long uniqueId;
    private String title;
    private String description;
    private String elements;
    private String imageUrl;

    public RecipeItem(Recipe recipe, List<Element> elements) {
        initRecipeData(recipe);
        initElementsData(elements);
    }

    private void initRecipeData(Recipe recipe) {
        uniqueId = recipe.getUniqueId();
        title = recipe.getTitle();
        description = recipe.getDescription();
        imageUrl = recipe.getImageUrl();
    }

    private void initElementsData(List<Element> elements) {
        this.elements = "";
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            Element element = elements.get(i);
            this.elements += element.getName();
            if (i < size - 1) {
                this.elements += ", ";
            }
        }
    }

    public boolean matchesFilter(String queryFilter) {
        queryFilter = queryFilter.toLowerCase(Locale.getDefault());
        String text = title.toLowerCase(Locale.getDefault()) + " " +
                elements.toLowerCase(Locale.getDefault());

        return text.contains(queryFilter);
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

    public String getElements() {
        return elements;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
