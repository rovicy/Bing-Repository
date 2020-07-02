package eid.bing.mysql.service;

import com.alibaba.druid.pool.DruidDataSource;
import eid.bing.mysql.init.InitDataSource;
import eid.bing.mysql.dao.SaveDataEvent;
import eid.bing.mysql.dao.DealDataEvent;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Description:
 */

public class DataSourceService {
    private DruidDataSource dataSource;

    private static Logger log = Logger.getLogger("DataSourceService");

    public DataSourceService(String dbName) {

        dataSource = InitDataSource.getOneDataSource(dbName);
    }

    public DruidDataSource getDataSource() {
        return dataSource;
    }

    public void selectForDeal(String sql, DealDataEvent event){
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                event.doSomething(rs);
            }
        }catch (SQLException e){
            log.warning("Something wrong with selectWithDeal...");
        }
    }


    public <T> List<T> selectForSave(String sql, SaveDataEvent ss) {
        List<T> list = new ArrayList<T>();
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                T t = ss.translate(rs);
                if (t != null) {
                    list.add(t);
                }
            }
            return list;
        } catch (Exception e) {
            log.warning("Something wrong with select sql: " + sql);
            return null;
        }
    }

    synchronized public boolean update(String sql) {
        boolean isUpdated = false;
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            st.executeUpdate();
            con.commit();
            isUpdated = true;
        } catch (Exception e) {
            log.warning("Something wrong with update sql: " + sql);
        }
        return isUpdated;
    }

    synchronized public boolean batchInsert(String sqlPrefix, List<String> values) {

        if(values == null || values.size() == 0){
            return false;
        }
        boolean isSuccess = false;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("")) {
            con.setAutoCommit(false);
            String sqlSuffix = StringUtils.strip(values.toString(), "[]");
            pst.addBatch(sqlPrefix + sqlSuffix);
            pst.executeBatch();
            con.commit();
            isSuccess = true;
        } catch (Exception e) {
            log.warning("Something wrong with batch insert sq: " + sqlPrefix);
            log.warning(e.getMessage());
        }
        return isSuccess;
    }

    synchronized public boolean insertOne(String sql){
        boolean isSuccess = false;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pst.execute();
            con.commit();
            isSuccess = true;
        } catch (Exception e) {
            log.warning("Something wrong with insert one data: " + sql);
            log.warning(e.getMessage());
        }
        return isSuccess;
    }

    synchronized public boolean insertTest(String sql){
        boolean isSuccess = false;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pst.execute();
            con.commit();
//            pst.execute();
            isSuccess = true;
        } catch (Exception e) {
            log.warning("Something wrong with insert one data: " + sql);
            log.warning(e.getMessage());
        }
        return isSuccess;
    }

}
