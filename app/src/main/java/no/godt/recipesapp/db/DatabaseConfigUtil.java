/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import no.godt.recipesapp.domain.Element;
import no.godt.recipesapp.domain.Recipe;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {
            Recipe.class, Element.class };

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }

}
