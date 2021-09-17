package nandor.ledenyi.robodog.controller;

import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Skill;
import nandor.ledenyi.robodog.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/listSkills")
    public List<Skill> listSkills() {
        return skillService.listSkills();
    }

    @PostMapping("/addSkill")
    public void addSkill(@RequestBody Skill skill) {
        skillService.addSkill(skill);
    }

    @GetMapping("/get/{skill_id}")
    public Skill getSkill(@PathVariable("skill_id") long id) {
        return skillService.getSkill(id);
    }

    @PutMapping("/update/{skill_id}")
    public void updateSkill(@PathVariable("skill_id") long id, @RequestBody Skill skill) {
        skillService.updateSkill(skill, id);
    }

    @DeleteMapping("/delete/{skill_id}")
    public void deleteSkill(@PathVariable("skill_id") long id) {
        skillService.deleteSkill(id);
    }

    @PostMapping("/addSkillAndReturnId")
    public long addSkillAndReturnId(@RequestBody Skill skill) {
        return skillService.addSkillAndReturnId(skill);
    }

    @GetMapping("/dogsByTrickId/{trick_id}")
    public List<Dog> listDogsByTrickId(@PathVariable("trick_id") long trickId) {
        return skillService.listDogsByTrickId(trickId);
    }

    //    @GetMapping("/getOptional/{dog_id}/{trick_id}")
    @GetMapping("/getOptional/dog/{dog_id}/trick/{trick_id}")
    public Optional<Skill> getOptionalSkill(@PathVariable("dog_id") long dogId, @PathVariable("trick_id") long trickId) {
        return skillService.getOptionalSkill(dogId, trickId);
    }

    @GetMapping("/name/{trick_name}/dog/{dog_id}")
    public Skill getSkillByDogIdAndTrickName(@PathVariable("dog_id") long dogId, @PathVariable("trick_name") String trickName) {
        return skillService.getSkillByDogIdAndTrickName(dogId, trickName);
    }

    @PutMapping("/name/{trick_name}/dog/{dog_id}")
    public void updateSkillByDogIdAndTrickName(@PathVariable("dog_id") long dogId, @PathVariable("trick_name") String trickName, @RequestBody Skill skill) {
        skillService.updateSkillByDogIdAndTrickName(skill, dogId, trickName);
    }
}
