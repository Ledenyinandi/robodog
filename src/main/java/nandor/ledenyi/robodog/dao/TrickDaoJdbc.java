package nandor.ledenyi.robodog.dao;

import nandor.ledenyi.robodog.dao.mapper.TrickMapper;
import nandor.ledenyi.robodog.model.Trick;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TrickDaoJdbc implements TrickDao {

    private JdbcTemplate jdbcTemplate;

    public TrickDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addTrick(Trick trick) {
        String sql = "insert into trick(name) values ?";
        jdbcTemplate.update(sql, trick.getName());
    }

    @Override
    public List<Trick> listTricks() {
        String sql = "select * from trick";
        return jdbcTemplate.query(sql, new TrickMapper());
    }

    @Override
    public Trick getTrick(long id) {
        String sql = "select * from trick where id = ?";
        Trick trick = null;
        try {
            trick = jdbcTemplate.queryForObject(sql, new TrickMapper(), id);
        } catch (DataAccessException e) {
            System.out.println("Trick not found!");
        }
        return trick;
    }

    @Override
    public void updateTrick(Trick trick, long id) {
        String sql = "update trick set name = ? where id = ?";
        jdbcTemplate.update(sql, trick.getName(), id);
    }

    @Override
    public void deleteTrick(long id) {
        String sql = "delete from skill where trick_id = ?;" +
                "delete from trick where id = ?";
        jdbcTemplate.update(sql, id, id);
    }

    @Override
    public long addTrickAndReturnId(Trick trick) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into trick(name) values ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, trick.getName());
            return ps;
        }, keyHolder);

        return  keyHolder.getKey().longValue();
    }
}
