package com.github.h90871424.hello;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadmeTest {

    @Test
    void test() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("README.md"));
        int index = lines.indexOf("--- | ---") + 1;
        List<String> subList = lines.subList(index, index + 110);
        Set<BigInteger> alle = new TreeSet<>();
        for (int i = 0; i < subList.size(); i++) {
            String line = subList.get(i);
            List<BigInteger> split = getTokens(line);
            alle.addAll(split);
            BigInteger product = FactorsTest.product(split);
            assertEquals(TEN.pow(i + 1).subtract(ONE), product);
            System.out.println(product);
        }
        for (BigInteger bigInteger : alle) {
            System.out.println(bigInteger);
        }
    }

    private static List<BigInteger> getTokens(String line) {
        String[] tokens = line.split("[|]", 2);
        tokens = tokens[1].split("[,]", -1);
        List<BigInteger> result = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            token = token.replace("*", "");
            result.add(new BigInteger(token.trim()));
        }
        return result;
    }
}
