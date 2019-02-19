package com.gazman.factor;

import com.gazman.factor.matrix.BitMatrix;
import com.gazman.factor.matrix.VectorsShrinker;
import com.gazman.factor.wheels.Wheel;
import com.gazman.math.MathUtils;
import com.gazman.math.SqrRoot;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ilya Gazman on 1/27/2016.
 */
public class QuadraticThieve extends Logger {


    private static final int B_SMOOTH = 5000;
    private static final double MINIMUM_LOG = 0.0000001;
    private static final int MAX_LOOPS = B_SMOOTH * 2;
    private static final int LOGS_TIME_BY_LOOPS = B_SMOOTH / 20;

    private final double minimumBigPrimeLog;
    private final int sieveVectorBound;
    private final BigInteger[] primeBase = new BigInteger[B_SMOOTH];
    private final int step;
    private final List<VectorData> bSmoothVectors = new ArrayList<>();
    private final BigInteger N;
    private final BigInteger root;
    private int bSmoothFound;
    private final BigPrimesList bigPrimesList = new BigPrimesList();
    private final VectorsShrinker vectorsShrinker;
    private final double double2Root;
    private final AtomicInteger speedCounter = new AtomicInteger(0);
    private final AtomicInteger speed = new AtomicInteger(0);

    public QuadraticThieve(BigInteger input) {
        N = input;
        root = SqrRoot.bigIntSqRootCeil(input);
        double2Root = root.add(root).doubleValue();

        buildPrimeBase();
        vectorsShrinker = new VectorsShrinker(root, primeBase.length, N);
        BigInteger highestPrime = primeBase[primeBase.length - 1];
        sieveVectorBound = highestPrime.intValue();
        minimumBigPrimeLog = Math.log(highestPrime.pow(2).doubleValue());
        step = sieveVectorBound;
    }

    public BigInteger start() {
        return execute();
    }

    private BigInteger execute() {
        long basePosition = 0;
        while (true) {
            long position = basePosition;
            Wheel[] localWheels = initSieveWheels(position);
            for (int loops = 0; loops < MAX_LOOPS; loops++) {
                double baseLog = calculateBaseLog(position);
                position += step;
                boolean sieve = sieve(position, baseLog, localWheels);
                speed.incrementAndGet();
                if (speedCounter.incrementAndGet() == LOGS_TIME_BY_LOOPS) {
                    speedCounter.set(0);
                    logProcesses();
                }

                if (sieve && isReadyToBeSolved()) {
                    Optional<BigInteger> solution = tryToSolve();
                    if (solution.isPresent()) {
                        return solution.get();
                    }
                }
            }
            basePosition += step * MAX_LOOPS;
        }
    }

    private void logProcesses() {
        log("B-Smooth found", bSmoothFound, "Big primes found", bigPrimesList.getPrimesFound());
    }

    private double calculateBaseLog(double position) {
        return Math.log(position * (position + double2Root));

    }

    private Wheel[] initSieveWheels(long position) {
        Wheel[] wheels = new Wheel[B_SMOOTH];
        for (int i = 0; i < wheels.length; i++) {
            wheels[i] = new Wheel(primeBase[i], N, root.add(BigInteger.valueOf(position)), sieveVectorBound);
        }
        return wheels;
    }

    private boolean sieve(long destination, double baseLog, Wheel[] wheels) {
        boolean vectorsFound = false;
        double[] logs = new double[sieveVectorBound];
        double[] trueLogs = new double[sieveVectorBound];
        VectorData[] vectors = new VectorData[sieveVectorBound];

        for (int i = 0; i < primeBase.length; i++) {
            Wheel wheel = wheels[i];
            wheel.savePosition();
            wheel.prepareToMove();
            while (wheel.testMove()) {
                int index = wheel.move();
                if (index > logs.length) {
                    log(index - sieveVectorBound, logs.length - sieveVectorBound, "error");
                    System.exit(3);
                }
                logs[index] += wheel.log;
            }
            wheel.restorePosition();
        }

        for (int i = primeBase.length - 1; i >= 0; i--) {
            Wheel wheel = wheels[i];
            wheel.prepareToMove();
            while (wheel.testMove()) {
                int index = wheel.move();

                if (trueLogs[index] == 0) {
                    if (baseLog - logs[index] > minimumBigPrimeLog) {
                        continue;
                    }
                    trueLogs[index] = calculateBaseLog(destination + index - sieveVectorBound);
                }

                double reminderLog = trueLogs[index] - logs[index];
                if (reminderLog > minimumBigPrimeLog) {
                    continue;
                }

                boolean bigPrime = reminderLog > MINIMUM_LOG;

                if (vectors[index] == null) {
                    VectorData vectorData = new VectorData(new BitSet(i), index + destination - sieveVectorBound);
                    vectors[index] = vectorData;

                    if (bigPrime) {
                        long prime = Math.round(Math.pow(Math.E, reminderLog));
                        bigPrimesList.add(prime, vectorData);
                    } else {
                        bSmoothVectors.add(vectorData);
                        bSmoothFound++;
                    }
                }
                vectorsFound = true;
                vectors[index].vector.set(i);
            }
        }

        return vectorsFound;
    }

    private Optional<BigInteger> tryToSolve() {
        log("Building matrix");

        List<VectorData> vectorDatas = vectorsShrinker.shrink(bSmoothVectors, bigPrimesList);

        BitMatrix bitMatrix = new BitMatrix();
        List<List<VectorData>> solutions = bitMatrix.solve(vectorDatas);

        for (int i = 0; i < solutions.size(); i++) {
            List<VectorData> solution = solutions.get(i);
            log("Testing solution", (i + 1) + "/" + solutions.size());
            Optional<BigInteger> gcd = testSolution(solution);
            if (gcd.isPresent()) {
                return gcd;
            }
        }
        log("no luck");

        return Optional.empty();
    }

    private boolean isReadyToBeSolved() {
        return bSmoothVectors.size() + bigPrimesList.getPrimesFound() >= B_SMOOTH;
    }

    private Optional<BigInteger> testSolution(List<VectorData> solutionVector) {
        BigInteger y = one;
        BigInteger x = one;

        for (VectorData vectorData : solutionVector) {
            BigInteger savedX, savedY;
            if (vectorData.x != null) {
                savedX = vectorData.x;
                savedY = vectorData.y;
            } else {
                savedX = root.add(BigInteger.valueOf(vectorData.position));
                savedY = savedX.pow(2).subtract(N);
            }
            x = x.multiply(savedX).mod(N);
            y = y.multiply(savedY);
        }

        y = SqrRoot.bigIntSqRootFloor(y);
        BigInteger gcd = N.gcd(x.add(y));
        if (!gcd.equals(one) && !gcd.equals(N)) {
            log("Solved");
            log(gcd);

            return Optional.of(gcd);
        }
        return Optional.empty();
    }

    private void buildPrimeBase() {
        BigInteger prime = BigInteger.ONE;

        for (int i = 0; i < B_SMOOTH; ) {
            prime = prime.nextProbablePrime();
            if (MathUtils.isRootInQuadraticResidues(N, prime)) {
                primeBase[i] = prime;
                i++;
            }
        }
    }
}
