package gob.grsm.denuncia.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class ConvertidorUtilitario {
	
    public  String encodeFileToBase64Binary(File file){
        String encodedFile = null;
        try {
            @SuppressWarnings("resource")
			FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
            //String encodedFile = Base64.getEncoder().encodeToString(bytes);
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedFile;
    } 

}
