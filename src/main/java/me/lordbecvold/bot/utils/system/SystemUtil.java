package me.lordbecvold.bot.utils.system;

public class SystemUtil {

    // Kill app with exit code 0
    public void appShutdown() {
        System.exit(0);
    }

    //Exec command and get output
    public String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
