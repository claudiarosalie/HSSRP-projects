import mraa.Aio;
import java.util.concurrent.TimeUnit;
public class LightProject//name of class
{
    static {//not a return type so must be static
        try {
            System.loadLibrary("mraajava");//loadLibrary build in method? will try this
        } catch (UnsatisfiedLinkError e) {//catch the exception error
            System.err.println(
                "Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" +
                    e);//error message that prints
            System.exit(1);//syntax for exiting the try catch block
        }
    }
    public static void main(String[]args)//main method
    {
        System.out.println("Recording the password now");
        int [] password = new int [4];
        for(int i=0; i<4; i++)
        {
            Aio light= new Aio(3);
            float value= light.readFloat();
            int passWordValue=Math.round(value);
            password[i]=passWordValue;
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e)
            {
            }

        }
        System.out.println("The password is" + password[0] + password[1]+ password[2]+ password[3]);
    }
}
    
            
        