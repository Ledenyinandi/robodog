package nandor.ledenyi.robodog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pedigree {

    @JsonIgnore
    private long id;
    private long momId;
    private long dadId;
    private long puppyId;

    public Pedigree(long momId, long dadId, long puppyId) {
        this.momId = momId;
        this.dadId = dadId;
        this.puppyId = puppyId;
    }
}
