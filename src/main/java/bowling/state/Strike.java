package bowling.state;

public class Strike extends EndState {
    private Strike() {}

    public static Strike instance() {
        return LazyHolder.STRIKE;
    }

    private static class LazyHolder {
        public static Strike STRIKE = new Strike();
    }
}
