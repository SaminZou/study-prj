package template;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Date Cycle Processing Template - Optimized Version
 *
 * Provides common date cycle processing scenarios:
 * 1. Daily cycle processing
 * 2. Weekly cycle processing  
 * 3. Monthly cycle processing
 * 4. Custom interval processing
 * 5. Workday processing (excluding weekends)
 *
 * Optimizations:
 * - Fixed left-closed right-open interval boundary conditions
 * - Added exception handling and parameter validation
 * - Provided multiple common scenario examples
 * - Improved code readability and structure
 * - Added performance statistics and logging output
 *
 * @author samin
 * @date 2024-10-01
 * @version 2.0
 */
public class DateCycleTask {

    /**
     * Default date formatter
     */
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Processing mode enumeration
     */
    public enum ProcessMode {
        DAILY,
        WEEKLY,
        MONTHLY,
        CUSTOM
    }

    /**
     * Date processing configuration
     */
    public static class DateCycleConfig {

        private final LocalDate startDate;
        private final LocalDate endDate;
        private final ProcessMode mode;
        private final int intervalDays;

        public DateCycleConfig(LocalDate startDate, LocalDate endDate, ProcessMode mode) {
            this(startDate, endDate, mode, 1);
        }

        public DateCycleConfig(LocalDate startDate, LocalDate endDate, ProcessMode mode, int intervalDays) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.mode = mode;
            this.intervalDays = intervalDays;
        }

        public LocalDate getStartDate() {return startDate;}

        public LocalDate getEndDate() {return endDate;}

        public ProcessMode getMode() {return mode;}

