import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BrainfuckToJava {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		int ch = 0;
		if(args.length != 1) {
			System.out.println("Usage: java BrainfuckToJava <filename>"
						   + "\nNOTE: Run the 'run.bat' after that to see the output.");
			System.exit(1);
		}
		BufferedReader in = new BufferedReader(new FileReader(new File(args[0])));
		PrintWriter out = new PrintWriter(new File("Main.java"));
		final String start = "import java.io.BufferedReader;"
			             + "\nimport java.io.InputStreamReader;"
			             + "\nimport java.io.IOException;"
			             + "\npublic class Main {"
						 + "\npublic static void main(String[] args) {"
						 + "\ntry {"
						 + "\nint ptr = 0;"
						 + "\nchar[] buff = new char[30000];"
						 + "\nBufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));";
		String code = start;
		while ((ch = in.read()) != -1) {
			switch ((char) ch) {
			case '>':
				code += "\n++ptr;";
				break;
			case '<':
				code += "\n--ptr;";
				break;
			case '+':
				code += "\n++buff[ptr];";
				break;
			case '-':
				code += "\n--buff[ptr];";
				break;
			case '.':
				code += "\nSystem.out.print(buff[ptr]);";
				break;
			case ',':
				code += "\nbuff[ptr] = stdin.readLine().charAt(0);";
				break;
			case '[':
				code += "\nwhile (buff[ptr] != (char)0) {";
				break;
			case ']':
				code += "\n}";
				break;
			}
		}
		code += "\n}";
		code += "\ncatch(Exception e) { e.printStackTrace(); }";
		code += "\n}";
		code += "\n}\n";
		out.println(code);
		out.close();
		in.close();
	}
}
