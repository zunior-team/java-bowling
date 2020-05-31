package bowling.state;

public abstract class EndState extends State {
    @Override
    public boolean isEnd() {
        return true;
    }
}
