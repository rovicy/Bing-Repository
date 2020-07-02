package eid.bing.mysql;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;
import eid.bing.mysql.init.XaInitDataSource;
import eid.bing.mysql.bean.CommitDataStruct;
import eid.bing.mysql.bean.SourceAndSql;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @Description:  deal the mysql with xaDataSource
 */

public class EidMysqlXaService {
    private static Logger logger = Logger.getLogger(EidMysqlXaService.class.getName());

    public EidMysqlXaService() {

    }

    public DruidXADataSource getDataSource(String dbName) {

        return XaInitDataSource.getOneXaDataSource(dbName);
    }

    public boolean executeSql(List<SourceAndSql> sourceAndSqlList) {
        if (sourceAndSqlList == null || sourceAndSqlList.size() == 0) {
            logger.info("The parameter of the executeSql function can't be null or empty..");
            return false;
        }
        boolean isSuccess = false;
        List<CommitDataStruct> result = new ArrayList<>();
        EidMysqlXaService eidMysqlXaService = new EidMysqlXaService();
        try {
            for (SourceAndSql sourceAndSql : sourceAndSqlList) {
                DruidXADataSource ds = eidMysqlXaService.getDataSource(sourceAndSql.getSourceName());
                if (ds == null) {
                    logger.info("The dataSource of " + sourceAndSql.getSourceName() + " doesn't exist..");
                    return false;
                }
                for (String sql : sourceAndSql.getSqlList()) {

                    XAConnection con = ds.getXAConnection();
                    XAResource xaResource = con.getXAResource();
                    Connection connection = con.getConnection();
                    Statement st = connection.createStatement();

                    Xid xid = new MysqlXid(UUID.randomUUID().toString().getBytes(), UUID.randomUUID().toString().getBytes(), 100);
                    xaResource.start(xid, XAResource.TMNOFLAGS);
                    st.executeUpdate(sql);
                    xaResource.end(xid, XAResource.TMSUCCESS);

                    int ret = xaResource.prepare(xid);

                    CommitDataStruct dat = new CommitDataStruct();
                    dat.setRet(ret);
                    dat.setXid(xid);
                    dat.setXaResource(xaResource);
                    dat.setCon(con);
                    dat.setConnection(connection);
                    dat.setStatement(st);
                    result.add(dat);

                }
            }
            if (isAllPrepared(result)) {
                for (CommitDataStruct dat : result) {
                    dat.getXaResource().commit(dat.getXid(), false);
                }
            }
            isSuccess = true;

        } catch (Exception e) {
            System.out.println("Something wrong with...");
            e.printStackTrace();
        } finally {
            closeSource(result);
        }
        return isSuccess;
    }

    private boolean isAllPrepared(List<CommitDataStruct> dataStructList) {
        if (dataStructList == null || dataStructList.size() == 0) {
            return false;
        }
        for (CommitDataStruct dat : dataStructList) {
            if (XAResource.XA_OK != dat.getRet()) {
                return false;
            }
        }
        return true;
    }

    private void closeSource(List<CommitDataStruct> dataStructList) {
        if (dataStructList != null) {
            for (CommitDataStruct dat : dataStructList) {
                close(dat);
            }
        }
    }

    private void close(CommitDataStruct commitDataStruct) {
        try {
            if (commitDataStruct.getStatement() != null) {
                commitDataStruct.getStatement().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (commitDataStruct.getConnection() != null) {
                commitDataStruct.getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (commitDataStruct.getCon() != null) {
                commitDataStruct.getCon().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

