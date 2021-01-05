package command;

public class ChangeChannelCommand implements Command {

    private final Television tv;

    public ChangeChannelCommand() {
        tv = new Television();
    }

    @Override
    public void execute(int i) {
        tv.changeChannel(i);
    }
}
