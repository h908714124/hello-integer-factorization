package com.github.h90871424.hello;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadmeTest {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("README.md"));
        int i = lines.indexOf("--- | ---");
        for (String line : lines.subList(i + 1, lines.size())) {
            List<BigInteger> split = getTokens(line);
            System.out.println(FactorsTest.product(split));
        }
    }

    private static List<BigInteger> getTokens(String line) {
        String[] tokens = line.split("[|]", 2);
        tokens = tokens[1].split("[,]", -1);
        List<BigInteger> result = new ArrayList<>(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.endsWith("*")) {
                token = token.substring(0, token.length() - 1);
            }
            result.add(new BigInteger(token.trim()));
        }
        return result;
    }
}
