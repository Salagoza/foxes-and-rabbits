package io.muic.ooc.fab;

public class Rabbit extends Animal {
    // Characteristics shared by all rabbits (class variables).

    @Override
    protected Location moveToNewLocation() {
        return field.freeAdjacentLocation(getLocation());
    }

    @Override
    public int getMaxAge() { return 40; }

    @Override
    protected double getBreedingProbability() {
        return 0.12;
    }

    @Override
    protected int getMaxLitterSize() {
        return 4;
    }

    @Override
    protected int getBreedingAge() { return 5; }

    @Override
    public int getFoodLevel() { return ANIMAL_FOOD_VALUE; }

}
