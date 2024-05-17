package com.example.sqrtarray;

import java.util.Arrays;

public class LargeInt {
    private byte digits[];
    public static final LargeInt ZERO = new LargeInt(new byte[] {0});

    public LargeInt(byte[] digits ) {
        if (digits == null ) throw new IllegalArgumentException(("digits should not be null"));
        int firstNot0 = 0;

        for (var d : digits) {
            if (d > 9 || d < 0) throw new IllegalArgumentException("Illegal digit : " + d);
        }

        for (var d: digits) {
            if (d==0) firstNot0++;
            else break;
        }

        if (firstNot0 == digits.length) this.digits = new byte[] {0};
        else this.digits  = Arrays.copyOfRange(digits, firstNot0, digits.length);
    }

    private LargeInt(byte[] digits, boolean doNotCopy) {
        if (doNotCopy) this.digits = digits;
        else this.digits = Arrays.copyOf(digits, digits.length);
    }

    public LargeInt multBy(byte v) {
        if (v < 0 || v> 9) throw  new IllegalArgumentException("Illegal v: "+v);
        if (v ==0 ) return LargeInt.ZERO;
        byte result[] = new byte[digits.length+1];
        int m = 0;
        for (int i = digits.length-1; i>=0; i--) {
            int t =  digits[i]*v+m;
            result[i+1] = (byte) (t % 10);
            m = (t/10);
        }
        result[0] = (byte) m;
        return result[0] > 0 ? new LargeInt(result, true) : new LargeInt(Arrays.copyOfRange(result, 1, result.length), true);
    }

    public int compareTo(LargeInt another) {
        if (digits.length > another.digits.length) return 1;
        else if (digits.length < another.digits.length) return -1;
        else {
            for (int i = 0; i< digits.length; i++) {
                if (digits[i] > another.digits[i] ) return 1;
                else if (digits[i] < another.digits[i]) return -1;
                else continue;
            }
            return 0;
        }
    }

    public byte[] toArray () {
        return Arrays.copyOf(digits, digits.length);
    }

    LargeInt subtract(LargeInt another) {
        if (compareTo(another) < 0 ) throw new IllegalArgumentException("Subtracted is larger than original");
        byte result[] = Arrays.copyOf(digits, digits.length);
        int borrow = 0;
        for (int i = digits.length-1, j= another.digits.length-1; i>=0 && j>=0; i--, j--) {
            if (digits[i] >=  another.digits[j] + borrow ) {
                result[i] = (byte)(digits[i] - (another.digits[j] + borrow));
                borrow = 0;
            } else {
                result[i] = (byte) ((digits[i] +10) - another.digits[j] - borrow);
                borrow = 1;
            }
        }
        if (borrow == 1) {
            for (int i = digits.length - another.digits.length-1; i>=0; i--) {
                if (digits[i] >=borrow) {
                    result[i] = (byte)(digits[i] - borrow);
                    break;
                } else {
                    result[i] = (byte) ((digits[i] +10) -  borrow);
                }
            }
        }
        return new LargeInt(result); // to collapse leading zeroes
    }

    LargeInt appendDigits (byte toAppend[]) {
        if ( this.compareTo(ZERO)==0 )
            return new LargeInt(toAppend);
        for (var b  : toAppend) {
            if (b <0 || b>9) throw new IllegalArgumentException("Append bytes contains illegal value: "+b);
        }
        byte result[] = Arrays.copyOf(digits, digits.length +  toAppend.length);
        System.arraycopy(toAppend, 0, result, digits.length,  toAppend.length);
        return new LargeInt(result, true);
    }

    LargeInt appendDigit(byte v) {
        if (v<0 || v>9 ) throw new IllegalArgumentException("Illigal digit to append : "+v);
        if (this.compareTo(ZERO) == 0)  {
            if (v == 0) {
                return this;
            }
            else {
                return new LargeInt(new byte[] {v} , true);
            }
        }
        byte[] result = Arrays.copyOf(digits, digits.length +  1);
        result[digits.length] = v;
        return new LargeInt(result, true);
    }

}
