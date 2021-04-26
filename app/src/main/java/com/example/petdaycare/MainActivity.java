package com.example.petdaycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
//import com.example.petdaycare.Data.PetContract.UserEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Pet> pets = new ArrayList<Pet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainList = (ListView) findViewById(R.id.mainList);

        pets = setPets();
        PetAdapter petAdapter = new PetAdapter(this, 0, pets);
        mainList.setAdapter(petAdapter);
        mainList.setOnItemClickListener(this);
    }

    public ArrayList<Pet> setPets() {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(new Pet("Juana", "Galgo", 23.2, "Hembra"));
        pets.add(new Pet("Toby", "Golden Terrier", 24.8, "Macho"));
        pets.add(new Pet("Señor Bigotes", "Carlino", 8.5, "Macho"));
        pets.add(new Pet("Albóndiga", "Pastor Alemán", 23.2, "Hembra"));
        pets.add(new Pet("Coco", "Caniche", 13.2, "Hembra"));
        pets.add(new Pet("Mordiscos", "Chihuahua", 23.2, "Hembra"));
        pets.add(new Pet("Ares", "Mastín", 63.9, "Macho"));
        pets.add(new Pet("Manchas", "Dálmata", 21.1, "Hembra"));
        pets.add(new Pet("Peperoni", "Dóberman", 25.4, "Macho"));
        pets.add(new Pet("Junio", "Spaniel", 9.4, "Hembra"));
        pets.add(new Pet("Juanito", "Shiba", 9.2, "Macho"));
        pets.add(new Pet("Farfalla", "Galgo Italiano", 6.4, "Hembra"));
        return pets;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        openPetActivity();
    }

    public void openPetActivity() {
        Intent petActivity = new Intent(this, activity_pet.class);
        startActivity(petActivity);
    }
}