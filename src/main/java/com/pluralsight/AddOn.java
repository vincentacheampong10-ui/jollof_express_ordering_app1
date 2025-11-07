package com.pluralsight;

public class AddOn {
    private String name;
    private boolean premium;

    public AddOn(String name, boolean premium) {
        this.name = name;
        this.premium = premium;
    }


    public double getCost() {
        return premium ? 6 : 3; ///true : false
    }

    @Override
    public String toString() {
        return name + (premium ? " (+6)" : " (+3)"); ///string appended to name if premium is true (+6)
    }
}

