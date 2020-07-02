package eid.bing.mysql.bean;

import lombok.Data;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @Description:
 */

@Data
public class CommitDataStruct {
    private int ret;

    private Xid xid;

    private XAResource xaResource;

    private XAConnection con;

    private Connection connection;

    private Statement statement;

    public CommitDataStruct(){

    }
}