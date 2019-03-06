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
        for (long j = 0; j < 100000000; j++) {
            boolean print = j % 16777216 == 0;
            if (print) {
                System.out.printf("j=%d%n", j);
            }
            EllipticCurve curve = EllipticCurve.randomCurve(n);
            Optional<TonelliShanks.XSolution> solution = TonelliShanks.findPoint(n, curve.getA(), curve.getB());
            if (!solution.isPresent()) {
                if (print) {
                    System.out.println("no solution");
                }
                continue;
            }
            ECCurve ecCurve = new ECCurve.Fp(n, curve.getA(), curve.getB(), null, null);
            System.err.printf("Solution: x=%s, y=%s%n", solution.get().getX(), solution.get().getY());
            ECPoint p = ecCurve.validatePoint(solution.get().getX(), solution.get().getY());
            System.err.printf("Testing curve %d, a=%s, b=%s, point: (%s, %s)%n", j, curve.getA(), curve.getB(), solution.get().getX(), solution.get().getY());

            for (int i = 2; i < 100000; i++) {
                p = curve.multiply(p, BigInteger.valueOf(i));
                curve.check(p);
            }
            System.err.println("Curve " + j + " done");
        }
    }
}
