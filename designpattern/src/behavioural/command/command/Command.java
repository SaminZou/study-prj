package behavioural.command.command;

/**
 * Command Interface - Command Pattern Role
 * <p>
 * Defines the execute method that all commands must implement.
 * This is the core of the Command Pattern - encapsulating a request as an object.
 * <p>
 * Educational Note: The Command Pattern allows you to parameterize objects with operations,
 * queue requests, and support undoable operations.
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 * <p>
 * Optimized For Educational Clarity: 2025-03-10
 */
public interface Command {

    /**
     * Execute the command.
     * This method encapsulates the action to be performed.
     * <p>
     * Educational Note: This is the key method that makes the Command pattern work.
     * Each concrete command implements this method to perform its specific action.
     */
    void execute();
}
