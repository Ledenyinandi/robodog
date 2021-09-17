package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.model.Dog;
import java.util.List;

public interface DogDao {

    void addDog(Dog dog);

    List<Dog> listDogs();

    Dog getDog(long id);

    void updateDog(Dog dog, long id);

    void deleteDog(long id);

    long addDogAndReturnId(Dog dog);
}

