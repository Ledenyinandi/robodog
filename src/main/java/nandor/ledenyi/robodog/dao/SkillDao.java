package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillDao {

    void addSkill(Skill skill);

    List<Skill> listSkills();

    Skill getSkill(long id);

    void updateSkill(Skill skill, long id);

    void deleteSkill(long id);

    long addSkillAndReturnId(Skill skill);

    List<Dog> listDogsByTrickId(long trickId);

    Optional<Skill> getOptionalSkill(long dogId, long trickId);

    Skill getSkillByDogIdAndTrickName(long dogId, String trickName);

    void updateSkillByDogIdAndTrickName(Skill skill, long dogId, String trickName);
}
