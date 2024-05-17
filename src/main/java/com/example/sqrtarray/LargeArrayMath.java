package com.example.sqrtarray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LargeArrayMath {

    static byte[] intSqrt(byte arg[]) {

        byte workWith[] = null;

        if (arg.length % 2 == 1) {  //fill lead 0
            workWith = new byte[arg.length+1];
            System.arraycopy(arg, 0, workWith, 1, arg.length);
        } else {
            workWith = arg;
        }
        LargeInt currentResult = LargeInt.ZERO;
        LargeInt currentSubject = LargeInt.ZERO;
        LargeInt previousAttempt;
        LargeInt currentResultBy2;
        byte candidateDigit;
        int extractPos=0;
        byte[] nextPair = new byte[2];

        while (extractPos < workWith.length) {

            nextPair[0] = workWith[extractPos++];
            nextPair[1] = workWith[extractPos++];
            currentSubject = currentSubject.appendDigits(nextPair);
            currentResultBy2 = currentResult.multBy((byte)2);
            candidateDigit = 0;
            previousAttempt = LargeInt.ZERO;
            for ( ; candidateDigit < 10; candidateDigit++) {
                LargeInt attempt =currentResultBy2.appendDigit((candidateDigit)).multBy(candidateDigit);
                if ( attempt.compareTo(currentSubject)>0) {
                    break;
                } else {
                    previousAttempt = attempt;
                }
            }
            candidateDigit--;
            currentResult = currentResult.appendDigit(candidateDigit);
            currentSubject = currentSubject.subtract(previousAttempt);
        }
        return currentResult.toArray();
    }
}
