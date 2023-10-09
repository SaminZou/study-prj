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
public class CloseTvCommand implements Command {

    private final Television tv;

    public CloseTvCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute(int i) {
        tv.close();
    }
}
