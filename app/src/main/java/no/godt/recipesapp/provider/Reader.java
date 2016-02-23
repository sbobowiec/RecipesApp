/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider;

import java.util.List;

import no.godt.recipesapp.provider.godt.dto.RecipeDTO;

public interface Reader {

    List<RecipeDTO> getRecipes();

}
