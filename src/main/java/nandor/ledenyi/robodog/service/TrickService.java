package nandor.ledenyi.robodog.service;

import nandor.ledenyi.robodog.dao.TrickDao;
import nandor.ledenyi.robodog.model.Trick;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrickService {

    private TrickDao trickDao;

    public TrickService(TrickDao trickDao) {
        this.trickDao = trickDao;
    }

    public void addTrick(Trick trick) {
        trickDao.addTrick(trick);
    }

    public List<Trick> listTricks() {
        return trickDao.listTricks();
    }

    public Trick getTrick(long id) {
        return trickDao.getTrick(id);
    }

    public void updateTrick(Trick trick, long id) {
        trickDao.updateTrick(trick, id);
    }

    public void deleteTrick(long id) {
        trickDao.deleteTrick(id);
    }

    public long addTrickAndReturnId(Trick trick) {
        return trickDao.addTrickAndReturnId(trick);
    }
}
