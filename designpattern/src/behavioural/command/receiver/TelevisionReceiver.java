package behavioural.command.receiver;

/**
 * Receiver - Command Pattern Role
 * <p>
 * The Receiver knows how to perform the actual work.
 * It contains the business logic that commands will delegate to.
 * <p>
 * Educational Note: The Receiver is decoupled from the Invoker.
 * Commands act as intermediaries between Invokers and Receivers.
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 * <p>
 * Optimized For Educational Clarity: 2025-03-10
 */
public class TelevisionReceiver {

    /**
     * Opens the television.
     * Educational Note: This is the actual business logic -
     * what really happens when we 'open' a TV.
     */
    public void open() {
        System.out.println("TV opened successfully. (TV is now ON)");
    }

    /**
     * Closes the television.
     * Educational Note: The Receiver performs the real work,
     * while Commands just know which Receiver method to call.
     */
    public void close() {
        System.out.println("TV closed successfully. (TV is now OFF)");
    }

    /**
     * Changes to the specified channel.
     * @param channel The channel number to change to
     * Educational Note: Parameterized operations show how commands
     * can work with different types of requests.
     */
    public void changeChannel(int channel) {
        System.out.println("Channel changed to: " + channel + " (Switched to channel " + channel + ")");
    }
}
