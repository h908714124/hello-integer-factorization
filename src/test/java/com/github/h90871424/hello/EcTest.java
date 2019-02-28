package com.github.h90871424.hello;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class EcTest {

    private BigInteger n = new BigInteger("455839");
    private int bits = n.bitLength();

    private BigInteger a = new BigInteger("5");
    private BigInteger b = new BigInteger("-5");
    private ECCurve curve = new ECCurve.Fp(n, a, b.mod(n), null, null);
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
        System.out.println(badPoint);
        System.out.println(new BigInteger("63f85", 16));
        System.out.println(badPoint.getXCoord());
        System.out.println(badPoint.getRawXCoord());
        System.out.println(badPoint.normalize());
        modInverse(BigInteger.valueOf(165923));
    }

    private BigInteger modInverse(BigInteger x)
    {
        int len = (bits + 31) >> 5;
        int[] p = Nat.fromBigInteger(bits, n);
        int[] n = Nat.fromBigInteger(bits, x);
        int[] z = Nat.create(len);
        Mod.invert(p, n, z);
        return Nat.toBigInteger(len, z);
    }


    private BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
