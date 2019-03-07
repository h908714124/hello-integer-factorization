package com.github.h90871424.hello;

import com.github.h90871424.lenstra.EllipticCurve;
import com.github.h90871424.lenstra.TonelliShanks;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Optional;

class BigTest {

    private BigInteger n = new BigInteger("92556179448994367391887834053878562534782033760810527051075248738484727059555245899601591");

    @Test
    void test() throws IOException {
        long curveCounter = 0;
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("curves.txt")))) {
            for (long j = 0; j < 2147483500; j++) {
                boolean print = j % 16777216 == 0;
                if (print) {
                    System.out.printf("j=%d%n", j);
                }
                EllipticCurve curve = EllipticCurve.randomCurve(n);
                Optional<TonelliShanks.XYSolution> solution = TonelliShanks.findPoint(n, curve.getA(), curve.getB());
                if (!solution.isPresent()) {
                    if (print) {
                        System.out.println("no solution");
                    }
                    continue;
                }
                ECCurve ecCurve = new ECCurve.Fp(n, curve.getA(), curve.getB(), null, null);
                boolean print2 = curveCounter % 2048 == 0;
                if (print2) {
                    System.err.printf("Solution: x=%s, y=%s%n", solution.get().getX(), solution.get().getY());
                }
                ECPoint p = ecCurve.validatePoint(solution.get().getX(), solution.get().getY());
                pw.printf("%s %s %s %s%n",
                        curve.getA(), curve.getB(), solution.get().getX(), solution.get().getY());
                if (print2) {
                    System.err.printf("Testing curve %d, a=%s, b=%s, point=(%s, %s)%n",
                            curveCounter, curve.getA(), curve.getB(), solution.get().getX(), solution.get().getY());
                    pw.flush();
                }

                for (int i = 2; i < 200; i++) {
                    p = curve.multiply(p, BigInteger.valueOf(i));
                    curve.check(p);
                }
                if (print2) {
                    System.err.printf("Curve %d done%n", curveCounter);
                }
                curveCounter++;
            }
        }
    }
}
