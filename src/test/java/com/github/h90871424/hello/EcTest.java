package com.github.h90871424.hello;

import com.github.h90871424.lenstra.EllipticCurve;
import com.github.h90871424.lenstra.TonelliShanks;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class EcTest {

    private BigInteger n = new BigInteger("455839");

    @Test
    void test() {
        for (int j = 0; j < 100; j++) {
            EllipticCurve curve = EllipticCurve.randomCurve(n);
            ECCurve ecCurve = new ECCurve.Fp(n, curve.getA(), curve.getB(), null, null);

            TonelliShanks.XSolution solution = TonelliShanks.findPoint(n, curve.getA(), curve.getB());
            ECPoint p = ecCurve.validatePoint(solution.getX(), solution.getY());

            for (int i = 2; i < 100; i++) {
                p = curve.multiply(p, BigInteger.valueOf(i));
                curve.check(p);
            }
        }
    }
}
