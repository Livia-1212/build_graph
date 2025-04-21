
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads a user-defined directed graph, computes G^k for k=1..N, and the transitive closure G+.
 */
public class TransitiveClosure {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Prompt user for number of nodes (N)
        System.out.print("Enter number of nodes N: ");
        int n = sc.nextInt();
        while (n <= 0) {
            System.out.print("N must be positive. Enter number of nodes N: ");
            n = sc.nextInt();
        }

        // 2. Prompt user for number of edges (K)
        System.out.print("Enter number of edges K: ");
        int K = sc.nextInt();
        while (K < 0) {
            System.out.print("K cannot be negative. Enter number of edges K: ");
            K = sc.nextInt();
        }

        // 3. Read edges and build adjacency matrix G
        boolean[][] G = new boolean[n][n];
        System.out.println("Enter each edge as two integers (u v) between 1 and " + n + ":");
        for (int e = 0; e < K; e++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (u < 1 || u > n || v < 1 || v > n) {
                System.out.println("Invalid edge (" + u + "," + v + "). Please enter values between 1 and " + n + ".");
                e--; // retry this edge
            } else {
                G[u - 1][v - 1] = true;
            }
        }
        sc.close();

        // 4. Initialize current = G^1 and Gplus = G^1
        boolean[][] current = copy(G);
        boolean[][] Gplus = copy(G);

        // 5. For k = 1..N, compute and display G^k, and build Gplus
        for (int k = 1; k <= n; k++) {
            System.out.println("\nG^" + k + " (paths of length " + k + "):");
            print(current);

            if (k == n) {
                // At longest path length, stop extending
                break;
            }
            // Compute next power: G^{k+1} = G^k boolean-multiply G
            current = booleanMatMul(current, G);
            // Merge into transitive closure
            orInto(Gplus, current);
        }

        // 6. Display final transitive closure
        System.out.println("\n=== Transitive closure G+ ===");
        print(Gplus);
    }

    /** Boolean matrix multiplication: C = A boolean-multiply B */
    private static boolean[][] booleanMatMul(boolean[][] A, boolean[][] B) {
        int n = A.length;
        boolean[][] C = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean reachable = false;
                for (int h = 0; h < n; h++) {
                    if (A[i][h] && B[h][j]) {
                        reachable = true;
                        break;
                    }
                }
                C[i][j] = reachable;
            }
        }
        return C;
    }

    /** ORs src into dest: dest[i][j] |= src[i][j] */
    private static void orInto(boolean[][] dest, boolean[][] src) {
        int n = dest.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dest[i][j] |= src[i][j];
            }
        }
    }

    /** Deep-copy a 2D boolean array */
    private static boolean[][] copy(boolean[][] m) {
        return Arrays.stream(m)
                .map(row -> row.clone())
                .toArray(boolean[][]::new);
    }

    /** Print a 2D boolean matrix as 1s and 0s */
    private static void print(boolean[][] m) {
        for (boolean[] row : m) {
            for (boolean cell : row) {
                System.out.print(cell ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}
