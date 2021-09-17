package nandor.ledenyi.robodog.service;

import nandor.ledenyi.robodog.model.Breed;
import nandor.ledenyi.robodog.model.Dog;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class DogCreator {

    private List<String> dogNames = List.of("Frédi", "Gusztáv", "Cézár", "Bobby", "Pimpa");
    private List<Breed> breeds = List.of(Breed.values());

    public Dog createRandomDog() {
        Random rand = new Random();
        String randomName = dogNames.get(rand.nextInt(dogNames.size()));
        int randomAge = rand.nextInt(15);
        Breed randomBreed = breeds.get(rand.nextInt(breeds.size()));
        return new Dog(randomBreed, randomName, randomAge);
    }
}
