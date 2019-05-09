package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

import javax.xml.stream.events.StartDocument;

public class EffectWithFocusShot {
    public EffectWithFocusShot() {
    }
    public class Bubble {

        // This class should not be instantiated.
        private Bubble() { }

        /**
         * Rearranges the array in ascending order, using the natural order.
         * @param a the array to be sorted
         */
        public <Key extends Comparable<Key>> void sort(Key[] a) {
            int n = a.length;
            for (int i = 0; i < n; i++) {
                int exchanges = 0;
                for (int j = n-1; j > i; j--) {
                    if (less(a[j], a[j-1])) {
                        exch(a, j, j-1);
                        exchanges++;
                    }
                }
                if (exchanges == 0) break;
            }
        }

        // is v < w ?
        private <Key extends Comparable<Key>> boolean less(Key v, Key w) {
            return v.compareTo(w) < 0;
        }

        // exchange a[i] and a[j]
        private  <Key extends Comparable<Key>> void exch(Key[] a, int i, int j) {
            Key swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

        // print array to standard output
        private void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
        }


    }

    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */



    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }



    // exchange a[i] and a[j]
    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/

    // is the array a[] sorted?
    private boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    public class Quick {

        // This class should not be instantiated.
        private Quick() { }

        /**
         * Rearranges the array in ascending order, using the natural order.
         * @param a the array to be sorted
         */
        public void sort(Comparable[] a) {
            sort(a, 0, a.length - 1);
            assert isSorted(a);
        }

        // quicksort the subarray from a[lo] to a[hi]
        private void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return;
            int j = partition(a, lo, hi);
            sort(a, lo, j-1);
            sort(a, j+1, hi);
            assert isSorted(a, lo, hi);
        }

        // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
        // and return the index j.
        private int partition(Comparable[] a, int lo, int hi) {
            int i = lo;
            int j = hi + 1;
            Comparable v = a[lo];
            while (true) {

                // find item on lo to swap
                while (less(a[++i], v)) {
                    if (i == hi) break;
                }

                // find item on hi to swap
                while (less(v, a[--j])) {
                    if (j == lo) break;      // redundant since a[lo] acts as sentinel
                }

                // check if pointers cross
                if (i >= j) break;

                exch(a, i, j);
            }

            // put partitioning item v at a[j]
            exch(a, lo, j);

            // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
            return j;
        }

        /**
         * Rearranges the array so that {@code a[k]} contains the kth smallest key;
         * {@code a[0]} through {@code a[k-1]} are less than (or equal to) {@code a[k]}; and
         * {@code a[k+1]} through {@code a[n-1]} are greater than (or equal to) {@code a[k]}.
         *
         * @param  a the array
         * @param  k the rank of the key
         * @return the key of rank {@code k}
         * @throws IllegalArgumentException unless {@code 0 <= k < a.length}
         */
        public Comparable select(Comparable[] a, int k) {
            if (k < 0 || k >= a.length) {
                throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
            }
            int lo = 0, hi = a.length - 1;
            while (hi > lo) {
                int i = partition(a, lo, hi);
                if      (i > k) hi = i - 1;
                else if (i < k) lo = i + 1;
                else return a[i];
            }
            return a[lo];
        }



        /***************************************************************************
         *  Helper sorting functions.
         ***************************************************************************/

        // is v < w ?
        private boolean less(Comparable v, Comparable w) {
            if (v == w) return false;   // optimization when reference equals
            return v.compareTo(w) < 0;
        }

        // exchange a[i] and a[j]
        private void exch(Object[] a, int i, int j) {
            Object swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }


        /***************************************************************************
         *  Check if array is sorted - useful for debugging.
         ***************************************************************************/
        private boolean isSorted(Comparable[] a) {
            return isSorted(a, 0, a.length - 1);
        }

        private boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++)
                if (less(a[i], a[i-1])) return false;
            return true;
        }




    }
    public class Merge {

        // This class should not be instantiated.
        private Merge() { }

        // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
        private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
            assert isSorted(a, lo, mid);
            assert isSorted(a, mid+1, hi);

            // copy to aux[]
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }

            // merge back to a[]
            int i = lo, j = mid+1;
            for (int k = lo; k <= hi; k++) {
                if      (i > mid)              a[k] = aux[j++];
                else if (j > hi)               a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else                           a[k] = aux[i++];
            }

            // postcondition: a[lo .. hi] is sorted
            assert isSorted(a, lo, hi);
        }

        // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
        private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            merge(a, aux, lo, mid, hi);
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         * @param a the array to be sorted
         */
        public void sort(Comparable[] a) {
            Comparable[] aux = new Comparable[a.length];
            sort(a, aux, 0, a.length-1);
            assert isSorted(a);
        }


        /***************************************************************************
         *  Helper sorting function.
         ***************************************************************************/

        // is v < w ?
        private boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        /***************************************************************************
         *  Check if array is sorted - useful for debugging.
         ***************************************************************************/
        private boolean isSorted(Comparable[] a) {
            return isSorted(a, 0, a.length - 1);
        }

        private boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++)
                if (less(a[i], a[i-1])) return false;
            return true;
        }


        /***************************************************************************
         *  Index mergesort.
         ***************************************************************************/
        // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
        private void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

            // copy to aux[]
            for (int k = lo; k <= hi; k++) {
                aux[k] = index[k];
            }

            // merge back to a[]
            int i = lo, j = mid+1;
            for (int k = lo; k <= hi; k++) {
                if      (i > mid)                    index[k] = aux[j++];
                else if (j > hi)                     index[k] = aux[i++];
                else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
                else                                 index[k] = aux[i++];
            }
        }

        /**
         * Returns a permutation that gives the elements in the array in ascending order.
         * @param a the array
         * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
         *    ..., {@code a[p[N-1]]} are in ascending order
         */
        public int[] indexSort(Comparable[] a) {
            int n = a.length;
            int[] index = new int[n];
            for (int i = 0; i < n; i++)
                index[i] = i;

            int[] aux = new int[n];
            sort(a, index, aux, 0, n-1);
            return index;
        }

        // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
        private void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, index, aux, lo, mid);
            sort(a, index, aux, mid + 1, hi);
            merge(a, index, aux, lo, mid, hi);
        }


    }




}
