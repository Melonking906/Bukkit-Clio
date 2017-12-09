package me.nonit.clio.operation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum AutoOperation
{
    ANNOUNCE, GIVE, SCANNER;

    private static final List<AutoOperation> VALUES = Collections.unmodifiableList( Arrays.asList( values() ) );
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static AutoOperation randomOperation()
    {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}