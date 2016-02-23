/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.godt;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import java.util.List;

import no.godt.recipesapp.GodtRecipesApp;
import no.godt.recipesapp.config.Actions;
import no.godt.recipesapp.db.DatabaseHelper;
import no.godt.recipesapp.domain.Element;
import no.godt.recipesapp.domain.Recipe;
import no.godt.recipesapp.domain.repository.ElementRepository;
import no.godt.recipesapp.domain.repository.RecipeRepository;
import no.godt.recipesapp.provider.Importer;
import no.godt.recipesapp.provider.godt.dto.ElementDTO;
import no.godt.recipesapp.provider.godt.dto.RecipeDTO;

@EBean
public class GodtImporter implements Importer {

    @App
    GodtRecipesApp mApp;

    @Bean
    GodtReader mReader;

    @OrmLiteDao(helper = DatabaseHelper.class)
    RecipeRepository mRecipeRepository;

    @OrmLiteDao(helper = DatabaseHelper.class)
    ElementRepository mElementRepository;

    @Override
    public void importData() {
        importRecipes();
    }

    @Background
    public void importRecipes() {
        List<RecipeDTO> recipeDTOs = mReader.getRecipes();
        importRecipesData(recipeDTOs);
        notifyRecipesDataUpdated();
    }

    private void importRecipesData(List<RecipeDTO> recipeDTOs) {
        for (RecipeDTO recipeDTO : recipeDTOs) {
            importRecipeData(recipeDTO);
            importRecipeElementsData(recipeDTO);
        }
    }

    private void importRecipeData(RecipeDTO recipeDTO) {
        Recipe recipe = mRecipeRepository.findByUniqueId(recipeDTO.getId());
        if (recipe == null) {
            recipe = new Recipe(recipeDTO);
            mRecipeRepository.create(recipe);
        } else {
            recipe.update(recipeDTO);
            mRecipeRepository.update(recipe);
        }
    }

    private void importRecipeElementsData(RecipeDTO recipeDTO) {
        List<ElementDTO> elementDTOs = recipeDTO.getElements();
        for (ElementDTO elementDTO : elementDTOs) {
            importRecipeElementData(elementDTO, recipeDTO);
        }
    }

    private void importRecipeElementData(ElementDTO elementDTO, RecipeDTO recipeDTO) {
        Element element = mElementRepository.findByUniqueId(elementDTO.getId());
        if (element == null) {
            element = new Element(elementDTO, recipeDTO.getId());
            mElementRepository.create(element);
        } else {
            element.update(elementDTO);
            mElementRepository.update(element);
        }
    }

    private void notifyRecipesDataUpdated() {
        Intent intent = new Intent(Actions.RECIPES_UPDATED);
        LocalBroadcastManager.getInstance(mApp.getApplicationContext()).sendBroadcast(intent);
    }

}
