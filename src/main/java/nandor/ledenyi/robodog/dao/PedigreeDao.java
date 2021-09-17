package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Pedigree;
import java.util.List;

public interface PedigreeDao {

    void addPedigree(Pedigree pedigree);

    List<Pedigree> listPedigrees();

    Pedigree getPedigree(long id);

    void updatePedigree(Pedigree pedigree, long id);

    void deletePedigree(long id);

    long addPedigreeAndReturnId(Pedigree pedigree);

    Pedigree getAllFamilyMembers(long dogId);

    void addPedigreeForDog(Pedigree pedigree);

    Dog getPuppy(PuppyDTO puppyDTO);

    Dog getMom(long id);

    Dog getDad(long id);

    List<Dog> getSiblings(long id);
}
