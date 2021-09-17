package nandor.ledenyi.robodog.controller;

import nandor.ledenyi.robodog.model.Trick;
import nandor.ledenyi.robodog.service.TrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trick")
public class TrickController {

    @Autowired
    TrickService trickService;

    @GetMapping("listTricks")
    public List<Trick> listTricks() {
        return trickService.listTricks();
    }

    @PostMapping("/addTrick")
    public void addTrick(@RequestBody Trick trick) {
        trickService.addTrick(trick);
    }

    @GetMapping("/get/{trick_id}")
    public Trick getTrick(@PathVariable("trick_id") long id) {
        return trickService.getTrick(id);
    }

    @PutMapping("/update/{trick_id}")
    public void updateTrick(@PathVariable("trick_id") long id, @RequestBody Trick trick) {
        trickService.updateTrick(trick, id);
    }

    @DeleteMapping("/delete/{trick_id}")
    public void deleteTrick(@PathVariable("trick_id") long id) {
        trickService.deleteTrick(id);
    }

    @PostMapping("/addTrickAndReturnId")
    public long addTrickAndReturnId(@RequestBody Trick trick) {
        return trickService.addTrickAndReturnId(trick);
    }
}

