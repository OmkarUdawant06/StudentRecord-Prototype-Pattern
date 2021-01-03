package studentskills.driver;

import studentskills.util.FileProcessor;
import studentskills.util.Results;
import java.io.IOException;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;
	private static int searchFlag;

	public static void main(String[] args) throws IOException, NullPointerException {

		/*
		 * As the build.xml specifies the arguments as input, modify, output1, output2, output2, error or debug, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}")) || (args[2].equals("${out1}")) || (args[3].equals("${out2}")) || (args[4].equals("${out3}")) || (args[5].equals("${error}")) || (args[6].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		FileProcessor openFp = new FileProcessor(args[0]);
		FileProcessor modifyFp = new FileProcessor(args[1]);
		Results outputClone0 = new Results(args[2]);
		Results outputClone1 = new Results(args[3]);
		Results outputClone2 = new Results(args[4]);
		Results errorWrite = new Results(args[5]);
		Results debugWrite = new Results(args[6]);

		DriverHelper driverHelper = new DriverHelper();

		driverHelper.processInput(openFp, modifyFp, outputClone0, outputClone1, outputClone2, errorWrite, debugWrite, args);
	}
}