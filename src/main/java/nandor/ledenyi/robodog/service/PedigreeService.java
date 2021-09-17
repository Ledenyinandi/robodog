package nandor.ledenyi.robodog.service;

import nandor.ledenyi.robodog.dao.PedigreeDao;
import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Pedigree;
import nandor.ledenyi.robodog.model.dto.PuppyDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedigreeService {

    private PedigreeDao pedigreeDao;

    public PedigreeService(PedigreeDao pedigreeDao) {
        this.pedigreeDao = pedigreeDao;
    }

    public void addPedigree(Pedigree pedigree) {
        pedigreeDao.addPedigree(pedigree);
    }

    public List<Pedigree> listPedigrees() {
        return pedigreeDao.listPedigrees();
    }

    public Pedigree getPedigree(long id) {
        return pedigreeDao.getPedigree(id);
    }

    public void updatePedigree(Pedigree pedigree, long id) {
        pedigreeDao.updatePedigree(pedigree, id);
    }

    public void deletePedigree(long id) {
        pedigreeDao.deletePedigree(id);
    }

    public long addPedigreeAndReturnId(Pedigree pedigree) {
        return pedigreeDao.addPedigreeAndReturnId(pedigree);
    }

    public Pedigree getAllFamilyMembers(long dogId) {
        return pedigreeDao.getAllFamilyMembers(dogId);
    }

    public void addPedigreeForDog(Pedigree pedigree) {
        pedigreeDao.addPedigreeForDog(pedigree);
    }

    public Dog getPuppy(PuppyDto puppyDto) {
        return pedigreeDao.getPuppy(puppyDto);
    }

    public Dog getMom(long id) {
        return pedigreeDao.getMom(id);
    }

    public Dog getDad(long id) {
        return pedigreeDao.getDad(id);
    }

    public List<Dog> getSiblings(long id) {
        return pedigreeDao.getSiblings(id);
    }
}

