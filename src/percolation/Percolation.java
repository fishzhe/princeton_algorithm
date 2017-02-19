package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by zheyu on 10/5/16.
 */

public class Percolation {

    private boolean[][] sites;
    private int top;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF quickUnionUF;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        sites = new boolean[n][n];
        top = 0;
        bottom = n * n + 1;
        quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int i, int j) {
        sites[i - 1][j - 1] = true;

        int index = getQuickUnionIndex(i, j);
        if (i == 1) {
            quickUnionUF.union(index, top);
        }

        if (i == size) {
            quickUnionUF.union(index, bottom);
        }

        if (i > 1 && isOpen(i - 1, j)) {
            quickUnionUF.union(index, getQuickUnionIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            quickUnionUF.union(index, getQuickUnionIndex(i + 1, j));
        }
        if (j > 1 && isOpen(i, j - 1)) {
            quickUnionUF.union(index, getQuickUnionIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            quickUnionUF.union(index, getQuickUnionIndex(i, j + 1));
        }

    }

    public boolean isOpen(int i, int j) {
        return sites[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        if (i > 0 && i <= size && j > 0 && j <= size) {
            return quickUnionUF.connected(top, getQuickUnionIndex(i, j));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean percolates() {
        return quickUnionUF.connected(top, bottom);
    }

    private int getQuickUnionIndex(int i, int j) {
        return size * (i - 1) + j;
    }
}
