import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

// Java TCPClient host port
public class TCPClient {
	public static void main(String... args) { // accepts argument parameters from command line
		String host = args[0]; // define first parameter
		int port = Integer.valueOf(args[1]); // define second parameter
        String command = "";
        boolean hasThirdParameter = false; 
        if(args.length >= 3) { // if argument has more than 2 parameters
            command = args[2]; // define third parameter
            hasThirdParameter = true;
        }
		
		try {
			Socket sock = new Socket(host, port); // create new socket (should put socket into open connection, otherwise signals via exception)
            OutputStream out = sock.getOutputStream(); // get output stream from socket (only accepts bytes by default)

            if (hasThirdParameter) {
                OutputStreamWriter streamWriter = new OutputStreamWriter(out);
                streamWriter.append(command); // writes string to output stream
                streamWriter.append("\n\n"); // adds end of line characters    
                streamWriter.flush(); // flushes output stream (sends everything that was appended before)  
            }

            InputStream in = sock.getInputStream(); // get input stream from socket
            InputStreamReader inReader = new InputStreamReader(in); // get inputStreamReader from inputStream
            BufferedReader inBufReader = new BufferedReader(inReader); // get BufferedReader from inputStreamReader (allows reading lines)

            String text = inBufReader.readLine(); // read the first input line
            System.out.println(text);
            while (inBufReader.ready()) { // keep reading as long as buffer still has text to read
                text = inBufReader.readLine();
                System.out.println(text);
            }

			sock.close();
		}
		catch (IOException ex) { // catch exceptions
			ex.printStackTrace();
		}
		
	}
}
