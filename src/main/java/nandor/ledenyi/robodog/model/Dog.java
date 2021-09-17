package nandor.ledenyi.robodog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Dog {

    @JsonIgnore
    private long id;
    private Breed breed;
    private String name;
    private int age;

    public Dog(Breed breed, String name, int age) {
        this.breed = breed;
        this.name = name;
        this.age = age;
    }
}
