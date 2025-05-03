package service;

import java.io.File;
public class FileManager {
    //папки
    public File[] allFolders(String path) {
        File directory = new File(path);
        return directory.listFiles(File::isDirectory);
    }
    //файлы
    public File[] allFiles(String path) {
        File directory = new File(path);
        return directory.listFiles(File::isFile);
    }
}