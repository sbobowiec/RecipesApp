/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import no.godt.recipesapp.R;
import no.godt.recipesapp.domain.Element;
import no.godt.recipesapp.domain.Recipe;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "godt_recipes.db";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(LOG_TAG, "onCreate");
            TableUtils.createTableIfNotExists(connectionSource, Recipe.class);
            TableUtils.createTableIfNotExists(connectionSource, Element.class);
        } catch (SQLException ex) {
            Log.e(LOG_TAG, "Cannot create database", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(LOG_TAG, "onUpgrade");

            // drop old tables
            TableUtils.dropTable(connectionSource, Recipe.class, true);
            TableUtils.dropTable(connectionSource, Element.class, true);
            // create new ones
            onCreate(db, connectionSource);
        } catch (SQLException ex) {
            Log.e(LOG_TAG, "Cannot drop databases", ex);
            throw new RuntimeException(ex);
        }
    }

}
