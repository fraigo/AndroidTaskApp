package me.franciscoigor.tasks.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "data.db";
    private static SQLiteDatabase database;
    private static ArrayList<DataModel> schemas;

    static {
        schemas = new ArrayList<DataModel>();
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static void addSchema(DataModel schema){
        schemas.add(schema);
    }

    public static SQLiteDatabase getDatabase(Context context){
        if (database==null){
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            database = dbhelper.getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (DataModel schema: schemas) {
            System.out.println("Creating");
            schema.create(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (DataModel schema: schemas) {
            try {
                System.out.println("Creating "+schema.getModelName());
                schema.create(db);
            }catch (Exception ex){

            }

        }
    }


    public static ArrayList<DataModel> getItems(String tableName, String where, String[] whereArgs) {
        Cursor cursor= queryItems(tableName, where, whereArgs);
        int count = cursor.getCount();
        int columns = cursor.getColumnCount();
        ArrayList<DataModel> models=new ArrayList<DataModel>();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();
            DataModel model = new DataModel(tableName);
            for (int j = 0; j < columns; j++) {
                String col= cursor.getColumnName(j);
                model.setValue(col, cursor.getString(j));
            }
            models.add(model);
        }
        return models;
    }

    public static ArrayList<DataModel> getAll(String tableName) {
        return getItems(tableName, null, null);
    }

    private static Cursor queryItems(String tableName, String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                tableName,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return cursor;
    }

    private static ContentValues getContentValues(HashMap<String,String> values) {
        ContentValues contentValues = new ContentValues();
        for(String field:values.keySet()){
            contentValues.put(field, values.get(field));
        }
        return contentValues;
    }

    public static void insert(DataModel item){
        database.insert(item.getModelName(), null, getContentValues(item.getValues()));
    }

    public static void update(DataModel item){
        database.update(item.getModelName(), getContentValues(item.getValues()),
                DataModel.FIELD_UUID + " = ?",
                new String[] { item.getUUID() });
    }

    public static void delete(DataModel item) {
        database.delete(item.getModelName(),
                DataModel.FIELD_UUID + " = ?",
                new String[] { item.getUUID() });
    }
}
