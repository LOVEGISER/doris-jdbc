package com.doris.jdbc;

import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import static com.doris.jdbc.Constant.JDBC_DORIS_PREFIX;

public class DorisDriver  implements Driver {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DorisDriver.class);


    private static boolean registered;
    private static final Driver INSTANCE = new DorisDriver();
    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (!acceptsURL(url)) {
            return null;
        }

        logger.info("create connect by url: {}",url);
        logger.info("create connect by properties: {}",info);
        //url解析
        try {
            return new DorisConnection(url,info);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(JDBC_DORIS_PREFIX);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
    public static synchronized Driver load() {
        if (!registered) {
            registered = true;
            try {
                DriverManager.registerDriver(INSTANCE);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return INSTANCE;
    }

    static {
        load();
    }

}
