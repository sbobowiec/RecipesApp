/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider;

import java.util.List;

import no.godt.recipesapp.domain.Recipe;
import no.godt.recipesapp.provider.godt.dto.RecipeDTO;

public interface Converter {

    List<Recipe> convertRecipeDTOs(List<RecipeDTO> recipeDTOs);

}
