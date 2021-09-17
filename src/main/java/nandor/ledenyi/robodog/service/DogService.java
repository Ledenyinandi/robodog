package nandor.ledenyi.robodog.service;

import nandor.ledenyi.robodog.dao.DogDao;
import nandor.ledenyi.robodog.model.Dog;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DogService {

    private DogDao dogDao;
    private DogCreator dogCreator;

    public DogService(DogDao dogDao, DogCreator dogCreator) {
        this.dogDao = dogDao;
        this.dogCreator = dogCreator;
    }

    public void createRandomDog() {
        addDog(dogCreator.createRandomDog());
    }

    public void addDog(Dog dog) {
        dogDao.addDog(dog);
    }

    public List<Dog> listDogs() {
        return dogDao.listDogs();
    }

    public Dog getDog(long id) {
        return dogDao.getDog(id);
    }

    public void updateDog(Dog dog, long id) {
        dogDao.updateDog(dog, id);
    }

    public void deleteDog(long id) {
        dogDao.deleteDog(id);
    }

    public long addDogAndReturnId(Dog dog) {
        return dogDao.addDogAndReturnId(dog);
    }
}
