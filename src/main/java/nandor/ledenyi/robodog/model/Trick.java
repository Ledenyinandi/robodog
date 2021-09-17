package nandor.ledenyi.robodog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Trick {

    @JsonIgnore
    private long id;
    private String name;

    public Trick(String name) {
        this.name = name;
    }
}