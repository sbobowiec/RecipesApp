/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider.godt.rest;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import no.godt.recipesapp.config.Parameters;
import no.godt.recipesapp.provider.common.http.OkHttpFactory;
import no.godt.recipesapp.provider.godt.dto.RecipeDTO;

@Rest(rootUrl = Parameters.GODT_URL,
        converters = { FormHttpMessageConverter.class,
                StringHttpMessageConverter.class,
                MappingJackson2HttpMessageConverter.class },
        requestFactory = OkHttpFactory.class)
public interface GodtRestClient {

    @Get("/api/getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1&limit={limit}&from={from}")
    RecipeDTO[] getRecipes(int limit, int from);

}
