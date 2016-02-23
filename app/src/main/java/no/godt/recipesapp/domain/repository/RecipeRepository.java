/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.domain.repository;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import no.godt.recipesapp.domain.Recipe;

public class RecipeRepository extends RuntimeExceptionDao<Recipe, Long> {

    private static final String LOG_TAG = RecipeRepository.class.getSimpleName();

    public RecipeRepository(Dao<Recipe, Long> dao) {
        super(dao);
    }

    public void clear() {
        try {
            TableUtils.clearTable(getConnectionSource(), Recipe.class);
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public Recipe findByUniqueId(long uniqueId) {
        Recipe recipe = null;

        QueryBuilder<Recipe, Long> queryBuilder = queryBuilder();
        Where<Recipe, Long> where = queryBuilder.where();
        SelectArg uniqueIdSelectArg = new SelectArg();
        uniqueIdSelectArg.setValue(uniqueId);

        try {
            recipe = where.eq(Recipe.UNIQUE_ID_FIELD_NAME, uniqueIdSelectArg).queryForFirst();
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return recipe;
    }

}
