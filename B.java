import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) {
        QReaderB input = new QReaderB();
        QWriterB output = new QWriterB();

        int n = input.nextInt();
        int k = input.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }

        long[] pre = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] ^ array[i - 1];
        }

        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (i > 0 || j > 0) {
                    dp[i][j] = Long.MIN_VALUE;
                }
            }
        }


        for (int j = 1; j < k + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                for (int p = 1; p <= i; p++) {
                    dp[i][j] = Math.max(dp[i][j], dp[p - 1][j - 1] + (pre[i] ^ pre[p - 1]));
                }
            }
        }
        output.println(dp[n][k]);
        output.close();
    }

}
class QReaderB {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}

class QWriterB implements AutoCloseable {
    private StringBuilder output = new StringBuilder();

    public void print(Object object) {
        output.append(object);
    }

    public void println(Object object) {
        output.append(object).append("\n");
    }

    public void close() {
        System.out.print(output);
    }
}