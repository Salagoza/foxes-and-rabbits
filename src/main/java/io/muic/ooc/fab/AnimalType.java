package io.muic.ooc.fab;

import java.awt.*;

public enum AnimalType {
    RABBIT(0.08,Rabbit.class,Color.ORANGE),
    FOX(0.02,Fox.class,Color.BLUE),
    TIGER(0.02,Tiger.class,Color.RED),
    HUNTER(0.001,Hunter.class,Color.GREEN);

    private final double breedingProbabilty;

    private Class animalClass;

    private Color color;

    AnimalType(double breedingProbability,Class animalClass,Color color) {
        this.breedingProbabilty = breedingProbability;
        this.animalClass = animalClass;
        this.color = color;
    }

    public double getBreedingProbability() { return breedingProbabilty; }
    public Class getAnimalClass(){
        return animalClass;
    }
    public Color getColor(){ return color;}
}
