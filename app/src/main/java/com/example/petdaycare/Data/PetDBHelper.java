package com.example.petdaycare.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.petdaycare.Pet;

import java.util.ArrayList;

public class PetDBHelper extends SQLiteOpenHelper {
    // Nombre de la BBDD
    private static final String DATABASE_NAME = "pets.db";

    // Versión actual de la BBDD
    private static final int DATABASE_VERSION = 1;

    public long insertPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PetContract.PetEntry.COLUMN_PET_NAME, pet.name);
        cv.put(PetContract.PetEntry.COLUMN_PET_BREED, pet.breed);
        cv.put(PetContract.PetEntry.COLUMN_PET_GENDER, pet.gender);
        cv.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, pet.weight);
        return db.insert(PetContract.PetEntry.TABLE_NAME, null, cv);
    }

    public PetDBHelper(Context context) {
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + " ("
                + PetContract.PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetContract.PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + PetContract.PetEntry.COLUMN_PET_GENDER + " TEXT NOT NULL, "
                + PetContract.PetEntry.COLUMN_PET_BREED + " TEXT NOT NULL, "
                + PetContract.PetEntry.COLUMN_PET_WEIGHT + " DOUBLE(5,2) NOT NULL);";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Pet> displayDBInfo() {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Proyección, donde tenemos las tablas que queremos consultar.
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED,
                PetContract.PetEntry.COLUMN_PET_GENDER,
                PetContract.PetEntry.COLUMN_PET_WEIGHT
        };

        Cursor cursor = db.query(
                PetContract.PetEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        //obtener los índices de las columnas
        int nameCol = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int breedCol = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
        int genderCol = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
        int weightCol = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);

        //Recorremos las filas de la tabla
        while (cursor.moveToNext()) {
            String name = cursor.getString(nameCol), breed = cursor.getString(breedCol),
                    gender = cursor.getString(genderCol);
            Double weight = cursor.getDouble(weightCol);
            if (name.isEmpty() || breed.isEmpty() || gender.isEmpty() || String.valueOf(weight).isEmpty()) {
                pets.add(null);
            } else {
                pets.add(new Pet(name, breed, weight, gender));
            }
        }

        return pets;
    }
}
