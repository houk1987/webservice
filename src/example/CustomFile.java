package example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CustomFile {

    private File file;

    public CustomFile(String path){
        this(new File(path));
    }

    public CustomFile(File file) {
        this.file = file;
    }

    public String getFileContentToString() throws UnsupportedEncodingException {
        return new String(getFileContent(),"utf-8");
    }

    public byte[] getFileContent(){
        byte[] result=null;
        try {
            result = FileUtils.readFileToByteArray(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getExtension(){
        return FilenameUtils.getExtension(this.getFileName());
    }

    public String getFileName(){
        return this.file.getName();
    }
}
