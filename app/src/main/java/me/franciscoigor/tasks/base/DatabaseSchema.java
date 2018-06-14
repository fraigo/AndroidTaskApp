package me.franciscoigor.tasks.base;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DatabaseSchema {
    private String name;
    private ArrayList<String> fieldNames;
    private UUID uuid;
    private HashMap<String,String> values;

    public DatabaseSchema(String name){
        this.name =name;
        this.uuid=UUID.randomUUID();
        this.fieldNames = new ArrayList<String>();
        this.values = new HashMap<String,String>();
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

    public void addField(String name){
        fieldNames.add(name);
    }

    public void setValue(String field, String value) {
        if (fieldNames.contains(field)){
            values.put(field,value);
        }
    }

    public String getName() {
        return name;
    }
}
