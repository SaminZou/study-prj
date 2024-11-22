package behavioural.iterator.entity;

public class MenuItem {

    private String name;
    private String description;
    private int channel;

    public MenuItem(int channel, String name, String description) {
        this.name = name;
        this.description = description;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
