package collection;

/**
 * HashMap linked list node implementation
 * <p>
 * Simulates HashMap internal linked list structure with basic key-value storage
 * Provides common HashMap operation examples including insert, search, delete
 *
 * @author Samin
 * @since 2024-06-03
 */
public class EntryUseCase {

    /** Key */
    private Object key;

    /** Value */
    private Object value;

    /** Next node reference */
    private EntryUseCase next;
    
    /** Hash value */
    private int hash;

    // ========== Constructors ==========

    /**
     * Default constructor
     */
    public EntryUseCase() {
        // Empty constructor for flexible initialization
    }

    /**
     * Constructor with key and next node
     *
     * @param key  Key
     * @param next Next node
     */
    public EntryUseCase(Object key, EntryUseCase next) {
        this.key = key;
        this.next = next;
    }

    /**
     * Constructor with key, value and hash
     *
     * @param key   Key
     * @param value Value
     * @param hash  Hash value
     */
    public EntryUseCase(Object key, Object value, int hash) {
        this.key = key;
        this.value = value;
        this.hash = hash;
    }

    /**
     * Constructor with key, value, hash and next node
     *
     * @param key   Key
     * @param value Value
     * @param hash  Hash value
     * @param next  Next node
     */
    public EntryUseCase(Object key, Object value, int hash, EntryUseCase next) {
        this.key = key;
        this.value = value;
        this.hash = hash;
        this.next = next;
    }

    // ========== Getter and Setter methods ==========

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public EntryUseCase getNext() {
        return next;
    }

    public void setNext(EntryUseCase next) {
        this.next = next;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    // ========== Common methods ==========

    /**
     * Check if key matches
     *
     * @param key Key to compare
     * @return true if key matches
     */
    public boolean keyEquals(Object key) {
        return this.key == key || (this.key != null && this.key.equals(key));
    }

    /**
     * Check if hash value matches
     *
     * @param hash Hash value to compare
     * @return true if hash matches
     */
    public boolean hashEquals(int hash) {
        return this.hash == hash;
    }

    /**
     * Check if node contains specified key
     *
     * @param key Key to find
     * @return true if found
     */
    public boolean containsKey(Object key) {
        EntryUseCase current = this;
        while (current != null) {
            if (current.keyEquals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Get linked list size
     *
     * @return Number of nodes in linked list
     */
    public int size() {
        int count = 0;
        EntryUseCase current = this;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Append node to end of linked list
     *
     * @param newNode Node to append
     */
    public void append(EntryUseCase newNode) {
        EntryUseCase current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    /**
     * Prepend node to head of linked list
     *
     * @param newNode Node to prepend
     * @return New head node
     */
    public EntryUseCase prepend(EntryUseCase newNode) {
        newNode.next = this;
        return newNode;
    }

    // ========== Example methods ==========

    /**
     * Demonstrate basic linked list operations
     */
    public static void demoBasicOperations() {
        System.out.println("=== HashMap Linked List Node Basic Operations Demo ===");

        // Create head node
        EntryUseCase header = new EntryUseCase("key1", "value1", "key1".hashCode());
        System.out.println("Created head node: key=" + header.getKey() + ", value=" + header.getValue());

        // Add second node
        EntryUseCase node2 = new EntryUseCase("key2", "value2", "key2".hashCode());
        header.append(node2);
        System.out.println("Added second node: key=" + node2.getKey());

        // Prepend new node to head
        EntryUseCase node0 = new EntryUseCase("key0", "value0", "key0".hashCode());
        header = header.prepend(node0);
        System.out.println("Prepended node to head: key=" + node0.getKey());

        // Check linked list size
        System.out.println("Linked list size: " + header.size());

        // Check if key exists
        System.out.println("Check if key2 exists: " + header.containsKey("key2"));
        System.out.println("Check if key3 exists: " + header.containsKey("key3"));
    }

    /**
     * Demonstrate HashMap collision resolution scenario
     */
    public static void demoHashCollision() {
        System.out.println("\n=== HashMap Hash Collision Demo ===");

        // Simulate hash collision (same hash value, different keys)
        String key1 = "Aa";
        String key2 = "BB";

        // These two strings produce same hash value with default hash function
        int hash1 = key1.hashCode();
        int hash2 = key2.hashCode();

        System.out.println("key1='" + key1 + "' hash value: " + hash1);
        System.out.println("key2='" + key2 + "' hash value: " + hash2);
        System.out.println("Hash values are equal: " + (hash1 == hash2));

        // Create collision linked list
        EntryUseCase bucket = new EntryUseCase(key1, "value1", hash1);
        EntryUseCase collisionNode = new EntryUseCase(key2, "value2", hash2);
        bucket.append(collisionNode);

        System.out.println("Collision linked list size: " + bucket.size());
        System.out.println("Find key1: " + bucket.containsKey(key1));
        System.out.println("Find key2: " + bucket.containsKey(key2));
    }

    /**
     * Main method - run all demos
     */
    public static void main(String[] args) {
        System.out.println("EntryUseCase Demo Program Starting...\n");

        // Run basic operations demo
        demoBasicOperations();

        // Run hash collision demo
        demoHashCollision();

        System.out.println("\n=== Original Example (for compatibility) ===");
        // Keep original example for compatibility
        EntryUseCase header = new EntryUseCase(new Object(), null);
        header.setNext(new EntryUseCase(new Object(), null));
        header = new EntryUseCase(new Object(), header);

        System.out.println("\nDemo program finished.");
    }

    /**
     * Override toString method for debugging
     */
    @Override
    public String toString() {
        return "EntryUseCase{key=" + key + ", value=" + value + ", hash=" + hash + "}";
    }
}