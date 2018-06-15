package me.franciscoigor.tasks.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
                System.out.println("Creating "+schema.getName());
                schema.create(db);
            }catch (Exception ex){

            }

        }
    }
}
