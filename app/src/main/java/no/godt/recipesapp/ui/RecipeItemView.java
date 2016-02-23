/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import no.godt.recipesapp.GodtRecipesApp;
import no.godt.recipesapp.R;

@EViewGroup(R.layout.view_recipe_item)
public class RecipeItemView extends RelativeLayout {

    @ViewById(R.id.recipe_image)
    ImageView mRecipeImage;

    @ViewById(R.id.title)
    TextView mTitle;

    @ViewById(R.id.description)
    TextView mDescription;

    @ViewById(R.id.elements)
    TextView mElements;

    @App
    GodtRecipesApp mApp;

    private RecipeItem mRecipeItem;

    public RecipeItemView(Context context) {
        super(context);
    }

    public RecipeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(RecipeItem recipeItem) {
        if (recipeItem == null) {
            throw new IllegalArgumentException("Recipe item cannot be null");
        }
        mRecipeItem = recipeItem;
        bindImage();
        bindTitle();
        bindDescription();
        bindElements();
    }

    private void bindImage() {
        String imageUrl = mRecipeItem.getImageUrl();
        Picasso.with(mApp.getApplicationContext())
                .load(imageUrl)
                .into(mRecipeImage);
    }

    private void bindTitle() {
        String title = mRecipeItem.getTitle();
        mTitle.setText(title);
    }

    private void bindDescription() {
        String description = mRecipeItem.getDescription();
        mDescription.setText(description);
    }

    private void bindElements() {
        String prefix = mApp.getApplicationContext().getString(R.string.ingredients);
        String elements = prefix + " " + mRecipeItem.getElements();
        mElements.setText(elements);
    }

}
