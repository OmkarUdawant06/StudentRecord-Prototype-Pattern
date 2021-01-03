package studentskills.util;

public class MyLogger {

    /**
     * Enum stores Debug point required for assignment
     */
    public static enum DebugLevel {
        CONSTRUCTOR,
        FILE_PROCESSOR,
        INSERT,
        MODIFY,
        NONE;
    };

    private static DebugLevel debugLevel;

    /**
     * Set Debug value
     * @param levelIn value of DebugLevel to set
     */
    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 4: debugLevel = DebugLevel.CONSTRUCTOR;
                    break;
            case 3: debugLevel = DebugLevel.FILE_PROCESSOR;
                    break;
            case 2: debugLevel = DebugLevel.INSERT;
                    break;
            case 1: debugLevel = DebugLevel.MODIFY;
                    break;
            default: debugLevel = DebugLevel.NONE;
                    break;
        }
    }

    /**
     * Set the Debug level
     * @param levelIn Debug level value
     */
    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    /**
     * Prints the message
     * @param message Message to be printed
     * @param levelIn Debug level
     */
    public static String writeMessage (String message  , DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            return message;

        return message;
    }

    /**
     * Print for debugging
     * @return The debugging level set to
     */
    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}
