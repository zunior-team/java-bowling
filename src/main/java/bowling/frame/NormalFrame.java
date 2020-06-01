package bowling.frame;

import bowling.state.Ready;
import bowling.state.State;

public class NormalFrame extends Frame {

    protected NormalFrame(final State state) {
        super(state);
    }

    public static NormalFrame init() {
        return new NormalFrame(Ready.instance());
    }
}
