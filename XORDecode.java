import java.util.Scanner;
import static java.lang.System.out;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.io.FileWriter;


class XORDecode {

    public static void main(String[] args) throws IOException{
        
        File file = new File("decryptedoutput");
        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] xorOutput = new byte[fileContents.length];

        byte[] key = {(byte)0x73, (byte)0x8B, (byte)0x55, (byte)0x44};

        for(int i = 0; i < fileContents.length; i++) {
            xorOutput[i] = (byte)((int)fileContents[i] ^ (int)key[i % key.length]);
        }

        FileOutputStream stream = new FileOutputStream("plaintextOutput");
        stream.write(xorOutput);


    }
}