        public int getIntervalDays() {return intervalDays;}
    }

    public static void main(String[] args) {
        DateCycleTask task = new DateCycleTask();

        System.out.println("=== Daily Cycle Processing Example ===");
        task.processDateCycle(new DateCycleConfig(LocalDate.parse("2024-10-01", DEFAULT_FORMATTER),
                                                  LocalDate.parse("2024-10-12", DEFAULT_FORMATTER), ProcessMode.DAILY));

        System.out.println("\n=== Weekly Cycle Processing Example ===");
        task.processDateCycle(new DateCycleConfig(LocalDate.parse("2024-01-01", DEFAULT_FORMATTER),
                                                  LocalDate.parse("2024-01-31", DEFAULT_FORMATTER), ProcessMode.WEEKLY));

        System.out.println("\n=== Monthly Cycle Processing Example ===");
        task.processDateCycle(new DateCycleConfig(LocalDate.parse("2024-01-01", DEFAULT_FORMATTER),
                                                  LocalDate.parse("2024-06-01", DEFAULT_FORMATTER), ProcessMode.MONTHLY));

        System.out.println("\n=== Custom Interval Processing Example ===");
        task.processDateCycle(new DateCycleConfig(LocalDate.parse("2024-10-01", DEFAULT_FORMATTER),
                                                  LocalDate.parse("2024-10-31", DEFAULT_FORMATTER), ProcessMode.CUSTOM, 3));

        System.out.println("\n=== Workday Processing Example (excluding weekends) ===");
        task.processWorkdays(LocalDate.parse("2024-10-01", DEFAULT_FORMATTER),
                             LocalDate.parse("2024-10-15", DEFAULT_FORMATTER));
    }

    /**
     * Generic date cycle processing method
     *
     * @param config Date processing configuration
     */
    public void processDateCycle(DateCycleConfig config) {
        validateConfig(config);

        LocalDate start = config.getStartDate();
        LocalDate end = config.getEndDate();

        System.out.println("Starting date cycle processing - Mode: " + config.getMode() + ", Start Date: " + start.format(
                DEFAULT_FORMATTER) + ", End Date: " + end.format(DEFAULT_FORMATTER));

        long startTime = System.currentTimeMillis();
        int processedCount = 0;

        switch (config.getMode()) {
            case DAILY:
                processedCount = processDaily(start, end);
                break;
            case WEEKLY:
                processedCount = processWeekly(start, end);
                break;
            case MONTHLY:
                processedCount = processMonthly(start, end);
                break;
            case CUSTOM:
                processedCount = processCustom(start, end, config.getIntervalDays());
                break;
            default:
                throw new IllegalArgumentException("Unknown processing mode: " + config.getMode());
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("Date cycle processing completed! Total time: %dms, Processed days: %d\n", (endTime - startTime),
                          processedCount);
    }

    /**
     * Daily processing
     */
    private int processDaily(LocalDate start, LocalDate end) {
        int count = 0;
        LocalDate current = start;

        while (current.isBefore(end)) {
            processSingleDate(current);
            current = current.plusDays(1);
            count++;
        }

        return count;
    }

    /**
     * Weekly processing
     */
    private int processWeekly(LocalDate start, LocalDate end) {
        int count = 0;
        LocalDate current = start;

        while (current.isBefore(end)) {
            processSingleDate(current);
            current = current.plusWeeks(1);
            count++;
        }

        return count;
    }

    /**
     * Monthly processing
     */
    private int processMonthly(LocalDate start, LocalDate end) {
        int count = 0;
        LocalDate current = start;

        while (current.isBefore(end)) {
            processSingleDate(current);
            current = current.plusMonths(1);
            count++;
        }

        return count;
    }

    /**
     * Custom interval processing
     */
    private int processCustom(LocalDate start, LocalDate end, int intervalDays) {
        if (intervalDays <= 0) {
            throw new IllegalArgumentException("Interval days must be greater than 0: " + intervalDays);
        }

        int count = 0;
        LocalDate current = start;

        while (current.isBefore(end)) {
            processSingleDate(current);
            current = current.plusDays(intervalDays);
            count++;
        }

        return count;
    }

    /**
     * Process single date
     */
    private void processSingleDate(LocalDate date) {
        // Actual business logic - this is just an example
        System.out.println("Processing date: " + date.format(DEFAULT_FORMATTER));

        // Example: Add specific business logic here
        // Such as: database queries, file processing, API calls, etc.
        performBusinessLogic(date);
    }

    /**
     * Workday processing (excluding weekends)
     */
    public void processWorkdays(LocalDate start, LocalDate end) {
        validateDates(start, end);

        System.out.println(
                "Starting workday processing - Start Date: " + start.format(DEFAULT_FORMATTER) + ", End Date: " + end.format(
                        DEFAULT_FORMATTER));

        long startTime = System.currentTimeMillis();
        int workdayCount = 0;
        LocalDate current = start;

        while (current.isBefore(end)) {
            // Skip weekends (Saturday and Sunday)
            if (!isWeekend(current)) {
                processSingleDate(current);
                workdayCount++;
            }
            current = current.plusDays(1);
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("Workday processing completed! Total time: %dms, Workday count: %d\n", (endTime - startTime),
                          workdayCount);
    }

    /**
     * Check if date is weekend
     */
    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek()
                   .getValue() >= 6; // 6=Saturday, 7=Sunday
    }

    /**
     * Execute specific business logic (example)
     */
    private void performBusinessLogic(LocalDate date) {
        // Add actual business logic here
        // For example:
        // 1. Query data for the day
        // 2. Generate reports
        // 3. Send notifications
        // 4. Process files

        // Example: Simulate business processing time
        try {
            Thread.sleep(1); // Simulate processing delay
        } catch (InterruptedException e) {
            Thread.currentThread()
                  .interrupt();
            System.err.println("Business processing interrupted: " + e.getMessage());
        }
    }

    /**
     * Validate configuration parameters
     */
    private void validateConfig(DateCycleConfig config) {
        Objects.requireNonNull(config, "Configuration cannot be null");
        validateDates(config.getStartDate(), config.getEndDate());
    }

    /**
     * Validate date parameters
     */
    private void validateDates(LocalDate start, LocalDate end) {
        Objects.requireNonNull(start, "Start date cannot be null");
        Objects.requireNonNull(end, "End date cannot be null");

        if (!start.isBefore(end)) {
            throw new IllegalArgumentException(
                    "Start date must be before end date: " + start.format(DEFAULT_FORMATTER) + " >= " + end.format(
                            DEFAULT_FORMATTER));
        }
    }

    /**
     * Get days difference between two dates
     */
    public long getDaysBetween(LocalDate start, LocalDate end) {
        validateDates(start, end);
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Old processing method (maintain backward compatibility)
     */
    public static void process(LocalDate start, LocalDate end) {
        DateCycleTask task = new DateCycleTask();
        task.processDateCycle(new DateCycleConfig(start, end, ProcessMode.DAILY));
    }
}