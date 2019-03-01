package com.github.h90871424.hello;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EcTest {

    private BigInteger n = new BigInteger("455839");
    private int bits = n.bitLength();

    private BigInteger a = new BigInteger("5");
    private BigInteger b = new BigInteger("-5");
    private ECCurve.Fp curve = new ECCurve.Fp(n, a, b.mod(n), null, null);
    private ECPoint p = curve.validatePoint(BigInteger.valueOf(1), BigInteger.valueOf(1));

    @Test
    void test() {
        for (int i = 0; i < 9; i++) {
            ECPoint newPoint = p.multiply(factorial(i));
            System.out.println(newPoint);
            System.out.println(newPoint.isValid());
            System.out.println(newPoint.normalize().isValid());
        }

    }

    @Test
    void asdf() {
        ECPoint badPoint = p.multiply(factorial(8));
        modInverse(badPoint.getZCoord(0).toBigInteger());
//        modInverse(BigInteger.valueOf(165923));
    }

    private BigInteger modInverse(BigInteger x) {
        int len = (bits + 31) >> 5;
        int[] p = Nat.fromBigInteger(bits, n);
        int[] n = Nat.fromBigInteger(bits, x);
        int[] z = Nat.create(len);
        invert(p, n, z);
        return Nat.toBigInteger(len, z);
    }


    private static void invert(int[] p, int[] x, int[] z) {
        int len = p.length;
        if (Nat.isZero(len, x)) {
            throw new IllegalArgumentException("'x' cannot be 0");
        }
        if (Nat.isOne(len, x)) {
            System.arraycopy(x, 0, z, 0, len);
            return;
        }

        int[] u = Nat.copy(len, x);
        int[] a = Nat.create(len);
        a[0] = 1;
        int ac = 0;

        if ((u[0] & 1) == 0) {
            ac = inversionStep(p, u, len, a, ac);
        }
        if (Nat.isOne(len, u)) {
            inversionResult(p, ac, a, z);
            return;
        }

        int[] v = Nat.copy(len, p);
        int[] b = Nat.create(len);
        int bc = 0;

        int uvLen = len;

        for (; ; ) {
            while (u[uvLen - 1] == 0 && v[uvLen - 1] == 0) {
                --uvLen;
            }

            if (Nat.gte(uvLen, u, v)) {
                Nat.subFrom(uvLen, v, u);
//              assert (u[0] & 1) == 0;
                ac += Nat.subFrom(len, b, a) - bc;
                ac = inversionStep(p, u, uvLen, a, ac);
                if (Nat.isOne(uvLen, u)) {
                    inversionResult(p, ac, a, z);
                    return;
                }
            } else {
                Nat.subFrom(uvLen, u, v);
//              assert (v[0] & 1) == 0;
                bc += Nat.subFrom(len, a, b) - ac;
                bc = inversionStep(p, v, uvLen, b, bc);
                if (Nat.isOne(uvLen, v)) {
                    inversionResult(p, bc, b, z);
                    return;
                }
            }
        }
    }


    private static void inversionResult(int[] p, int ac, int[] a, int[] z) {
        if (ac < 0) {
            Nat.add(p.length, a, p, z);
        } else {
            System.arraycopy(a, 0, z, 0, p.length);
        }
    }


    private static int inversionStep(int[] p, int[] u, int uLen, int[] x, int xc) {
        int len = p.length;
        int count = shiftLeftToFirstNonzero(u, uLen);

        {
            int zeroes = getTrailingZeroes(u[0]);
            if (zeroes > 0) {
                Nat.shiftDownBits(uLen, u, zeroes, 0);
                count += zeroes;
            }
        }

        for (int i = 0; i < count; ++i) {
            if ((x[0] & 1) != 0) {
                if (xc < 0) {
                    xc += Nat.addTo(len, p, x);
                } else {
                    xc += Nat.subFrom(len, p, x);
                }
            }

//            assert xc == 0 || xc == 1;
            Nat.shiftDownBit(len, x, xc);
        }

        return xc;
    }

    private static int shiftLeftToFirstNonzero(int[] u, int uLen) {
        int count = u.length;
        while (u[0] == 0) {
            count--;
            assert count > 0 : "zero input";
            shiftDownWord(uLen, u, 0);
        }
        return 32 * (u.length - count);
    }

    @Test
    void testInversionStep() {
        int[] u = {0, 0, 1};
        assertEquals(64, shiftLeftToFirstNonzero(u, u.length));
        assertArrayEquals(new int[]{1, 0, 0}, u);
        u = new int[]{0, 1, 2};
        assertEquals(32, shiftLeftToFirstNonzero(u, u.length));
        assertArrayEquals(new int[]{1, 2, 0}, u);
        u = new int[]{1, 0, 2};
        assertEquals(0, shiftLeftToFirstNonzero(u, u.length));
        assertArrayEquals(new int[]{1, 0, 2}, u);
    }


    private static int shiftDownWord(int len, int[] z, int c) {
        int i = len;
        while (--i >= 0) {
            int next = z[i];
            z[i] = c;
            c = next;
        }
        return c;
    }


    private static int getTrailingZeroes(int x) {
//        assert x != 0;

        int count = 0;
        while ((x & 1) == 0) {
            x >>>= 1;
            ++count;
        }
        return count;
    }

    private BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
