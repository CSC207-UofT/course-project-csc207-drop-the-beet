package Command;

/**flag a schedule as important in the current planer
 *
 */
public class Flag extends Command {

    /**
     * Constructor of the command
     *
     * @param maxArgs the maximum of the arguments
     * @param minArgs the minimum of the arguments
     */
    public Flag(int maxArgs, int minArgs) {
        super(maxArgs, minArgs);
    }

    /**
     * The abstract class for executeCommand
     *
     * @param StateCode the stat of the shell
     * @param arguments the arguments that the command returned
     * @return A list of string of the command result
     * @throws Exception the exception raised when the command executed
     */
    @Override
    public List<String> executeCommand(int StateCode, List<String> arguments) throws Exception {
        return null;
    }
}
