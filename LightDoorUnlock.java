import mraa.Aio;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.net.*;

//UserID is Group4
//Password is 1101

public class LightDoorUnlock {

  static {
    try {
      System.loadLibrary("mraajava");
    } catch (UnsatisfiedLinkError e) {
      System.err.println(
          "Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" +
          e);
      System.exit(1);
    }
  }

  public static void main(String[] args)
  {
    System.out.println("Recording password now.");
    int[] password = new int[4];
    for(int i = 0; i < 4; i++) {
      System.out.println(4-i);
      Aio light = new Aio(3);
      float value = light.readFloat();
      //System.out.println("The reading from the phototransistor is " + value);
      int passwordValue = Math.round(value);
      password[i] = passwordValue;
      try {
        TimeUnit.SECONDS.sleep(1);
      }catch (InterruptedException e) {
      }

    }
      System.out.println("The password is " + password[0]+password[1]+password[2]+password[3]);
      try {
        Socket socket = new Socket("r01.cs.ucla.edu",16000);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("ID = Group4 Password = " + password[0]+password[1]+password[2]+password[3]);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(bufferedReader.readLine());
      } catch (Exception e){
        System.exit(1);
      }
  }
}
