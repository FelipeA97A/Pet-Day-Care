package com.example.petdaycare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PetAdapter extends ArrayAdapter<Pet> {
    public PetAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pet> pets) {
        super(context, resource, pets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.item_list_pets, parent, false);
        }
        Pet currentPet = getItem(position);
        TextView name = (TextView) listItem.findViewById(R.id.petName), breed = (TextView) listItem.findViewById(R.id.petBreed);
        name.setText(currentPet.name);
        breed.setText(currentPet.breed);

        return listItem;
    }
}
