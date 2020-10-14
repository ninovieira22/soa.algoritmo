import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Algoritmo extends JFrame implements ActionListener {

	static int pagina = 0;
	static int i = 0;
	static int j = 0;
	static int k = 0;
	static int falta = 0;
	static int teste1 = 0;
	static int teste2 = 0;

	static Random aleatorio = new Random();

	JPanel P1;
	JLabel L1, L2, L3, L4, L5;
	JButton B1;
	JTextField T1, T2, T3, T4;
	JTextArea CaixaDeTexto = new JTextArea();
	JScrollPane BarraRolagem = new JScrollPane(CaixaDeTexto);

	public Algoritmo() {

		// Cria��o do Frame
		setTitle("Algoritmo");
		setResizable(false);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 16));

		// Cria��o dos componentes;
		L1 = new JLabel("Algoritmo de Substitui��o de P�ginas - FIFO");
		L1.setBounds(250, 5, 700, 50);
		L1.setFont(new Font("Calibri", Font.BOLD, 25));
		L1.setForeground(new Color(66, 64, 64));

		L2 = new JLabel("Faltas de P�ginas:");
		L2.setBounds(620, 150, 250, 400);
		L2.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		L2.setForeground(new Color(66, 64, 64));

		L3 = new JLabel("Tamanho da Mem�ria:");
		L3.setBounds(620, -80, 250, 490);
		L3.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		L3.setForeground(new Color(66, 64, 64));

		L4 = new JLabel("Quantidade de P�ginas:");
		L4.setBounds(620, -30, 250, 490);
		L4.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		L4.setForeground(new Color(66, 64, 64));

		L5 = new JLabel("Preencha os campos abaixo:");
		L5.setBounds(620, -145, 250, 490);
		L5.setFont(new Font("Calibri", Font.BOLD, 20));
		L5.setForeground(new Color(66, 64, 64));

		T1 = new JTextField("");
		T1.setBounds(860, 150, 60, 30);
		T1.setFont(new Font("Calibri", Font.BOLD, 20));
		T1.setForeground(new Color(66, 64, 64));

		T3 = new JTextField("");
		T3.setBounds(860, 200, 60, 30);
		T3.setFont(new Font("Calibri", Font.BOLD, 20));
		T3.setForeground(new Color(66, 64, 64));

		T4 = new JTextField("");
		T4.setBounds(860, 335, 60, 30);
		T4.setFont(new Font("Calibri", Font.BOLD, 20));
		T4.setForeground(new Color(66, 64, 64));

		P1 = new JPanel();
		P1.setLayout(null);
		P1.setBorder(new LineBorder(new Color(162, 223, 253)));
		P1.setBackground(new Color(162, 223, 253));
		P1.setBounds(0, 10, 1000, 50);
		P1.add(L1);

		B1 = new JButton("Executar");
		B1.setLocation(620, 265);
		B1.setSize(300, 40);
		B1.setFont(new Font("Calibri", Font.BOLD, 17));
		B1.addActionListener(this);
		B1.setBackground(new Color(108, 205, 67));
		B1.setBorder(BorderFactory.createLineBorder(new Color(66, 64, 64)));

		CaixaDeTexto.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		CaixaDeTexto.disable();

		BarraRolagem.setBounds(50, 75, 500, 350);
		BarraRolagem.setBorder(BorderFactory.createLineBorder(new Color(66, 64, 64)));
		BarraRolagem.setVisible(true);

		// Inclus�o dos componentes no Frame
		getContentPane().add(BarraRolagem);
		getContentPane().add(P1);
		getContentPane().add(B1);
		getContentPane().add(L2);
		getContentPane().add(L3);
		getContentPane().add(L4);
		getContentPane().add(L5);
		getContentPane().add(T1);
		getContentPane().add(T3);
		getContentPane().add(T4);
	}

	public static void main(String arg[]) {
		new Algoritmo().setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		// A��o caso o bot�o "Executar" seja selecionado
		if (e.getSource() == B1) {

			try {
				// Verifica se o campo de indica��o do tamanho da mem�ria est� vazio
				if (T1.getText() == null || T1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "O tamanho da mem�ria n�o pode ser nulo!");
					CaixaDeTexto.setText(null);
					T4.setText("");

				// Verifica se o campo de indica��o da quantidade de p�ginas
				} else if (T3.getText() == null || T3.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "A quantidade de p�ginas n�o pode ser nula!");
					CaixaDeTexto.setText(null);
					T4.setText("");
				}

				// Captura o valor indicado nos campos de tamanho da mem�ria e quantidade de
				// p�ginas
				teste1 = Integer.parseInt(T1.getText());
				teste2 = Integer.parseInt(T3.getText());

				// Verifica se o tamanho da mem�ria � superior � quantidade de p�ginas
				if (teste2 < teste1) {
					JOptionPane.showMessageDialog(null, "A mem�ria deve ser menor do que a quantidade de p�ginas!");
					CaixaDeTexto.setText(null);
					T4.setText("");

				// Verifica se o tamanho da mem�ria � inferior ou igual � quantidade de p�ginas.
				} else if (teste2 >= teste1) {

					// Habilita a visualiza��o do componente onde os resultados ser�o impressos
					CaixaDeTexto.enable(true);
					CaixaDeTexto.setText(null);

					// Inicia contador de falta de p�ginas com valor zero
					falta = 0;

					// Inicia o vetor de acordo com a quantidade de endere�os de mem�ria indicada
					// pelo usu�rio
					int z = 0;
					z = Integer.parseInt(T1.getText());
					int memoria[] = new int[z];

					// Inicia o vetor de compara��o de acordo com a quantidade de p�ginas indicada
					// pelo usu�rio
					int y = 0;
					y = Integer.parseInt(T3.getText());
					int compare[] = new int[y];

					// Imprime mensagem indicando o in�cio da execu��o do programa
					CaixaDeTexto.append("Mem�ria no in�cio da execu��o:\n\n");

					// Imprime o vetor de mem�ria indicando que h� apenas dados nulos, visto que
					// ainda n�o foi inserida nenhuma p�gina
					for (i = 0; i < memoria.length; i++) {
						CaixaDeTexto.append((i + 1) + "� Espa�o de Mem�ria: [" + "null" + "]\n");
					}

					// Gera p�ginas de acordo com a quantidade indicada pelo usu�rio. Em seguida
					// imprime a p�gina gerada.
					for (j = 0; j < compare.length; j++) {
						pagina = aleatorio.nextInt(compare.length) + 1;
						CaixaDeTexto.append("\n--> " + (j + 1) + "� P�gina Gerada: [" + pagina + "]\n");

						// Compara a p�gina gerada com as p�ginas j� inseridas anteriormente na mem�ria
						for (i = 0; i < memoria.length; i++) {

							// Se a pagina ainda n�o est� no endere�o que o �ndice indica, imprime a p�gina
							// que est� no �ndice e indica que n�o est� naquele endere�o
							if (pagina != memoria[i]) {

								// Vetor compare recebe as p�ginas em ordem caso haja futura necessidade de
								// substitui��o
								compare[i] = memoria[i];
								CaixaDeTexto.append(
										(i + 1) + "� Espa�o de Mem�ria [" + memoria[i] + "] - N�o existe na Mem�ria\n");

							// Se a pagina j� est� no endere�o que o �ndice indica:
							} else {

								// Vari�vel compare recebe valor -1 para compara��o futura
								compare[0] = -1;

								// imprime a p�gina que est� no �ndice e indica que j� est� naquele endere�o
								CaixaDeTexto.append(
										(i + 1) + "� Espa�o de Mem�ria [" + memoria[i] + "] - J� existe na mem�ria\n");

								// Indica que n�o houve falta de p�gina e termina a busca na mem�ria
								CaixaDeTexto.append("---- N�o houve falta de p�gina -----\n");
								break;
							}
						}

						// Verifica se o valor de compare � diferente de -1. Se sim, indica que houve
						// falta de p�gina
						if (compare[0] != -1) {

							// Enrede�o de mem�ria mais antigo recebe a p�gina que estava faltando
							memoria[k] = pagina;

							// Imprime mensagem indicando que houve falta de p�gina
							CaixaDeTexto.append("---- Houve uma falta de p�gina -----\n");

							// Contador de faltas de p�ginas acresce +1
							falta++;

							// Vari�vel k (respons�vel por verificar qual a p�gina mais antiga) acresce +1
							k++;

							// Caso a vari�vel alcance o tamanho da mem�ria, retorna o �ndice para a p�gina
							// inicial (pois ser� a mais antiga)
							if (k == memoria.length)
								k = 0;
						}
					}
					// Define a vari�vel k como 0 caso o usu�rio deseje executar o programa
					// novamente
					k = 0;

					// Imprime a mensagem de que o programa chegou ao fim de sua execu��o
					CaixaDeTexto.append("\nFim\n\n");

					// Verifica e imprime os valores dos endere�os de mem�rias ap�s todas as
					// substitui��es de p�ginas que ocorreram no programa
					for (i = 0; i < memoria.length; i++) {
						CaixaDeTexto.append((i + 1) + "� Espa�o de Mem�ria: [" + memoria[i] + "]\n");
					}

					// Imprime e indica no frame qual foi a quantidade total de falta de p�ginas
					CaixaDeTexto.append("\nTotal de Faltas de P�ginas: [" + falta + "]");
					T4.setText(Integer.toString(falta));
				}
			}
			// Captura exce��es que n�o tenham sido tratadas acima para que o programa n�o
			// feche ou trave sua execu��o
			catch (Throwable thr) {
			}
		}
	}
}
