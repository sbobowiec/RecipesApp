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
import java.util.List;

import no.godt.recipesapp.domain.Element;

public class ElementRepository extends RuntimeExceptionDao<Element, Long> {

    private static final String LOG_TAG = RecipeRepository.class.getSimpleName();

    public ElementRepository(Dao<Element, Long> dao) {
        super(dao);
    }

    public void clear() {
        try {
            TableUtils.clearTable(getConnectionSource(), Element.class);
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public Element findByUniqueId(long uniqueId) {
        Element element = null;

        QueryBuilder<Element, Long> queryBuilder = queryBuilder();
        Where<Element, Long> where = queryBuilder.where();
        SelectArg uniqueIdSelectArg = new SelectArg();
        uniqueIdSelectArg.setValue(uniqueId);

        try {
            element = where.eq(Element.UNIQUE_ID_FIELD_NAME, uniqueIdSelectArg).queryForFirst();
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return element;
    }

    public List<Element> findByRecipeUniqueId(long recipeUniqueId) {
        List<Element> elements = null;

        QueryBuilder<Element, Long> queryBuilder = queryBuilder();
        Where<Element, Long> where = queryBuilder.where();
        SelectArg uniqueIdSelectArg = new SelectArg();
        uniqueIdSelectArg.setValue(recipeUniqueId);

        try {
            elements = where.eq(Element.RECIPE_UNIQUE_ID_FIELD_NAME, uniqueIdSelectArg).query();
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return elements;
    }

}
