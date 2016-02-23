/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import no.godt.recipesapp.db.DatabaseHelper;
import no.godt.recipesapp.domain.Element;
import no.godt.recipesapp.domain.Recipe;
import no.godt.recipesapp.domain.repository.ElementRepository;
import no.godt.recipesapp.domain.repository.RecipeRepository;

@EBean
public class RecipeAdapter extends BaseAdapter {

    @RootContext
    Context mContext;

    @OrmLiteDao(helper = DatabaseHelper.class)
    RecipeRepository mRecipeRepository;

    @OrmLiteDao(helper = DatabaseHelper.class)
    ElementRepository mElementRepository;

    private List<RecipeItem> mCurrentItems = new ArrayList<>();
    private List<RecipeItem> mAllItems = new ArrayList<>();

    public void initItems() {
        List<Recipe> recipes = mRecipeRepository.queryForAll();
        mCurrentItems.clear();
        mAllItems.clear();
        addItems(recipes);
    }

    public void filter(String query) {
        mCurrentItems.clear();
        if (query.length() == 0) {
            mCurrentItems.addAll(mAllItems);
        } else {
            for (RecipeItem item : mAllItems) {
                if (item.matchesFilter(query)) {
                    mCurrentItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void addItems(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            addItem(recipe);
        }
        notifyDataSetChanged();
    }

    private void addItem(Recipe recipe) {
        long uniqueId = recipe.getUniqueId();
        List<Element> recipeElements = mElementRepository.findByRecipeUniqueId(uniqueId);
        RecipeItem recipeItem = new RecipeItem(recipe, recipeElements);
        mCurrentItems.add(recipeItem);
        mAllItems.add(recipeItem);
    }

    @Override
    public int getCount() {
        return mCurrentItems.size();
    }

    @Override
    public RecipeItem getItem(final int position) {
        if (position < mCurrentItems.size()) {
            return mCurrentItems.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecipeItemView recipeItemView;
        if (convertView == null) {
            recipeItemView = RecipeItemView_.build(mContext);
        } else {
            recipeItemView = (RecipeItemView) convertView;
        }
        recipeItemView.bind(getItem(position));

        return recipeItemView;
    }

}
