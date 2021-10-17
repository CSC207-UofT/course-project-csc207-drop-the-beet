package Command;

abstract class Command {
    protected final int maxArgs;
    protected final int minArgs;

    /**
     * Constructor of the command
     * @param maxArgs the maximum of the arguments
     * @param minArgs the minimum of the arguments
     */
    public Command(int maxArgs, int minArgs) {
        this.maxArgs = maxArgs;
        this.minArgs = minArgs;
    }


    /**
     * Get the maximum of the arguments
     * @return the maxArgs
     */
    public int getMx() {
        return maxArgs;
    }

    /**
     * Get the minimum of the arguments
     * @return the minArgs
     */
    public int getMn() {
        return minArgs;
    }

    /**
     * The abstract class for executeCommand
     * @param StateCode the stat of the shell
     * @param arguments the arguments that the command returned
     * @return A list of string of the command result
     * @throws Exception the exception raised when the command executed
     */
    abstract public List<String> executeCommand(int StateCode, List<String > arguments) throws Exception;

    /**
     * Check the arugments of the input
     * @param args the arguments input
     */
    protected void checkArgument(List<String> args) {
    // TODO: Check the arguments of the input
    }
}

