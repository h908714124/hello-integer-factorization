package com.github.h90871424.hello;

import com.github.h90871424.lenstra.EllipticCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class EcTest {

    private BigInteger n = new BigInteger("455839");

    private BigInteger a = new BigInteger("5");
    private BigInteger b = new BigInteger("-5");

    @Test
    void test() {
        EllipticCurve curve = new EllipticCurve(n, a, b);
        ECPoint p = curve.getPoint();
        final ECPoint oldP = curve.getPoint();
        ECPoint newPoint = null;
        for (int i = 2; i < 10; i++) {
            try {
                BigInteger fact = factorial(i);
                ECPoint fromFactorial = oldP.multiply(fact);
                System.out.println(fromFactorial);
                p = curve.multiply(p, BigInteger.valueOf(i));
                curve.check(p);
                curve.check(fromFactorial);
                System.out.println(p);
//                System.out.println(p.equals(fromFactorial));
//                System.out.println();
            } catch (AssertionError e) {
                System.err.println("" + i);
                throw e;
            }
        }
/*
        for (int i = 0; i < 9; i++) {
            try {
                newPoint = p.multiply(factorial(i));
                curve.check(newPoint);
            } catch (AssertionError error) {
                System.out.println("" + newPoint);
            }
        }
        for (int i = 2; i < 9; i++) {
            try {
                p = p.multiply(BigInteger.valueOf(i));
                curve.check(p);
            } catch (AssertionError error) {
                System.out.println("" + p);
            }
        }
*/
    }

    static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
