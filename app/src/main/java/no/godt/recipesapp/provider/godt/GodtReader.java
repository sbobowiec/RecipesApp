/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.godt;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.Arrays;
import java.util.List;

import no.godt.recipesapp.provider.Reader;
import no.godt.recipesapp.provider.godt.dto.RecipeDTO;
import no.godt.recipesapp.provider.godt.rest.GodtRestClient;

@EBean
public class GodtReader implements Reader {

    private static final int LIMIT = 50;

    @RestService
    GodtRestClient mClient;

    @Override
    public List<RecipeDTO> getRecipes() {
        RecipeDTO[] recipes = mClient.getRecipes(LIMIT, 0);
        return Arrays.asList(recipes);
    }

}
