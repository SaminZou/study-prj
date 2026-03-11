package behavioural.command.command;

import behavioural.command.receiver.TelevisionReceiver;

/**
 * Concrete Command - Close TV Command
 * <p>
 * Implements the Command interface for closing a television.
 * This is a concrete command that knows how to perform the 'close TV' operation
 * by delegating to the receiver (TelevisionReceiver).
 * <p>
 * Educational Note: Each concrete command encapsulates a specific operation.
 * The invoker can work with any command without knowing its concrete type.
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 * <p>
 * Optimized For Educational Clarity: 2025-03-10
 */
public class CloseTvCommand implements Command {

    // Holds a reference to the receiver object
    private final TelevisionReceiver tv;

    /**
     * Constructor - binds this command to a specific receiver.
     * @param tv The television receiver that will perform the actual operation
     */
    public CloseTvCommand(TelevisionReceiver tv) {
        this.tv = tv;
    }

    /**
     * Executes the 'close TV' command by delegating to the receiver.
     * Educational Note: Commands can be stored, queued, logged, or undone.
     * This flexibility is a key benefit of the Command Pattern.
     */
    @Override
    public void execute() {
        tv.close();
    }
}
