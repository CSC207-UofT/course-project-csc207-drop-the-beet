package Command;
import java.util.List;

/**
 *  A class that performs chunking of input command
 */

public class ArgumentsTuple {
    private final String command;
    private final List<String> args;

    /**
     * Constructor for the result
     * @param command commandline input
     * @param args command arguments input
     */
    public ArgumentsTuple(String command, List<String> args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Return the command
     * @return command the first object
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Return the command arguments
     * @return arguments the second object
     */
    public List<String> getArguments() {
        return this.args;
    }


}
