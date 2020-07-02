package eid.bing.mysql.init;


import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.alibaba.fastjson.JSONObject;
import eid.bing.mysql.bean.SourceStruct;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 */

public class XaInitDataSource {
    private static ConcurrentHashMap<String, DruidXADataSource> sourcePool = new ConcurrentHashMap<>();

    static {
        InputStream in = XaInitDataSource.class.getClassLoader().getResourceAsStream("XaDataConfig.json");
        StringBuilder sb = new StringBuilder();
        List<SourceStruct> list = new ArrayList<SourceStruct>();
        BufferedReader reader = null;
        try {
            assert in != null;
            reader = new BufferedReader(new InputStreamReader(in));
            String data;
            while ((data = reader.readLine()) != null) {
                sb.append(data);
            }
            String jsonString = sb.toString();

            list = JSONObject.parseArray(jsonString, SourceStruct.class);
            reader.close();

            for (SourceStruct struct : list) {
                DruidXADataSource dataSource = new DruidXADataSource();
                dataSource.setUrl(struct.getUrl());
                dataSource.setDriverClassName(struct.getDriveClassName());
                dataSource.setUsername(struct.getUserName());
                dataSource.setPassword(struct.getPassWord());
                dataSource.setInitialSize(struct.getInitialSize());
                dataSource.setMinIdle(struct.getMinIdle());
                dataSource.setMaxActive(struct.getMaxActive());
                dataSource.setRemoveAbandoned(struct.getRemoveAbandoned());
                dataSource.setRemoveAbandonedTimeout(struct.getRemoveAbandonedTimeout());
                dataSource.setMaxWait(struct.getMaxWait());
                dataSource.setTimeBetweenEvictionRunsMillis(struct.getTimeBetweenEvictionRunsMillis());
                dataSource.setValidationQuery(struct.getValidationQuery());
                dataSource.setTestWhileIdle(struct.getTestWhileIdle());
                dataSource.setTestOnBorrow(struct.getTestOnBorrow());

                sourcePool.put(struct.getSourceName(), dataSource);
            }
        } catch (Exception e) {
            System.out.println("Something wrong with config...");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static DruidXADataSource getOneXaDataSource(String dbName) {
        if (dbName == null) {
            return null;
        }
        return sourcePool.getOrDefault(dbName, null);
    }
}

