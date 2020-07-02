package eid.bing.mysql.bean;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 */

public class SourceAndSql {
    @Getter
    private String sourceName;
    @Getter
    private List<String> sqlList;

    public SourceAndSql(String sourceName){
        this.sourceName = sourceName;
        this.sqlList = new ArrayList<>();
    }
}