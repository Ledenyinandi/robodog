package nandor.ledenyi.robodog.dao.mapper;

import nandor.ledenyi.robodog.model.Trick;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrickMapper implements RowMapper<Trick> {

    @Override
    public Trick mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trick trick = new Trick();
        trick.setId(rs.getLong("id"));
        trick.setName(rs.getString("name"));
        return trick;
    }
}
