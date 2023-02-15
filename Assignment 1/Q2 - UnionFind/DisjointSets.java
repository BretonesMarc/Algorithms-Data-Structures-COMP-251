import java.io.*;
import java.util.*;


/****************************
 *
 * COMP251 template file
 *
 * Assignment 1, Question 2
 *
 *****************************/


public class DisjointSets {

    private int[] par;
    private int[] rank;

    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }

    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find representative of element i */
    public int find(int i) {
        // If the parent of i is i, then i is already the representative of its set, so return i.
        if (par[i] == i) {
            return i;
        }
        // Otherwise, recursively find the representative of the parent of i, and then update the parent
        // of i to be the representative of the set.
        int rep = find(par[i]);
        par[i] = rep; // path compression
        return rep;
    }

    /* Merge sets containing elements i and j */
    public int union(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);
        if (root1 == root2) {
            return root1;
        }
        if (rank[root1] < rank[root2]) {
            par[root1] = root2;
            return root2;
        } else if (rank[root1] > rank[root2]) {
            par[root2] = root1;
            return root1;
        } else {
            par[root1] = root2;
            rank[root2]++;
            return root2;
        }
    }

    public static void main(String[] args) {

        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);

    }

}
