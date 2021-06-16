package io.muic.ooc.fab;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {

    private static Map<AnimalType, Class> animalClassMap = new HashMap<>() {{
        AnimalType[] animalTypes = AnimalType.values();
        for (int i = 0; i < animalTypes.length; i++) {
            put(animalTypes[i], animalTypes[i].getAnimalClass());
        }
    }};


    public static Animal createAnimal(AnimalType animalType, Field field, Location location) {
        Class animalClass = animalClassMap.get(animalType);
        return createAnimal(animalClass,field,location);
    }

    public static Animal createAnimal(Class animalClass, Field field, Location location) {
        if (animalClass != null) {
            try {
                Animal animal = (Animal) animalClass.getDeclaredConstructor().newInstance();
                animal.initialize(true, field, location);
                return animal;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Unknown AnimalType");
    }
}


