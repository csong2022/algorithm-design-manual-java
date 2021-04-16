package com.algorist.leetcodeadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public static interface BacktrackCallback<S extends Collection,I> {
        /**
         * Test the first k elements of vector a are a complete solution for the given problem.
         *
         * @param a     solution vector.
         * @param input allow pass general information.
         * @return true if the first k elements of vector a are a complete solution, otherwise false.
         */
        boolean isaSolution(S a, int k, I input);

        /**
         * Process a complete solution once it is constructed.
         *
         * @param a     solution vector.
         * @param input general input.
         */
        void processSolution(S a, int k, I input);

        /**
         * Fills an array c with the complete set of possible candidates for the kth position of a,
         * given the contents of the first k - 1 positions.
         *
         * @param a     solution vector.
         * @param input general input.
         * @return the number of candidates.
         */
        int[] constructCandidates(S a, I input);

        /**
         * Make a move based on updated kth position of a.
         *
         * @param a     solution vector.
         * @param input general input.
         */
        void makeMove(S a, int k, I input);

        /**
         * Undo the move based on updated kth position of a.
         *
         * @param a     solution vector.
         * @param input general input.
         */
        void unmakeMove(S a, I input);
    }


    public static class Backtrack<S extends Collection> {
        private boolean finished = false;                  /* found all solutions yet? */

        public <I> void backtrack(S possibleSolution, int k, I input, BacktrackCallback<S,I> callback) {

            if (callback.isaSolution(possibleSolution, k, input))
                callback.processSolution(possibleSolution, k, input);

            else {
                k++;
                int[] candidates = callback.constructCandidates(possibleSolution, input); /* next position candidate count */
                for (int i = 0; i < candidates.length; i++) {
                    callback.makeMove(possibleSolution, i, input);
                    backtrack(possibleSolution, k, input, callback);
                    if (finished) return;    /* terminate early */

                    callback.unmakeMove(possibleSolution, input);
                }
            }
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }
    }
    public static class Subsets implements BacktrackCallback<List<Integer>, int[]> {

        List<List<Integer>> solution = new ArrayList<>();
        @Override
        public void processSolution(List<Integer> possibleSolution, int k, int[] n) {
            List<Integer> in = new ArrayList<>();
            //possibleSolution.add(candidates[k]);
            for(int i=0;i<possibleSolution.size();i++){
                if(possibleSolution.get(i) == 1){
                    in.add(n[i]);
                }
            }
            solution.add(in);
        }

        @Override
        public boolean isaSolution(List<Integer> a, int k, int[] n) {
            return k == n.length;
        }

        @Override
        public void makeMove(List<Integer> a, int i, int[] n) {
            a.add(candidates[i]);

        }

        @Override
        public void unmakeMove(List<Integer> a, int[] n) {
            a.remove(a.size()-1);
        }

        final int[] candidates = {1,0};
        @Override
        public int[] constructCandidates(List<Integer> a, int[] n) {
            return candidates;
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> a = new ArrayList<>();

        Subsets subsets = new Subsets();

        Backtrack backtrack = new Backtrack();
        backtrack.backtrack(a, 0, nums, subsets);
        return subsets.solution;
    }



}