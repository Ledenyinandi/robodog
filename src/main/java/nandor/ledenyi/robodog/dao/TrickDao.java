package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.model.Trick;
import java.util.List;

public interface TrickDao {

    void addTrick(Trick trick);

    List<Trick> listTricks();

    Trick getTrick(long id);

    void updateTrick(Trick trick, long id);

    void deleteTrick(long id);

    long addTrickAndReturnId(Trick trick);
}
