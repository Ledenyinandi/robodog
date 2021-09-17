package nandor.ledenyi.robodog.service;

import nandor.ledenyi.robodog.dao.SkillDao;
import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Skill;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    private SkillDao skillDao;

    public SkillService(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    public void addSkill(Skill skill) {
        skillDao.addSkill(skill);
    }

    public List<Skill> listSkills() {
        return skillDao.listSkills();
    }

    public Skill getSkill(long id) {
        return skillDao.getSkill(id);
    }

    public void updateSkill(Skill skill, long id) {
        skillDao.updateSkill(skill, id);
    }

    public void deleteSkill(long id) {
        skillDao.deleteSkill(id);
    }

    public long addSkillAndReturnId(Skill skill) {
        return skillDao.addSkillAndReturnId(skill);
    }

    public List<Dog> listDogsByTrickId(long trickId) {
        return skillDao.listDogsByTrickId(trickId);
    }

    public Optional<Skill> getOptionalSkill(long dogId, long trickId) {
        return skillDao.getOptionalSkill(dogId, trickId);
    }

    public Skill getSkillByDogIdAndTrickName(long dogId, String trickName) {
        return skillDao.getSkillByDogIdAndTrickName(dogId, trickName);
    }

    public void updateSkillByDogIdAndTrickName(Skill skill, long dogId, String trickName) {
        skillDao.updateSkillByDogIdAndTrickName(skill, dogId,trickName);
    }
}
