package bowling.state;

public class Strike extends EndState {
    private Strike() {}

    public static Strike instance() {
        return LazyHolder.STRIKE;
    }

    private static class LazyHolder {
        public static final Strike STRIKE = new Strike();

        private LazyHolder() {}
    }

    @Override
    public boolean isCleanState() {
        return true;
    }
}
