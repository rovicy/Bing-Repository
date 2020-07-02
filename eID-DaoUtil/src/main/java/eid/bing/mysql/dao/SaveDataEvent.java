package eid.bing.mysql.dao;

import java.sql.ResultSet;

/**
 * @Description:
 */

public interface SaveDataEvent {

    <T> T translate(ResultSet rs);
}
