package nandor.ledenyi.robodog.controller;

import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Pedigree;
import nandor.ledenyi.robodog.model.dto.PuppyDto;
import nandor.ledenyi.robodog.service.PedigreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedigree")
public class PedigreeController {

    @Autowired
    PedigreeService pedigreeService;

    @GetMapping("/listPedigrees")
    public List<Pedigree> listPedigrees() {
        return pedigreeService.listPedigrees();
    }

    @PostMapping("/addPedigree")
    public void addPedigree(@RequestBody Pedigree pedigree) {
        pedigreeService.addPedigree(pedigree);
    }

    @GetMapping("/get/{pedigree_id}")
    public Pedigree getPedigree(@PathVariable("pedigree_id") long id) {
        return pedigreeService.getPedigree(id);
    }

    @PutMapping("/update/{pedigree_id}")
    public void updatePedigree(@PathVariable("pedigree_id") long id, @RequestBody Pedigree pedigree) {
        pedigreeService.updatePedigree(pedigree, id);
    }

    @DeleteMapping("/delete/{pedigree_id}")
    public void deletePedigree(@PathVariable("pedigree_id") long id) {
        pedigreeService.deletePedigree(id);
    }

    @PostMapping("/addPedigreeAndReturnId")
    public long addPedigreeAndReturnId(@RequestBody Pedigree pedigree) {
        return pedigreeService.addPedigreeAndReturnId(pedigree);
    }

    @GetMapping("/dog/{dog_id}/pedigree")
    public Pedigree getAllFamilyMembers(@PathVariable("dog_id") long dogId) {
        return pedigreeService.getAllFamilyMembers(dogId);
    }

    @PostMapping("/dog/{dog_id}/pedigree")
    public void addPedigreeForDog(@RequestBody Pedigree pedigree) {
        pedigreeService.addPedigreeForDog(pedigree);
    }

    @GetMapping("/dog/puppy")
    public Dog getPuppy(@RequestBody PuppyDto puppyDto) {
        return pedigreeService.getPuppy(puppyDto);
    }

    @GetMapping("/dog/{dog_id}/pedigree/mom")
    public Dog getMom(@PathVariable("dog_id") long id) {
        return pedigreeService.getMom(id);
    }

    @GetMapping("/dog/{dog_id}/pedigree/dad")
    public Dog getDad(@PathVariable("dog_id") long id) {
        return pedigreeService.getDad(id);
    }

    @GetMapping("/dog/{dog_id}/pedigree/sibling")
    public List<Dog> getSiblings(@PathVariable("dog_id") long id) {
        return pedigreeService.getSiblings(id);
    }
}
