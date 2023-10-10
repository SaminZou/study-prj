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

    private final TelevisionReceiver tv;

    public ChangeChannelCommand(TelevisionReceiver tv) {
        this.tv = tv;
    }

    @Override
    public void execute(int i) {
        tv.changeChannel(i);
    }
}
