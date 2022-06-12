package me.lordbecvold.bot.utils.database;

import me.lordbecvold.bot.config.ConfigManager;
import me.lordbecvold.bot.utils.file.FileUtils;
import me.lordbecvold.bot.utils.system.TimeUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * The mysql utils
 * Functions: connect to database, get users data
 */

public class MysqlUtils {

    //Inits
    public static ConfigManager configManager = new ConfigManager();
    public static TimeUtils timeUtils = new TimeUtils();
    public static FileUtils fileUtils = new FileUtils();

    // create connect with mysql database
    public Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + configManager.getConfigValue("mysqlIP") + "/" + configManager.getConfigValue("mysqlDatabase") + "?" + "user=" + configManager.getConfigValue("mysqlUser") + "&password=" + configManager.getConfigValue("mysqlPasswword") + "&characterEncoding=UTF-8");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }

    //The methode for send mysql query
    public void sendQuery(String query) {
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isMysqlLogginEnabled() {
        if (fileUtils.checkFileExist("config.yml")) {
            if (configManager.getConfigBolValue("mysqlLogging")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //Save msg log
    public void saveMSGLog(String author, String channel, String msg) {
        // Check if mysql logging is enabled
        if (configManager.getConfigBolValue("mysqlLogging")) {
            sendQuery("INSERT INTO msg_log(author, channel, msg, date) VALUES ('" + author + "',  '" + channel + "', '" + msg + "', '" + timeUtils.getTime() + "')");
        }
    }

    //Save normal log
    public void saveNORMALLog(String prefix, String msg) {
        // Check if mysql logging is enabled
        if (configManager.getConfigBolValue("mysqlLogging")) {
            sendQuery("INSERT INTO normal_log(prefix, msg, date) VALUES ('" + prefix + "',  '" + msg + "', '" + timeUtils.getTime() + "')");
        }
    }

    //Save error log
    public void saveERRORLog(String prefix, String error) {
        // Check if mysql logging is enabled
        if (configManager.getConfigBolValue("mysqlLogging")) {
            sendQuery("INSERT INTO error_log(prefix, error, date) VALUES ('" + prefix + "',  '" + error + "', '" + timeUtils.getTime() + "')");
        }
    }
}
