package behavioural.command.invoker;

import behavioural.command.command.Command;

/**
 * Invoker - Command Pattern Role
 * <p>
 * The Invoker asks the command to carry out the request.
 * It knows how to execute a command but knows nothing about the concrete command.
 * <p>
 * Educational Note: The Invoker is decoupled from the Receiver -
 * it only works with Command objects, making the system more flexible.
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 * <p>
 * Optimized For Educational Clarity: 2025-03-10
 */
public class ControllerInvoker {

    /**
     * Current channel - tracks the TV's current channel
     */
    private int currentChannel = 0;

    /**
     * Previous channel - used for undo functionality
     */
    private int previousChannel = 0;

    // Commands held by the invoker
    private final Command openTvCommand;
    private final Command closeTvCommand;
    private final behavioural.command.command.ChangeChannelCommand changeChannelCommand;

    /**
     * Constructor - initializes the invoker with commands.
     * @param openTvCommand Command to open the TV
     * @param closeTvCommand Command to close the TV
     * @param changeChannelCommand Command to change channels
     */
    public ControllerInvoker(Command openTvCommand, Command closeTvCommand, 
                             behavioural.command.command.ChangeChannelCommand changeChannelCommand) {
        this.openTvCommand = openTvCommand;
        this.closeTvCommand = closeTvCommand;
        this.changeChannelCommand = changeChannelCommand;
    }

    /**
     * Opens the television.
     * Educational Note: The invoker simply calls execute() on the command object.
     * It doesn't need to know how the TV is opened.
     */
    public void open() {
        openTvCommand.execute();
    }

    /**
     * Closes the television.
     * Educational Note: Same command interface used for different operations.
     */
    public void close() {
        closeTvCommand.execute();
    }

    /**
     * Changes to the next channel.
     * Educational Note: This demonstrates state management in the invoker.
     * The invoker tracks channel state and updates the command before execution.
     */
    public void change() {
        // Save current channel for potential undo
        previousChannel = currentChannel;
        
        // Move to next channel
        currentChannel++;
        
        // Update and execute the change channel command
        changeChannelCommand.setChannel(currentChannel);
        changeChannelCommand.execute();
    }

    /**
     * Undoes the last channel change.
     * Educational Note: This shows how the Command Pattern supports undo functionality.
     * By keeping track of state, we can revert to previous states.
     */
    public void channelUndo() {
        // Swap current and previous channels
        int temp = currentChannel;
        currentChannel = previousChannel;
        previousChannel = temp;
        
        // Execute with the previous channel
        changeChannelCommand.setChannel(currentChannel);
        changeChannelCommand.execute();
        
        System.out.println("Undo performed. Now on channel: " + currentChannel);
    }

    /**
     * Gets the current channel.
     * @return The current channel number
     */
    public int getCurrentChannel() {
        return currentChannel;
    }

    /**
     * Gets the previous channel.
     * @return The previous channel number
     */
    public int getPreviousChannel() {
        return previousChannel;
    }
}
