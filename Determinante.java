import java.util.Scanner;

public class Determinante {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("1. 2x2");
        System.out.println("2. 3x3");
        System.out.println("3. 4x4");
        System.out.println("==============================");
        System.out.printf("Escolha o tamanho da matriz:");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
            
            System.out.println("==============================");
            System.out.println("1. Calcular a Determinante");
            System.out.println("2. Fazer Adição");
            System.out.println("3. Fazer Subtração");
            System.out.println("4. Fazer Multiplicação");
            System.out.println("5. Fazer Divisão");
            System.out.println("==============================");
            System.out.printf("Escolha uma função:");
            int ç = scanner.nextInt();

            if (ç == 1) {
                double[][] matrix2x2 = new double[2][2];
                System.out.println("");
                System.out.println("Digite os elementos da matriz 2x2:");

                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            System.out.printf("Digite um numero: ");
                            matrix2x2[i][j] = scanner.nextDouble();
                        }
                    } 
                System.out.println("Determinante: " + calculateDeterminant2x2(matrix2x2));
                break;
            }
            
                
            case 2:
                double[][] matrix3x3 = new double[3][3];
                System.out.println("");
                System.out.println("Digite os elementos da matriz 3x3:");
                

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.printf("Digite um numero: ");
                        matrix3x3[i][j] = scanner.nextDouble();
                    }
                }
                System.out.println("Determinante: " + calculateDeterminant3x3(matrix3x3));
                break;
            case 3:
                double[][] matrix4x4 = new double[4][4];
                System.out.println("");
                System.out.println("Digite os elementos da matriz 4x4:");
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.printf("Digite um numero: ");
                        matrix4x4[i][j] = scanner.nextDouble();
                    }
                }
                System.out.println("Determinante: " + calculateDeterminant4x4(matrix4x4));
                break;
            default:
                System.out.println("Opção inválida.");
        }
        scanner.close();
    }

    public static double calculateDeterminant2x2(double[][] matrix) {
        return matrix[0][0] * matrix[0][1] - matrix[1][0] * matrix[1][1];
    }

    public static double calculateDeterminant3x3(double[][] matrix) {
        double determinant = 0;
        for (int i = 0; i < 3; i++) {
            determinant += matrix[0][i] * (matrix[1][(i+1)%3] * matrix[2][(i+2)%3] - matrix[1][(i+2)%3] * matrix[2][(i+1)%3]);
        }
        return determinant;
    }

    public static double calculateDeterminant4x4(double[][] matrix) {
        double determinant = 0;
        for (int i = 0; i < 4; i++) {
            determinant += matrix[0][i] * cofactor(matrix, 0, i);
        }
        return determinant;
    }

    private static double cofactor(double[][] matrix, int row, int col) {
        double[][] minor = new double[3][3];
        for (int i = 0, m = 0; i < 4; i++) {
            if (i == row) continue;
            for (int j = 0, n = 0; j < 4; j++) {
                if (j == col) continue;
                minor[m][n] = matrix[i][j];
                n++;
            }
            m++;
        }
        return ((row + col) % 2 == 0 ? 1 : -1) * calculateDeterminant3x3(minor);
    }
}
