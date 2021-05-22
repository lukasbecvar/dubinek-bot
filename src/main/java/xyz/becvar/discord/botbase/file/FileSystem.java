package xyz.becvar.discord.botbase.file;

import xyz.becvar.discord.botbase.utils.TimeUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * The File manager class
 * All files functions: create, write, etc
*/

public class FileSystem {

    //Create file by name in root directory
    public static void createFile(String name) {
        File f = new File(name);
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Save msg to msg log file
    public static void saveMessageLog(String message) {
        createFile("msg.log");
        try {
            if (Files.notExists(Paths.get("msg.log"))) {
                File f = new File(String.valueOf(Paths.get("msg.log")));
                f.createNewFile();
            }
            try(PrintWriter output = new PrintWriter(new FileWriter("msg.log",true))) {
                output.printf("%s\r\n", "[" + TimeUtils.getTime() + "]: " + message);
            }
            catch (Exception e) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Save System log to System log file
    public static void saveSystemLog(String message) {
        createFile("system.log");
        try {
            if (Files.notExists(Paths.get("system.log"))) {
                File f = new File(String.valueOf(Paths.get("system.log")));
                f.createNewFile();
            }
            try(PrintWriter output = new PrintWriter(new FileWriter("system.log",true))) {
                output.printf("%s\r\n", "[" + TimeUtils.getTime() + "]: " + message);
            }
            catch (Exception e) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
