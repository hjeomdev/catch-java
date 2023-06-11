package exceptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ResourceProvider implements AutoCloseable{
    private String filename;
    private Properties properties;

    public ResourceProvider(String filename) {
        this.filename = filename;
        properties = new Properties();
        load();
    }

    private void load() {
        try(FileInputStream fis = new FileInputStream("src/exceptions/" + filename)) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("파일 로드 실패: " + e.getMessage());
        }
    }

    public String read(String key) {
        return properties.getProperty(key);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public void write(String key, String value) {
        try {
            if(key.length() != 4 || !isNumeric(key)) {
                throw new KeyIsNotNumberException("키값 " + key + "이 올바르지 않습니다.");
            }
        } catch (KeyIsNotNumberException e) {
            System.out.println("데이터 형식 오류: " + e.getMessage());
            return;
        }

        properties.setProperty(key, value);

        try(FileOutputStream fos = new FileOutputStream(filename)) {
            properties.store(fos, null);
        } catch(IOException e) {
            System.out.println("새로운 데이터 저장 실패: " + e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println(filename + "is Closed.");
    }
}
