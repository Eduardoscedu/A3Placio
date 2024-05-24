import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maincopy {
    private static JTextField[][] inputFields2x2 = new JTextField[2][2];
    private static JTextField[][] inputFields3x3 = new JTextField[3][3];
    private static JTextField[][] inputFields4x4 = new JTextField[4][4];
    private static JComboBox<String> matrixTypeComboBox;
    private static JTextArea resultArea;

    public static double determinanteMatriz2x2(double[][] matriz) {
        return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
    }

    public static double determinanteMatriz3x3(double[][] matriz) {
        double determinante = 0;
        for (int i = 0; i < 3; i++) {
            determinante += matriz[0][i] * (matriz[1][(i + 1) % 3] * matriz[2][(i + 2) % 3] - matriz[1][(i + 2) % 3] * matriz[2][(i + 1) % 3]);
        }
        return determinante;
    }

    public static double determinanteMatriz4x4(double[][] matriz) {
        double determinante = 0;
        for (int i = 0; i < 4; i++) {
            determinante += matriz[0][i] * matriz[1][(i + 1) % 4] * matriz[2][(i + 2) % 4] * matriz[3][(i + 3) % 4]
                    - matriz[0][(i + 3) % 4] * matriz[1][(i + 2) % 4] * matriz[2][(i + 1) % 4] * matriz[3][i];
        }
        return determinante;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora de Matriz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Painel de seleção
        JPanel topPanel = new JPanel();
        matrixTypeComboBox = new JComboBox<>(new String[]{"2x2", "3x3", "4x4"});
        JButton calculateButton = new JButton("Calcular Determinante");
        JButton inversaButton = new JButton("Calcular Inversa");
        
        inversaButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularInversa();
            }
        });
        
        calculateButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularDeterminante();
            }
        });
        topPanel.add(new JLabel("Tipo de Matriz:"));
        topPanel.add(matrixTypeComboBox);
        topPanel.add(calculateButton);
        topPanel.add(inversaButton);


        // Painel de entrada
        JPanel inputPanel = new JPanel(new CardLayout());
        inputPanel.add(createInputPanel(2, inputFields2x2), "2x2");
        inputPanel.add(createInputPanel(3, inputFields3x3), "3x3");
        inputPanel.add(createInputPanel(4, inputFields4x4), "4x4");

        matrixTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (inputPanel.getLayout());
                cl.show(inputPanel, (String) matrixTypeComboBox.getSelectedItem());
            }
        });

        // Área de resultado
        resultArea = new JTextArea(5, 40);
        resultArea.setEditable(false);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JPanel createInputPanel(int size, JTextField[][] inputFields) {
        JPanel panel = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inputFields[i][j] = new JTextField(5);
                panel.add(inputFields[i][j]);
            }
        }
        return panel;
    }

    private static void calcularInversa(){
       //Arranja um meio de calcular a inversa
    }

    private static void calcularDeterminante() {
        String selectedMatrixType = (String) matrixTypeComboBox.getSelectedItem();
        double determinante = 0;

        if (selectedMatrixType.equals("2x2")) {
            double[][] matriz2x2 = getInputValues(inputFields2x2, 2);
            determinante = determinanteMatriz2x2(matriz2x2);

            matriz2x2[0][0] = determinante / matriz2x2[0][0];
            matriz2x2[0][1] = determinante / matriz2x2[0][1];
            matriz2x2[1][0] = determinante / matriz2x2[1][0];
            matriz2x2[1][1] = determinante / matriz2x2[1][1];
            
        } else if (selectedMatrixType.equals("3x3")) {
            double[][] matriz3x3 = getInputValues(inputFields3x3, 3);
            determinante = determinanteMatriz3x3(matriz3x3);
        } else if (selectedMatrixType.equals("4x4")) {
            double[][] matriz4x4 = getInputValues(inputFields4x4, 4);
            determinante = determinanteMatriz4x4(matriz4x4);
        }

        resultArea.setText("A inversa da matriz " + selectedMatrixType + " é: " + determinante);
    }

    private static double[][] getInputValues(JTextField[][] inputFields, int size) {
        double[][] values = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    values[i][j] = Double.parseDouble(inputFields[i][j].getText());
                } catch (NumberFormatException e) {
                    values[i][j] = 0;
                }
            }
        }
        return values;
    }
}
