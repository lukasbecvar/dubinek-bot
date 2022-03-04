package me.lordbecvold.bot.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    // Create file if not exist
    public void createFile(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Check if file exist and return true or false
    public boolean checkFileExist(String fileName) {
        File f = new File(fileName);
        if(f.exists() && !f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    // Delte all files from directory
    public void purgeDirectory(String path) {

        try {
            Files.newDirectoryStream(Path.of(path)).forEach(file -> {
                try {
                    Files.delete(file);
                }
                catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete file by name
    public void deleteFile(String fileName) {

        File f = new File(fileName);

        if (f.exists()) {
            f.delete();
        }
    }
}
