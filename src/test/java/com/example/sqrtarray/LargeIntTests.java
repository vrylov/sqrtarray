package com.example.sqrtarray;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LargeIntTests {

    @Test
    void testComparisions () {
        byte one[] = {1, 2 ,3 ,4 ,5};

        byte another[] = {1, 2, 3  ,4, 5, 6};

        byte third [] = {1, 2, 3, 4, 5, 7};
        LargeInt oneInt = new LargeInt(one);
        LargeInt anotherInt = new LargeInt(another);
        LargeInt onceMoreInt = new LargeInt(another);
        LargeInt thirdInt = new LargeInt(third);

        assertEquals( -1, oneInt.compareTo(anotherInt));

        assertEquals(1, anotherInt.compareTo(oneInt));

        assertEquals(0, anotherInt.compareTo(onceMoreInt));

        assertEquals (-1, anotherInt.compareTo(new LargeInt(third)));

        assertEquals(1, thirdInt.compareTo(anotherInt));
    }

    @Test
    void givenLargeInt_multiplyByByte_ShouldReturnCorrectValue ()
    {
        byte array[] = { 9, 9, 9, 9, 9, 9, 9, 9, 9 };
        byte result[] = {8, 9, 9 ,9, 9, 9, 9, 9, 9, 1};
        assertEquals(0,
                new LargeInt(array)
                        .multBy((byte)9)
                        .compareTo(new LargeInt(result)));

        LargeInt one = new LargeInt(new byte[] { 3, 3, 3, 3});
        LargeInt another = new LargeInt(new byte[] {9, 9, 9, 9});
        assertEquals(0, one.multBy((byte)3).compareTo(another));

    }

    @Test
    void zeroMultByAnithing () {
        assertEquals(0, LargeInt.ZERO.multBy((byte)1).compareTo(LargeInt.ZERO));
        assertEquals(0, LargeInt.ZERO.multBy((byte)0).compareTo(LargeInt.ZERO));
        assertEquals(0, new LargeInt(new byte[] {1,1,1}).multBy((byte) 0 ).compareTo(LargeInt.ZERO));
    }


    @Test
    void substractTest () {
        LargeInt one = new LargeInt(new byte[] {0, 1, 0, 0, 0} );
        LargeInt another = LargeInt.ZERO;
        assertEquals(0,
                one.subtract(another)
                        .compareTo(new LargeInt(new byte[] {1, 0, 0, 0})));
        assertEquals(0, one
                .subtract(new LargeInt(new byte[]{0, 0, 9, 9, 9})).compareTo(new LargeInt(new byte[]{1})));
        assertEquals(0, one.subtract(one).compareTo(LargeInt.ZERO));

    }

    @Test
    void appendDigitsTest () {
        LargeInt one = new LargeInt(new byte[] {1, 0, 0});
        assertEquals(0, one.appendDigits(new byte[] {0,0}).compareTo(new LargeInt(new byte[]{1, 0, 0, 0, 0})));
        LargeInt another = new LargeInt(new byte[] { 0, 0});
        assertEquals(0, another.appendDigits(new byte[] {0, 0} ).compareTo(LargeInt.ZERO));
    }

    @Test
    void appendDigitTest() {
        assertEquals(0, LargeInt.ZERO.appendDigit((byte)0).compareTo(LargeInt.ZERO));
        assertEquals(0, LargeInt.ZERO.appendDigit((byte)1).compareTo(new LargeInt(new byte[] {1})));
        assertEquals(0, new LargeInt(new byte[] {1}).appendDigit((byte)0).compareTo(new LargeInt(new byte[] {1, 0})));
    }
}
