package nandor.ledenyi.robodog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Skill {

    @JsonIgnore
    private long id;
    private long dogId;
    private long trickId;
    private int level;

    public Skill(long dogId, long trickId, int level) {
        this.dogId = dogId;
        this.trickId = trickId;
        this.level = level;
    }
}
