package com.example.petdaycare;

public class Pet {
    public String name;
    public String breed;
    public double weight;
    public String gender;

    public Pet() {
    }

    public Pet(String name, String breed, double weight, String gender) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
