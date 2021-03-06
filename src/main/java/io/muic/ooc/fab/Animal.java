package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public abstract class Animal {

    // Whether the animal is alive or not.
    private boolean alive;
    // The fox's position.
    protected Location location;
    // The field occupied.
    protected Field field;
    // Individual characteristics (instance fields).
    // The fox's age.
    protected int age;

    protected static final int ANIMAL_FOOD_VALUE = 9;

    protected static final Random RANDOM = new Random();


    public void initialize(boolean randomAge, Field field, Location location) {
        this.field = field;
        this.age = 0;
        setAlive(true);
        setLocation(location);
        if(randomAge){
            age = RANDOM.nextInt(getMaxAge());
        }
    }

    /**
     * Check whether the animal is alive or not.
     *
     * @return true if the animal is still alive.
     */

    public boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the fox is no longer alive. It is removed from the field.
     */

    protected void setDead() {
        setAlive(false);
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }


    protected abstract Location moveToNewLocation();

    public void act(List<Animal> newAnimals) {
        incrementAge();
        if (isAlive()) {
            giveBirth(newAnimals);
            // Move towards a source of food if found.
            Location newLocation = moveToNewLocation();
            if (newLocation != null) {
                // No food found - try to move to a free location.
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Return the fox's location.
     *
     * @return The fox's location.
     */
    public Location getLocation() { return location; }

    /**
     * Place the rabbit at the new location in the given field.
     *
     * @param newLocation The rabbit's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    public abstract int getMaxAge();

    /**
     * Increase the age. This could result in the rabbit's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    private boolean canBreed() { return age >= getBreedingAge(); }

    protected abstract double getBreedingProbability();

    protected abstract int getMaxLitterSize();

    /**
     * A rabbit can breed if it has reached the breeding age.
     *
     * @return true if the rabbit can breed, false otherwise.
     */


    protected abstract int getBreedingAge();

    protected  Animal createYoung(boolean randomAge, Field field, Location location){
        return AnimalFactory.createAnimal(getClass(),field,location);
    }

    /**
     * Check whether or not this rabbit is to give birth at this step. New
     * births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born rabbits.
     */
    protected void giveBirth(List<Animal> newAnimals) {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Animal young = createYoung(false, field, loc);
            newAnimals.add(young);
        }
    }
    public abstract int getFoodLevel();
}

