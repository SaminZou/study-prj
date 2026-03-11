package behavioural.command;

import behavioural.command.command.ChangeChannelCommand;
import behavioural.command.command.CloseTvCommand;
import behavioural.command.command.Command;
import behavioural.command.command.OpenTvCommand;
import behavioural.command.invoker.ControllerInvoker;
import behavioural.command.receiver.TelevisionReceiver;

/**
 * Command Pattern Demo - Television Remote Control Example
 * <p>
 * This demo shows the Command Pattern in action using a TV remote control scenario.
 * <p>
 * Command Pattern Roles Demonstrated:
 * 1. Command (Interface) - Defines the execute() method
 * 2. Concrete Commands - OpenTvCommand, CloseTvCommand, ChangeChannelCommand
 * 3. Invoker - ControllerInvoker (the remote control)
 * 4. Receiver - TelevisionReceiver (the TV)
 * <p>
 * Key Educational Points:
 * - Commands encapsulate requests as objects
 * - Invoker is decoupled from receivers
 * - Supports undo operations
 * - Commands can be parameterized or stateful
 * <p>
 * @author samin
 * @date 2021-01-05
 * @optimized 2025-03-10 for educational clarity
 */
public class DemoEntry {

    public static void main(String[] args) {
        System.out.println("=== Command Pattern Demo: TV Remote Control ===\n");

        // Step 1: Create the Receiver (the actual TV)
        System.out.println("1. Creating Receiver (Television)...");
        TelevisionReceiver tv = new TelevisionReceiver();

        // Step 2: Create Concrete Commands
        System.out.println("2. Creating Concrete Commands...");
        Command openCommand = new OpenTvCommand(tv);
        Command closeCommand = new CloseTvCommand(tv);
        ChangeChannelCommand changeCommand = new ChangeChannelCommand(tv);

        // Step 3: Create the Invoker (Remote Control)
        System.out.println("3. Creating Invoker (Remote Control)...");
        ControllerInvoker remote = new ControllerInvoker(openCommand, closeCommand, changeCommand);

        System.out.println("\n--- Executing Commands ---");

        // Step 4: Execute commands through the invoker
        System.out.println("\n4. Opening TV...");
        remote.open();

        System.out.println("\n5. Changing channels...");
        remote.change();  // Channel 1
        System.out.println("   Current channel: " + remote.getCurrentChannel());
        
        remote.change();  // Channel 2
        System.out.println("   Current channel: " + remote.getCurrentChannel());

        System.out.println("\n6. Demonstrating undo functionality...");
        remote.channelUndo();  // Back to Channel 1
        
        remote.channelUndo();  // Back to Channel 0
        
        remote.channelUndo();  // Stays at Channel 0 (no earlier history)

        System.out.println("\n7. Closing TV...");
        remote.close();

        System.out.println("\n=== Demo Complete ===");
        System.out.println("\nEducational Summary:");
        System.out.println("- The Command Pattern encapsulates requests as objects");
        System.out.println("- The Invoker (remote) doesn't know how the TV works");
        System.out.println("- Commands can be queued, logged, or undone");
        System.out.println("- New commands can be added without changing existing code");
    }
}
