package com.github.h90871424.lenstra;

import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WNafPreCompInfo;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.math.raw.Nat;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class EllipticCurve {

    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger TWENTY_SEVEN = BigInteger.valueOf(27);
    private static final int IMAX = Integer.MAX_VALUE / 1000;
    private static final BigInteger INT_MAX = BigInteger.valueOf(IMAX);

    private final BigInteger n;

    private final int bits;

    private final BigInteger a;

    private final BigInteger b;

//    private final ECCurve.Fp curve;

    private EllipticCurve(
            BigInteger n,
            BigInteger a,
            BigInteger b) {
        this.n = n;
        this.a = a.mod(n);
        this.b = b.mod(n);
        this.bits = n.bitLength();
    }

    public static EllipticCurve randomCurve(BigInteger n) {
        int a;
        int b;
        do {
            if (n.compareTo(INT_MAX) < 0) {
                int uBound = Math.toIntExact(n.longValueExact());
                a = ThreadLocalRandom.current().nextInt(0, uBound);
                b = ThreadLocalRandom.current().nextInt(0, uBound);
            } else {
                a = ThreadLocalRandom.current().nextInt(0, IMAX);
                b = ThreadLocalRandom.current().nextInt(0, IMAX);
            }
        } while (!isEllipticCurve(n, BigInteger.valueOf(a), BigInteger.valueOf(b)));
        return new EllipticCurve(n, BigInteger.valueOf(a), BigInteger.valueOf(b));
    }

    private static boolean isEllipticCurve(BigInteger n, BigInteger a, BigInteger b) {
        BigInteger aSummand = a.modPow(THREE, n).multiply(FOUR);
        BigInteger bSummand = b.modPow(BigInteger.valueOf(2), n).multiply(TWENTY_SEVEN);
        return !aSummand.add(bSummand).mod(n).equals(BigInteger.ZERO);
    }


    /**
     * AbstractECMultiplier
     */
    public ECPoint multiply(ECPoint p, BigInteger k) {
        int sign = k.signum();
        if (sign == 0 || p.isInfinity()) {
            return p.getCurve().getInfinity();
        }

        ECPoint positive = multiplyPositive(p, k.abs());
        ECPoint result = sign > 0 ? positive : positive.negate();

        /*
         * Although the various multipliers ought not to produce invalid output under normal
         * circumstances, a final check here is advised to guard against fault attacks.
         */
        return result;
    }


    /**
     * Multiplies <code>this</code> by an integer <code>k</code> using the
     * Window NAF method.
     * @param k The integer by which <code>this</code> is multiplied.
     * @return A new <code>ECPoint</code> which equals <code>this</code>
     * multiplied by <code>k</code>.
     */
    protected ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        // Clamp the window width in the range [2, 16]
        int width = Math.max(2, Math.min(16, getWindowSize(k.bitLength())));

        WNafPreCompInfo wnafPreCompInfo = WNafUtil.precompute(p, width, true);
        ECPoint[] preComp = wnafPreCompInfo.getPreComp();
        ECPoint[] preCompNeg = wnafPreCompInfo.getPreCompNeg();

        int[] wnaf = WNafUtil.generateCompactWindowNaf(width, k);

        ECPoint R = p.getCurve().getInfinity();

        int i = wnaf.length;

        /*
         * NOTE: We try to optimize the first window using the precomputed points to substitute an
         * addition for 2 or more doublings.
         */
        if (i > 1) {
            int wi = wnaf[--i];
            int digit = wi >> 16, zeroes = wi & 0xFFFF;

            int n = Math.abs(digit);
            ECPoint[] table = digit < 0 ? preCompNeg : preComp;

            // Optimization can only be used for values in the lower half of the table
            if ((n << 2) < (1 << width)) {
                int highest = LongArray.bitLengths[n];

                // TODO Get addition/doubling cost ratio from curve and compare to 'scale' to see if worth substituting?
                int scale = width - highest;
                int lowBits = n ^ (1 << (highest - 1));

                int i1 = ((1 << (width - 1)) - 1);
                int i2 = (lowBits << scale) + 1;
                R = table[i1 >>> 1].add(table[i2 >>> 1]);

                zeroes -= scale;

//              System.out.println("Optimized: 2^" + scale + " * " + n + " = " + i1 + " + " + i2);
            } else {
                R = table[n >>> 1];
            }

            R = R.timesPow2(zeroes);
        }

        while (i > 0) {
            int wi = wnaf[--i];
            int digit = wi >> 16, zeroes = wi & 0xFFFF;

            int n = Math.abs(digit);
            ECPoint[] table = digit < 0 ? preCompNeg : preComp;
            ECPoint r = table[n >>> 1];

            R = R.twicePlus(r);
            R = R.timesPow2(zeroes);
        }

        return R;
    }

    /**
     * Determine window width to use for a scalar multiplication of the given size.
     *
     * @param bits the bit-length of the scalar to multiply by
     * @return the window size to use
     */
    protected int getWindowSize(int bits) {
        return WNafUtil.getWindowSize(bits);
    }

    public void check(ECPoint newPoint) {
        BigInteger z = newPoint.getZCoord(0).toBigInteger();
        try {
            modInverse(z);
        } catch (Exception e) {
            System.err.println("Divisor found: " + n.gcd(z));
            throw e;
        }
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
            if (count == 0) {
                throw new IllegalArgumentException("zero");
            }
            shiftDownWord(uLen, u, 0);
        }
        return 32 * (u.length - count);
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

    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }
}
