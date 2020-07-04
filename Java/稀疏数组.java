
public class HairArray {
    public static void main(String[] args) {
        int[][] data = new int[11][11];
        data[1][2] = 1;
        data[2][3] = 2;
        data[2][8] = 2;
        System.out.println("原始数组......");
        showArray(data);

        int[][] hair = HairArray.rollIn(data);
        System.out.println("稀疏数组......");
        showArray(hair);

        int[][] back = HairArray.rollBack(hair);
        System.out.println("恢复数组......");
        showArray(back);
    }

    //写成稀疏数组
    public static int[][] rollIn(int[][] datas) {
        //先遍历二维数组，得到非0数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (datas[i][j] != 0)
                    sum++;
            }
        }
        int[][] hair = new int[sum + 1][3];
        hair[0][0] = datas.length;
        hair[0][1] = datas[1].length;
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (datas[i][j] != 0) {
                    count++;
                    hair[count][0] = i;
                    hair[count][1] = j;
                    hair[count][2] = datas[i][j];
                }
            }
        }
        hair[0][2] = count;
        return hair;
    }

    //恢复数组
    private static int[][] rollBack(int[][] hair) {
        int[][] rollback = new int[hair[0][0]][hair[0][1]];
        for (int i = 1; i < hair.length; i++) {
            rollback[hair[i][0]][hair[i][1]] = hair[i][2];
        }
        return rollback;
    }


    //打印数组
    public static void showArray(int[][] array) {
        for (int[] row : array) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.print("\n");
        }
    }
}
