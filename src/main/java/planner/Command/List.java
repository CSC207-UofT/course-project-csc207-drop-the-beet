package planner.Command;

/**format and display the events in any planner
 *
 */
public class List<S> extends Command{

    /**
     * Constructor of the command
     *
     * @param maxArgs the maximum of the arguments
     * @param minArgs the minimum of the arguments
     */
    public List(int maxArgs, int minArgs) {
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
