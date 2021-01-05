package command;

public class Controller {

    public int nowChannel = 0; // 当前频道
    public int priorChannel; // 前一个频道，用于执行返回操作
    private final Command openTVCommand;
    private final Command closeTVCommand;
    private final Command changeChannelCommand;

    public Controller(Command openTvCommand, Command closeTvCommand, Command changeChannelCommand) {
        this.openTVCommand = openTvCommand;
        this.closeTVCommand = closeTvCommand;
        this.changeChannelCommand = changeChannelCommand;
    }

    // 打开电视剧
    public void open() {
        openTVCommand.execute(0);
    }

    // 关闭电视机
    public void close() {
        closeTVCommand.execute(0);
    }

    // 换频道：只在当前频道递增
    public void change() {
        // 换频道前记录当前频道
        priorChannel = nowChannel;
        // 频道+1
        nowChannel++;
        changeChannelCommand.execute(nowChannel);
    }

    // 频道返回
    public void ChannelUndo() {
        // 将以前的频道传入
        changeChannelCommand.execute(priorChannel);
        // 当前频道与前一个频道进行互换
        int tempChannel;
        tempChannel = priorChannel;
        priorChannel = nowChannel;
        nowChannel = tempChannel;
    }
}
