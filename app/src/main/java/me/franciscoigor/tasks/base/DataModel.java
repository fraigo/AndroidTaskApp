package me.franciscoigor.tasks.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import me.franciscoigor.tasks.models.TaskModel;

public class DataModel {
    private String name;
    private ArrayList<String> fieldNames;
    private HashMap<String,String> values;
    public static final String FIELD_UUID = "uuid";

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


    public HashMap<String, String> getValues() {
        return values;
    }

    public void addField(String name){
        fieldNames.add(name);
    }

    public void setValue(String field, String value) {
        values.put(field,value);
    }

    public void setValue(String field, boolean value) {
        values.put(field,value ? "1" : "0");
    }

    public String getStringValue(String key){
        return values.get(key);
    }

    public boolean getBooleanValue(String key){
        return "1".equals(values.get(key));
    }

    public String getUUID() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataModel dataModel = (DataModel) o;
        return Objects.equals(name, dataModel.name) &&
                Objects.equals(fieldNames, dataModel.fieldNames) &&
                Objects.equals(values, dataModel.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fieldNames, values);
    }


}
