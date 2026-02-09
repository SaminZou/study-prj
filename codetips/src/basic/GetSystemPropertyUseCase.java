package basic;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * System Property Utility Class
 *
 * <p>This class provides utility methods for accessing and displaying Java system properties.
 * It demonstrates common use cases for retrieving system information such as JVM settings,
 * operating system details, file paths, and user information.</p>
 *
 * <h3>Common System Properties Examples:</h3>
 * <ul>
 *   <li><b>JVM Information:</b> java.version, java.vendor, java.vm.version</li>
 *   <li><b>Operating System:</b> os.name, os.version, os.arch</li>
 *   <li><b>File Paths:</b> java.home, user.home, user.dir, java.library.path</li>
 *   <li><b>User Information:</b> user.name, user.country, user.language</li>
 *   <li><b>Runtime Environment:</b> file.encoding, line.separator, path.separator</li>
 * </ul>
 *
 * <h3>Usage Examples:</h3>
 * <pre>
 * // Print all system properties
 * java GetSystemPropertyUseCase
 *
 * // Print specific properties
 * java GetSystemPropertyUseCase java.version os.name user.home
 *
 * // Print common use cases
 * java GetSystemPropertyUseCase --common
 * </pre>
 *
 * @author samin
 * @date 2023-03-21
 * @version 2.0
 */
public class GetSystemPropertyUseCase {

    /**
     * Common system properties that are frequently used
     */
    private static final List<String> COMMON_PROPERTIES = Arrays.asList("java.version", "java.vendor", "java.vm.version",
                                                                        "os.name", "os.version", "os.arch", "java.home",
                                                                        "user.home", "user.dir", "java.library.path",
                                                                        "user.name", "file.encoding", "line.separator",
                                                                        "path.separator");

    /**
     * Main method that handles command-line arguments and displays system properties
     *
     * @param args Command-line arguments:
     *             - No arguments: Display all system properties
     *             - Property names: Display specific properties
     *             - "--common": Display common system properties
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            displayAllProperties();
        } else if (args.length == 1 && "--common".equals(args[0])) {
            displayCommonProperties();
        } else {
            displaySpecificProperties(args);
        }
    }

    /**
     * Displays all system properties in a formatted manner
     */
    private static void displayAllProperties() {
        System.out.println("=== ALL SYSTEM PROPERTIES ===");
        System.out.println();

        Properties properties = System.getProperties();
        Enumeration<Object> keys = properties.keys();

        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = properties.getProperty(key);

            // Format the output for better readability
            if (value != null && value.length() > 80) {
                value = value.substring(0, 77) + "...";
            }

            System.out.printf("%-30s : %s%n", key, value);
        }

        System.out.println();
        System.out.println("Total properties: " + properties.size());
    }

    /**
     * Displays commonly used system properties with descriptions
     */
    private static void displayCommonProperties() {
        System.out.println("=== COMMON SYSTEM PROPERTIES ===");
        System.out.println();

        for (String property : COMMON_PROPERTIES) {
            String value = System.getProperty(property);
            String description = getPropertyDescription(property);

            System.out.printf("%-25s : %-40s | %s%n", property, value != null ? value : "(not set)", description);
        }

        System.out.println();
        displaySystemSummary();
    }

    /**
     * Displays specific properties provided as command-line arguments
     *
     * @param properties Array of property names to display
     */
    private static void displaySpecificProperties(String[] properties) {
        System.out.println("=== SPECIFIED PROPERTIES ===");
        System.out.println();

        boolean hasErrors = false;

        for (String property : properties) {
            String value = System.getProperty(property);

            if (value != null) {
                System.out.printf("%-25s : %s%n", property, value);
            } else {
                System.err.printf("%-25s : PROPERTY NOT FOUND%n", property);
                hasErrors = true;
            }
        }

        if (hasErrors) {
            System.out.println();
            System.out.println("Note: Some properties were not found. Use '--common' to see available properties.");
        }
    }

    /**
     * Provides descriptive information for common system properties
     *
     * @param property The system property name
     * @return Description of the property
     */
    private static String getPropertyDescription(String property) {
        switch (property) {
            case "java.version":
                return "Java Runtime Environment version";
            case "java.vendor":
                return "Java Runtime Environment vendor";
            case "java.vm.version":
                return "Java Virtual Machine implementation version";
            case "os.name":
                return "Operating system name";
            case "os.version":
                return "Operating system version";
            case "os.arch":
                return "Operating system architecture";
            case "java.home":
                return "Java installation directory";
            case "user.home":
                return "User's home directory";
            case "user.dir":
                return "User's current working directory";
            case "java.library.path":
                return "Path for native libraries";
            case "user.name":
                return "User's account name";
            case "file.encoding":
                return "Default character encoding";
            case "line.separator":
                return "Line separator string";
            case "path.separator":
                return "Path separator character";
            default:
                return "System property";
        }
    }

    /**
     * Displays a summary of key system information
     */
    private static void displaySystemSummary() {
        System.out.println("=== SYSTEM SUMMARY ===");
        System.out.printf("Java Version: %s (%s)%n", System.getProperty("java.version"), System.getProperty("java.vendor"));
        System.out.printf("JVM Version: %s%n", System.getProperty("java.vm.version"));
        System.out.printf("Operating System: %s %s (%s)%n", System.getProperty("os.name"), System.getProperty("os.version"),
                          System.getProperty("os.arch"));
        System.out.printf("User: %s (%s)%n", System.getProperty("user.name"), System.getProperty("user.home"));
        System.out.printf("Encoding: %s%n", System.getProperty("file.encoding"));
        System.out.printf("Working Directory: %s%n", System.getProperty("user.dir"));
    }
}
