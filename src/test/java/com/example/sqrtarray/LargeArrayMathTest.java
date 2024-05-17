package com.example.sqrtarray;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LargeArrayMathTest {
    @Test
    void squareFromOneShouldBeOne () {
        byte[] arg = {1};
        byte[] res = LargeArrayMath.intSqrt(arg);
        assertArrayEquals(arg, res);

    }

    @Test
    void squareFromZeroShouldBeZero() {
        byte[] arg = {0};
        byte[] res = LargeArrayMath.intSqrt(arg);
        assertArrayEquals(arg, res);
    }
    @Test
    void squreFrom_2_ShouldBe_1() {
        byte[] arg = {2};
        byte[] res = LargeArrayMath.intSqrt(arg);
        assertArrayEquals(new byte[] {1}, res);
    }


    @Test
    void squreFrom_9_ShouldBe_3() {
        byte[] arg = {9};
        byte[] res = LargeArrayMath.intSqrt(arg);
        assertArrayEquals(new byte[] {3}, res);
    }

    @Test
    void squreFrom_625_shouldBe_25() {
        byte[] res = LargeArrayMath.intSqrt(new byte[] {6, 2, 5});
        assertArrayEquals(new byte[] {2, 5}, res);
    }

    @Test
    void squreFrom_9999_shouldBe_99() {
        byte[] res = LargeArrayMath.intSqrt(new byte[] {9, 9, 9, 9});
        assertArrayEquals(new byte[]{9, 9}, res);
    }

    @Test
    void squareFrom_01234567890_shouldBe_35136() {
        byte[] res = LargeArrayMath.intSqrt(new byte[] {0,1, 2,3,4,5,6,7,8,9,0});
        assertArrayEquals(new byte[] {3,5,1,3,6} , res);
    }



}
