package com.github.h90871424.hello;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadmeTest {

    @Test
    void test() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("README.md"));
        List<String> subList = lines.subList(lines.indexOf("--- | ---") + 1, lines.size());
        assertEquals(99, subList.size());
        for (int i = 0; i < subList.size(); i++) {
            String line = subList.get(i);
            List<BigInteger> split = getTokens(line);
            BigInteger product = FactorsTest.product(split);
            assertEquals(TEN.pow(i + 1).subtract(ONE), product);
            System.out.println(product);
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
