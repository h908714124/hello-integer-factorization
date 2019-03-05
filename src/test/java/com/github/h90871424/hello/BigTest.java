package com.github.h90871424.hello;

import com.github.h90871424.lenstra.EllipticCurve;
import com.github.h90871424.lenstra.TonelliShanks;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Optional;

class BigTest {

    private BigInteger n = new BigInteger("92556179448994367391887834053878562534782033760810527051075248738484727059555245899601591");

    @Test
    void test() {
        for (int j = 0; j < 100; j++) {
            EllipticCurve curve = EllipticCurve.randomCurve(n);
            ECCurve ecCurve = new ECCurve.Fp(n, curve.getA(), curve.getB(), null, null);
            System.out.printf("Testing curve %d, n=%s, a=%s, b=%s%n", j, n, curve.getA(), curve.getB());
            Optional<TonelliShanks.XSolution> solution = TonelliShanks.findPoint(n, curve.getA(), curve.getB());
            if (!solution.isPresent()) {
                System.out.println("no solution");
                continue;
            }
            System.out.printf("Solution: x=%s, y=%s%n", solution.get().getX(), solution.get().getY());
            ECPoint p = ecCurve.validatePoint(solution.get().getX(), solution.get().getY());
            System.out.println("Testing curve " + j + ", point: " + p);

            for (int i = 2; i < 100; i++) {
                p = curve.multiply(p, BigInteger.valueOf(i));
                curve.check(p);
            }
            System.out.println("Curve " + j + " done");
        }
    }
}
