/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import no.godt.recipesapp.provider.godt.dto.ElementDTO;

@DatabaseTable(tableName = "element")
public class Element {

    public static final String UNIQUE_ID_FIELD_NAME = "uniqueId";
    public static final String RECIPE_UNIQUE_ID_FIELD_NAME = "recipeUniqueId";
    public static final String NAME_FIELD_NAME = "name";
    public static final String AMOUNT_FIELD_NAME = "amount";
    public static final String HINT_FIELD_NAME = "hint";
    public static final String SYMBOL_FIELD_NAME = "symbol";
    public static final String UNIT_NAME_FIELD_NAME = "unitName";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(columnName = UNIQUE_ID_FIELD_NAME)
    private long uniqueId;

    @DatabaseField(columnName = RECIPE_UNIQUE_ID_FIELD_NAME)
    private long recipeUniqueId;

    @DatabaseField(columnName = NAME_FIELD_NAME)
    private String name;

    @DatabaseField(columnName = AMOUNT_FIELD_NAME)
    private int amount;

    @DatabaseField(columnName = HINT_FIELD_NAME)
    private String hint;

    @DatabaseField(columnName = SYMBOL_FIELD_NAME)
    private String symbol;

    @DatabaseField(columnName = UNIT_NAME_FIELD_NAME)
    private String unitName;

    Element() {
        // needed by ORM lite
    }

    public Element(ElementDTO elementDTO, long recipeUniqueId) {
        uniqueId = elementDTO.getId();
        this.recipeUniqueId = recipeUniqueId;
        init(elementDTO);
    }

    public void update(ElementDTO elementDTO) {
        init(elementDTO);
    }

    private void init(ElementDTO elementDTO) {
        name = elementDTO.getName();
        amount = elementDTO.getAmount();
        hint = elementDTO.getHint();
        symbol = elementDTO.getSymbol();
        unitName = elementDTO.getUnitName();
    }

    public long getId() {
        return id;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public long getRecipeUniqueId() {
        return recipeUniqueId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getHint() {
        return hint;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUnitName() {
        return unitName;
    }

}
