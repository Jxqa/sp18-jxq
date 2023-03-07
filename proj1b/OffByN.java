public class OffByN implements CharacterComparator {
    private int n;
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == this.n) {
            return true;
        }
        return false;
    }

    public OffByN(int N) {
        n = N;
    }
}
