package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.dao.mapper.DogMapper;
import nandor.ledenyi.robodog.dao.mapper.PedigreeMapper;
import nandor.ledenyi.robodog.model.Breed;
import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Pedigree;
import nandor.ledenyi.robodog.model.dto.PuppyDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PedigreeDaoJdbc implements PedigreeDao {

    private JdbcTemplate jdbcTemplate;
    private DogDao dogDao;

    public PedigreeDaoJdbc(JdbcTemplate jdbcTemplate, DogDao dogDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.dogDao = dogDao;
    }

    @Override
    public void addPedigree(Pedigree pedigree) {
        String sql = "insert into pedigree(mom_id, dad_id, puppy_id) values (?, ?, ?)";
        jdbcTemplate.update(sql, pedigree.getMomId(), pedigree.getDadId(), pedigree.getPuppyId());
    }

    @Override
    public List<Pedigree> listPedigrees() {
        String sql = "select * from pedigree";
        return jdbcTemplate.query(sql, new PedigreeMapper());
    }

    @Override
    public Pedigree getPedigree(long id) {
        String sql = "select * from pedigree where id = ?";
        Pedigree pedigree = null;
        try {
            pedigree = jdbcTemplate.queryForObject(sql, new PedigreeMapper(), id);
        } catch (DataAccessException e) {
            System.out.println("Pedigree not found!");
        }
        return pedigree;
    }

    @Override
    public void updatePedigree(Pedigree pedigree, long id) {
        String sql = "update pedigree set mom_id = ?, dad_id = ?, puppy_id = ? where id = ?";
        jdbcTemplate.update(sql, pedigree.getMomId(), pedigree.getDadId(), pedigree.getPuppyId(), id);
    }

    @Override
    public void deletePedigree(long id) {
        String sql = "delete from pedigree where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public long addPedigreeAndReturnId(Pedigree pedigree) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into pedigree(mom_id, dad_id, puppy_id) values (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, pedigree.getMomId());
            ps.setLong(2, pedigree.getDadId());
            ps.setLong(3, pedigree.getPuppyId());
            return ps;
        }, keyHolder);

        return  keyHolder.getKey().longValue();
    }

    @Override
    public Pedigree getAllFamilyMembers(long dogId) {
        String sql = "select * from pedigree where mom_id = ? or dad_id = ? or puppy_id = ?";
        Pedigree pedigree = null;
        try {
            pedigree = jdbcTemplate.queryForObject(sql, new PedigreeMapper(), dogId, dogId, dogId);
        } catch (DataAccessException e) {
            System.out.println("Pedigree not found!");
        }
        return pedigree;
    }

    @Override
    public void addPedigreeForDog(Pedigree pedigree) {
        String sql = "insert into pedigree(mom_id, dad_id, puppy_id) values (?, ?, ?)";
        jdbcTemplate.update(sql, pedigree.getMomId(), pedigree.getDadId(), pedigree.getPuppyId());
    }

    @Override
    public Dog getPuppy(PuppyDto puppyDTO) {
        Dog dog = new Dog(breedForPuppy(puppyDTO.getMomId(), puppyDTO.getMomId()), puppyDTO.getName(), 0);
        dogDao.addDog(dog);
        return dog;
    }

    public Breed breedForPuppy(long momId, long dadId) {
        Breed puppyBreed;
        if (Math.random() > 0.5) {
            puppyBreed = dogDao.getDog(momId).getBreed();
        } else {
            puppyBreed = dogDao.getDog(dadId).getBreed();
        }
        return puppyBreed;
    }

    @Override
    public Dog getMom(long id) {
        Pedigree pedigree = getPedigree(id);
        String sql = "select * from dog where id = ?";
        return jdbcTemplate.queryForObject(sql, new DogMapper(), pedigree.getMomId());
    }

    @Override
    public Dog getDad(long id) {
        Pedigree pedigree = getPedigree(id);
        String sql = "select * from dog where id = ?";
        return jdbcTemplate.queryForObject(sql, new DogMapper(), pedigree.getDadId());
    }

    @Override
    public List<Dog> getSiblings(long id) {
        Pedigree pedigree = getPedigree(id);
        String sql = "select * from dog join pedigree on dog.id = pedigree.puppy_id where mom_id = ? or dad_id = ?";
        return jdbcTemplate.query(sql, new DogMapper(), pedigree.getMomId(), pedigree.getDadId());
    }
}
