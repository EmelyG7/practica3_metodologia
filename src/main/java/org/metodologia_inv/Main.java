package org.metodologia_inv;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class Main {
        private static final int NUM_TESTS = 10;
        private static final int[] SIZES = {1000, 10000, 100000, 1000000};
        private static final Random RANDOM = new Random();

        public static void main(String[] args) {
            for (int size : SIZES) {
                System.out.println("Probando las listas con el tamano: " + size + "\n");
                testListPerformance(new ArrayList<>(), size);
                testListPerformance(new LinkedList<>(), size);
                System.out.println();
            }
        }

        private static void testListPerformance(List<Integer> list, int size) {
            System.out.println("Probando " + list.getClass().getSimpleName());


            for (int i=0; i<NUM_TESTS; i++) {
                list.clear();
                List<Integer> datos = generateRandomList(size);

                System.out.println("Prueba " + (i + 1) + "/" + NUM_TESTS + "\n");

                // Insert elements
                long insertTime = measureTime(() -> {
                        list.addAll(datos);

                });
                System.out.println("Insert time: " + insertTime + " ns\n");

                // Access elements
                long accessTime = measureTime(() -> {
                    for (int j = 0; j < size; j++) {
                        list.get(j);
                    }
                });
                System.out.println("Access time: " + accessTime + " ns\n");

                // Iterate elements
                long iterateTime = measureTime(() -> {
                    for (Integer integer : list) {
                        // no-op
                    }
                });
                System.out.println("Iteration time: " + iterateTime + " ns\n");

                // Remove elements
                long removeTime = measureTime(() -> {
                    for (int j = size - 1; j >= 0; j--) {
                        list.remove(j);
                    }
                });

            System.out.println("Removal time: " + removeTime + " ns\n");

            System.out.println("Prueba " + (i + 1) + "/" + NUM_TESTS + " Completada\n");

            }
        }

    private static List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(RANDOM.nextInt());
        }
        return list;
    }

    private static long measureTime(Runnable task) {
            long startTime = System.nanoTime();
            task.run();
            return System.nanoTime() - startTime;
        }
    }

