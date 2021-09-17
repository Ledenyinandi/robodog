package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.dao.mapper.DogMapper;
import nandor.ledenyi.robodog.dao.mapper.SkillMapper;
import nandor.ledenyi.robodog.model.Dog;
import nandor.ledenyi.robodog.model.Skill;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class SkillDaoJdbc implements SkillDao {

    private JdbcTemplate jdbcTemplate;

    public SkillDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addSkill(Skill skill) {
        String sql = "insert into skill(dog_id, trick_id, level) values (?, ?, ?)";
        jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel());
    }

    @Override
    public List<Skill> listSkills() {
        String sql = "select * from skill";
        return jdbcTemplate.query(sql, new SkillMapper());
    }

    @Override
    public Skill getSkill(long id) {
        String sql = "select * from skill where id = ?";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, new SkillMapper(), id);
        } catch (DataAccessException e) {
            System.out.println("Skill not found!");
        }
        return skill;
    }

    @Override
    public void updateSkill(Skill skill, long id) {
        String sql = "update skill set dog_id = ?, trick_id = ?, level = ? where id = ?";
        jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel(), id);
    }

    @Override
    public void deleteSkill(long id) {
        String sql = "delete from skill where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public long addSkillAndReturnId(Skill skill) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into skill(dog_id, trick_id, level) values ((select id from dog where id = ?), ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, skill.getDogId());
            ps.setLong(2, skill.getTrickId());
            ps.setInt(3, skill.getLevel());
            return ps;
        }, keyHolder);

        return  keyHolder.getKey().longValue();
    }

    @Override
    public List<Dog> listDogsByTrickId(long trickId) {
        String sql = "select * from dog where id in (select dog_id from skill where trick_id = ?)";
        return jdbcTemplate.query(sql, new DogMapper(), trickId);
    }

    @Override
    public Optional<Skill> getOptionalSkill(long dogId, long trickId) {
        String sql = "select * from skill where dog_id = ? and trick_id = ?";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, new SkillMapper(), dogId, trickId);
        } catch (DataAccessException e) {
            System.out.println("Skill not found!");
        }
        return Optional.ofNullable(skill);
    }

    @Override
    public Skill getSkillByDogIdAndTrickName(long dogId, String trickName) {
        String sql = "select * from skill where dog_id = ? and trick_id in (select id from trick where name = ?)";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, new SkillMapper(), dogId, trickName);
        } catch (DataAccessException e) {
            System.out.println("Skill not found");
        }
        return skill;
    }

    @Override
    public void updateSkillByDogIdAndTrickName(Skill skill, long dogId, String trickName) {
        String sql = "update skill set level = ? where dog_id = ? and trick_id in (select id from trick where name = ?)";
        int level = skill.getLevel();
        if (level <= 3 && level >= 0) {
            jdbcTemplate.update(sql, ++level, dogId, trickName);
        }
    }
}
