package behavioural.command.command;

import behavioural.command.receiver.TelevisionReceiver;

/**
 * Concrete Command - Change Channel Command
 * <p>
 * Implements the Command interface for changing television channels.
 * This command requires a parameter (channel number) to execute.
 * <p>
 * Educational Note: This demonstrates how commands can have different requirements.
 * We use a separate executeWithParam method for parameterized commands.
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 * <p>
 * Optimized For Educational Clarity: 2025-03-10
 */
public class ChangeChannelCommand implements Command {

    // Holds a reference to the receiver object
    private final TelevisionReceiver tv;
    // Current channel to change to
    private int channel;

    /**
     * Constructor - binds this command to a specific receiver.
     * @param tv The television receiver that will perform the actual operation
     */
    public ChangeChannelCommand(TelevisionReceiver tv) {
        this.tv = tv;
        this.channel = 0; // Default channel
    }

    /**
     * Sets the channel for this command execution.
     * @param channel The channel number to change to
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * Executes the 'change channel' command with the currently set channel.
     * Educational Note: This shows an alternative approach - setting state before execution.
     * Another approach would be to pass the parameter in execute().
     */
    @Override
    public void execute() {
        tv.changeChannel(channel);
    }
}
