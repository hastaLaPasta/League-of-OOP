package main;

import java.io.IOException;

public final class Main {
    private Main() {
    }
    public static void main(final String[] args) throws IOException {
        Map map = new Map(args);
        map.startGame();
        map.writeOutput(args);
    }
}
