package no.godt.recipesapp.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import no.godt.recipesapp.R;
import no.godt.recipesapp.config.Actions;
import no.godt.recipesapp.db.DatabaseHelper;
import no.godt.recipesapp.domain.Recipe;
import no.godt.recipesapp.domain.repository.RecipeRepository;
import no.godt.recipesapp.provider.Importer;
import no.godt.recipesapp.provider.Provider;
import no.godt.recipesapp.util.ConnectionDetector;
import no.godt.recipesapp.util.KeyboardUtils;

@EActivity(R.layout.activity_recipes)
public class RecipesActivity extends AppCompatActivity {

    @ViewById(R.id.recipe_search_view)
    EditText mRecipeSearchView;

    @ViewById(R.id.recipes)
    ListView mRecipes;

    @Bean
    RecipeAdapter mAdapter;

    @Bean
    ConnectionDetector mConnectionDetector;

    @Bean
    Provider mProvider;

    @OrmLiteDao(helper = DatabaseHelper.class)
    RecipeRepository mRecipeRepository;

    private ProgressDialog mProgress;

    @AfterViews
    public void initViews() {
        initRecipeList();
        if (!recipesExistsInDb()) {
            if (!mConnectionDetector.isOnline()) {
                Toast.makeText(this, "Offline", Toast.LENGTH_LONG).show();
                return;
            }
            String message = getString(R.string.loading_recipes);
            mProgress = ProgressDialog.show(this, "", message, true);
            importData();
        } else {
            showRecipes();
        }
    }

    private void initRecipeList() {
        mRecipes.setAdapter(mAdapter);
        mRecipes.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    KeyboardUtils.hideSoftKeyboardIfOpened(RecipesActivity.this);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // ignore
            }
        });
    }

    private void showRecipes() {
        mAdapter.initItems();
    }

    private boolean recipesExistsInDb() {
        List<Recipe> recipes = mRecipeRepository.queryForAll();
        return !recipes.isEmpty();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recipes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_refresh_app_data:
                if (!mConnectionDetector.isOnline()) {
                    Toast.makeText(this, "Offline", Toast.LENGTH_LONG).show();
                    return super.onOptionsItemSelected(item);
                }
                refreshData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshData() {
        String message = getString(R.string.refresh_data);
        if (mProgress == null) {
            mProgress = ProgressDialog.show(this, "", message, true);
        } else {
            mProgress.setMessage(message);
            mProgress.show();
        }
        mRecipeSearchView.setText("");
        mRecipeSearchView.clearFocus();
        KeyboardUtils.hideSoftKeyboardIfOpened(this);

        importData();
    }

    private void importData() {
        Importer importer = mProvider.provideImporter();
        importer.importData();
    }

    @TextChange(R.id.recipe_search_view)
    public void filterRecipes(CharSequence text) {
        String query = text.toString();
        if (query.contains("\n")) {
            query = query.replace("\n", "");
            mRecipeSearchView.setText(query);
        }
        mAdapter.filter(query);
    }

    @Receiver(actions = Actions.RECIPES_UPDATED, local = true, registerAt = Receiver.RegisterAt.OnResumeOnPause)
    public void onRecipesUpdated() {
        mProgress.dismiss();
        mAdapter.initItems();
    }

}
