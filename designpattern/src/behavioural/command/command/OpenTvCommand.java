package behavioural.command.command;

import behavioural.command.receiver.TelevisionReceiver;

/**
 * Concrete Command - Open TV Command
 * <p>
 * Implements the Command interface for opening a television.
 * This is a concrete command that knows how to perform the 'open TV' operation
 * by delegating to the receiver (TelevisionReceiver).
 * <p>
 * Educational Note: Concrete commands bind together a receiver and an action.
 * They implement the execute() method by calling the appropriate receiver method(s).
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 * <p>
 * Optimized For Educational Clarity: 2025-03-10
 */
public class OpenTvCommand implements Command {

    // Holds a reference to the receiver object
    private final TelevisionReceiver tv;

    /**
     * Constructor - binds this command to a specific receiver.
     * @param tv The television receiver that will perform the actual operation
     */
    public OpenTvCommand(TelevisionReceiver tv) {
        this.tv = tv;
    }

    /**
     * Executes the 'open TV' command by delegating to the receiver.
     * Educational Note: This is where the command pattern's encapsulation happens -
     * the invoker doesn't need to know how to open a TV, just that this command does it.
     */
    @Override
    public void execute() {
        tv.open();
    }
}
