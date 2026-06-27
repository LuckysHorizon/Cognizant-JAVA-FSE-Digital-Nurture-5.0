package mockito;

public interface FileStorage {
    boolean saveFile(String fileName, String content);
    String readFile(String fileName);
    boolean deleteFile(String fileName);
    boolean fileExists(String fileName);
}
