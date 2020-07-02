package eid.bing.mysql.bean;

import lombok.Data;

/**
 * @Description:
 */

@Data
public class SourceStruct {

    private String sourceName;
    private String url;
    private String driveClassName;
    private String userName;
    private String passWord;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private boolean removeAbandoned;
    private int removeAbandonedTimeout;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;

    public SourceStruct(){

    }

    public boolean getRemoveAbandoned(){
        return removeAbandoned;
    }

    public boolean getTestWhileIdle(){
        return testWhileIdle;
    }

    public boolean getTestOnBorrow(){
        return testOnBorrow;
    }

}

