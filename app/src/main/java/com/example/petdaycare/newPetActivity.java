package com.example.petdaycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petdaycare.Data.PetDBHelper;

public class newPetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText name, breed, weight;
    Spinner genderSpinner;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        Button newPet = findViewById(R.id.newPetButton);
        newPet.setOnClickListener(this);

        name = findViewById(R.id.editTxtName);
        breed = findViewById(R.id.editTxtBreed);
        weight = findViewById(R.id.editTxtWeight);

        // Introducimos los datos a mostrar en el spinner
        genderSpinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(this);
    }

    public void newInsertPet() {
        Pet newPet = new Pet(name.getText().toString(), breed.getText().toString(), Double.parseDouble(weight.getText().toString()), gender);
        PetDBHelper DBH = new PetDBHelper(this);
        double test = DBH.insertPet(newPet);
        Log.i("Id de la mascota nueva:", "" + test);
    }

    // Métodos del spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Listener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newPetButton:
                newInsertPet();
                break;
        }
    }
}