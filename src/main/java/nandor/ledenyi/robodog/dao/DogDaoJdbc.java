package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.dao.mapper.DogMapper;
import nandor.ledenyi.robodog.model.Dog;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DogDaoJdbc implements DogDao {

    private JdbcTemplate jdbcTemplate;

    public DogDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addDog(Dog dog) {
        String sql = "insert into dog(breed, name, age) values (?, ?, ?)";
        jdbcTemplate.update(sql, dog.getBreed().toString(), dog.getName(), dog.getAge());
    }

    @Override
    public List<Dog> listDogs() {
        String sql = "select * from dog";
        return jdbcTemplate.query(sql, new DogMapper());
    }

    @Override
    public Dog getDog(long id) {
        String sql = "select * from dog where id = ?";
        Dog dog = null;
        try {
            dog = jdbcTemplate.queryForObject(sql, new DogMapper(), id);
        } catch (DataAccessException e) {
            System.out.println("Dog not found");
        }
        return dog;
    }

    @Override
    public void updateDog(Dog dog, long id) {
        String sql = "update dog set breed = ?, name = ?, age = ? where id = ?";
        jdbcTemplate.update(sql, dog.getBreed().toString(), dog.getName(), dog.getAge(), id);
    }

    @Override
    public void deleteDog(long id) {
        String sql = "delete from skill where dog_id = ?;" +
                "delete from dog where id = ?";
        jdbcTemplate.update(sql, id, id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into dog (breed, name, age) values (?,?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dog.getBreed().toString());
            ps.setString(2, dog.getName());
            ps.setInt(3, dog.getAge());
            return ps;
        }, keyHolder);

        return  keyHolder.getKey().longValue();
    }
}