package Command;

/**Delete a schedule in the current planer
 *
 */
public class Delete extends Command {
    /**
     * Constructor of the command
     *
     * @param maxArgs the maximum of the arguments
     * @param minArgs the minimum of the arguments
     */
    public Delete(int maxArgs, int minArgs) {
        super(maxArgs, minArgs);
    }

    @Override
    public List<String> executeCommand(int StateCode, List<String> arguments) throws Exception {
        return null;
    }
}
