package behavioural.command.command;

import behavioural.command.receiver.TelevisionReceiver;

/**
 * Concrete Command
 * <p>
 * Description: Concrete Command
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 */
public class OpenTvCommand implements Command {

    private final TelevisionReceiver tv;

    public OpenTvCommand(TelevisionReceiver tv) {
        this.tv = tv;
    }

    @Override
    public void execute(int i) {
        tv.open();
    }
}
