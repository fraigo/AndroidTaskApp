package me.franciscoigor.tasks.base;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DataModel {
    private String name;
    private ArrayList<String> fieldNames;
    private HashMap<String,String> values;
    private static final String FIELD_UUID = "uuid";

    public DataModel(String name){
        this.name =name;
        this.fieldNames = new ArrayList<String>();
        addField(FIELD_UUID);
        this.values = new HashMap<String,String>();
        setValue(FIELD_UUID, UUID.randomUUID().toString());
    }


    public void create(SQLiteDatabase db) {
        String sqlCreate=String.format("CREATE TABLE %s (", name);
        sqlCreate += "_id integer primary key autoincrement";
        for (String field:fieldNames) {
            sqlCreate += String.format(" , %s",field);
        }
        sqlCreate += " )";
        System.out.println("SQL "+sqlCreate);
        db.execSQL(sqlCreate);
    }

    private static ContentValues getContentValues(HashMap<String,String> values) {
        ContentValues contentValues = new ContentValues();
        for(String field:values.keySet()){
            contentValues.put(field, values.get(field));
        }
        return contentValues;
    }

    public void insert(SQLiteDatabase db){
        db.insert(name, null, getContentValues(values));
    }

    public void update(SQLiteDatabase db){
        db.update(name, getContentValues(values),
                FIELD_UUID + " = ?",
                new String[] { getUuid() });
    }

    public void addField(String name){
        fieldNames.add(name);
    }

    public void setValue(String field, String value) {
        if (fieldNames.contains(field)){
            values.put(field,value);
        }
    }

    public String getStringValue(String key){
        return values.get(key);
    }

    public String getUuid() {
        return getStringValue(FIELD_UUID);
    }

    public String getModelName() {
        return name;
    }

    @Override
    public String toString() {
        return "DataModel." +
                name + " : " +
                //" {" + fieldNames + "} "
                " (" + values +
                ")";
    }
}
