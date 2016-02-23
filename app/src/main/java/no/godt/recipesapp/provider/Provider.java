/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.provider;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;

import no.godt.recipesapp.GodtRecipesApp;
import no.godt.recipesapp.provider.godt.GodtImporter_;
import no.godt.recipesapp.provider.godt.GodtReader_;

@EBean
public class Provider {

    @App
    GodtRecipesApp mApp;

    public Importer provideImporter() {
        // temporary implementation - should returns importer based on method parameter
        return GodtImporter_.getInstance_(mApp.getApplicationContext());
    }

    public Reader provideReader() {
        // temporary implementation - should returns importer based on method parameter
        return GodtReader_.getInstance_(mApp.getApplicationContext());
    }

}
