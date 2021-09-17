package nandor.ledenyi.robodog.controller;

import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {

    @Autowired
    DogService dogService;

    @GetMapping("/listDogs")
    public List<Dog> listDogs() {
        return dogService.listDogs();
    }

    @GetMapping("/randomGet")
    public void createRandomDog() {
        dogService.createRandomDog();
    }

    @PostMapping("/addDog")
    public void addDog(@RequestBody Dog dog) {
        dogService.addDog(dog);
    }

    @GetMapping("/get/{dog_id}")
    public Dog getDog(@PathVariable("dog_id") long id) {
        return dogService.getDog(id);
    }

    @PutMapping("/update/{dog_id}")
    public void updateDog(@PathVariable("dog_id") long id, @RequestBody Dog dog) {
        dogService.updateDog(dog, id);
    }

    @DeleteMapping("/delete/{dog_id}")
    public void deleteDog(@PathVariable("dog_id") long id) {
        dogService.deleteDog(id);
    }

    @PostMapping("/addDogAndReturnId")
    public long addDogAndReturnId(@RequestBody Dog dog) {
        return dogService.addDogAndReturnId(dog);
    }
}
