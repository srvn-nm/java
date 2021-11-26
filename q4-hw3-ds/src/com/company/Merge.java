

import java.util.*;

    class Merge {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            ArrayList<Integer[]> list = new ArrayList<>();
            for (int i = 0; i < number; i++){
                Scanner in2 = new Scanner(System.in);
                String data = in2.nextLine();
                String[] tempDataArray = data.split(" ");
                Integer[] arr = new Integer[tempDataArray.length];
                for(int j=0; j<tempDataArray.length; j++) {
                    arr[j] = Integer.parseInt(tempDataArray[j]);
                }
                list.add(arr);
            }
            int totalSize = 0;
            for (int i = 0; i < number; i++) {
                totalSize += list.get(i).length;
            }
            Integer[] T = new Integer[totalSize];
            int pos = 0;
                for (int j = 0; j < number; j++) {
                        for (int element : list.get(j)){
                            T[pos] = element;
                            pos++;
                        }
                }
            for (int i = 0; i < T.length; i++) {
                for (int j = i + 1; j < T.length; j++) {
                    int tmp;
                    if (T[i] > T[j]) {
                        tmp = T[i];
                        T[i] = T[j];
                        T[j] = tmp;
                    }
                }
            }
            for (int i = 0; i < totalSize; i++) {
                System.out.print(T[i] + " ");
            }
        }
    }