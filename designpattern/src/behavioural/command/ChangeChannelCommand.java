package behavioural.command;

/**
 * Concrete Command
 * <p>
 * Description: Concrete Command
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 */
public class ChangeChannelCommand implements Command {

    private final Television tv;

    public ChangeChannelCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute(int i) {
        tv.changeChannel(i);
    }
}
