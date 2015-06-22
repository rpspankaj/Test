package os_command_example;
import java.io.*;

public class Example1 {
	public static void main(String[] args)
	throws IOException {
		if(args.length != 1) {
			System.out.println("No arguments");
			System.exit(1);
		}
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec("find" + " " + args[0]);
		
		InputStream is = proc.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
}