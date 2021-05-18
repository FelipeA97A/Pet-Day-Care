package com.example.petdaycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.charset.Charset;

public class activity_pet extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        EditText name = findViewById(R.id.editTxtName);
        EditText weight = findViewById(R.id.editTxtWeight);
        EditText breed = findViewById(R.id.editTxtBreed);

        Spinner gender = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(this);

        // Recibimos la mascota desde la actividad anterior
        Bundle receivedObject = getIntent().getExtras();
        Pet receivedPet = (Pet)receivedObject.getSerializable("pet");

        //Toast.makeText(this, "" + receivedPet.name, Toast.LENGTH_SHORT).show();
        String raza = receivedPet.breed;
        //Toast.makeText(this, raza, Toast.LENGTH_SHORT).show();
        // Introducimos los datos de la mascota en los editText y el spinner.
        name.setText(receivedPet.name);
        weight.setText(String.valueOf(receivedPet.weight));
        breed.setText(raza);
        gender.setSelection(getGender(receivedPet));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public int getGender(Pet pet) {
        if (pet.gender.equalsIgnoreCase("hembra")) {
            return 0;
        } else {
            return 1;
        }
    }
}