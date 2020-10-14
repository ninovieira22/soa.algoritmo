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

		// Criação do Frame
		setTitle("Algoritmo");
		setResizable(false);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 16));

		// Criação dos componentes;
		L1 = new JLabel("Algoritmo de Substituição de Páginas - FIFO");
		L1.setBounds(250, 5, 700, 50);
		L1.setFont(new Font("Calibri", Font.BOLD, 25));
		L1.setForeground(new Color(66, 64, 64));

		L2 = new JLabel("Faltas de Páginas:");
		L2.setBounds(620, 150, 250, 400);
		L2.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		L2.setForeground(new Color(66, 64, 64));

		L3 = new JLabel("Tamanho da Memória:");
		L3.setBounds(620, -80, 250, 490);
		L3.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		L3.setForeground(new Color(66, 64, 64));

		L4 = new JLabel("Quantidade de Páginas:");
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

		// Inclusão dos componentes no Frame
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

		// Ação caso o botão "Executar" seja selecionado
		if (e.getSource() == B1) {

			try {
				// Verifica se o campo de indicação do tamanho da memória está vazio
				if (T1.getText() == null || T1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "O tamanho da memória não pode ser nulo!");
					CaixaDeTexto.setText(null);
					T4.setText("");

				// Verifica se o campo de indicação da quantidade de páginas
				} else if (T3.getText() == null || T3.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "A quantidade de páginas não pode ser nula!");
					CaixaDeTexto.setText(null);
					T4.setText("");
				}

				// Captura o valor indicado nos campos de tamanho da memória e quantidade de
				// páginas
				teste1 = Integer.parseInt(T1.getText());
				teste2 = Integer.parseInt(T3.getText());

				// Verifica se o tamanho da memória é superior à quantidade de páginas
				if (teste2 < teste1) {
					JOptionPane.showMessageDialog(null, "A memória deve ser menor do que a quantidade de páginas!");
					CaixaDeTexto.setText(null);
					T4.setText("");

				// Verifica se o tamanho da memória é inferior ou igual à quantidade de páginas.
				} else if (teste2 >= teste1) {

					// Habilita a visualização do componente onde os resultados serão impressos
					CaixaDeTexto.enable(true);
					CaixaDeTexto.setText(null);

					// Inicia contador de falta de páginas com valor zero
					falta = 0;

					// Inicia o vetor de acordo com a quantidade de endereços de memória indicada
					// pelo usuário
					int z = 0;
					z = Integer.parseInt(T1.getText());
					int memoria[] = new int[z];

					// Inicia o vetor de comparação de acordo com a quantidade de páginas indicada
					// pelo usuário
					int y = 0;
					y = Integer.parseInt(T3.getText());
					int compare[] = new int[y];

					// Imprime mensagem indicando o início da execução do programa
					CaixaDeTexto.append("Memória no início da execução:\n\n");

					// Imprime o vetor de memória indicando que há apenas dados nulos, visto que
					// ainda não foi inserida nenhuma página
					for (i = 0; i < memoria.length; i++) {
						CaixaDeTexto.append((i + 1) + "º Espaço de Memória: [" + "null" + "]\n");
					}

					// Gera páginas de acordo com a quantidade indicada pelo usuário. Em seguida
					// imprime a página gerada.
					for (j = 0; j < compare.length; j++) {
						pagina = aleatorio.nextInt(compare.length) + 1;
						CaixaDeTexto.append("\n--> " + (j + 1) + "ª Página Gerada: [" + pagina + "]\n");

						// Compara a página gerada com as páginas já inseridas anteriormente na memória
						for (i = 0; i < memoria.length; i++) {

							// Se a pagina ainda não está no endereço que o índice indica, imprime a página
							// que está no índice e indica que não está naquele endereço
							if (pagina != memoria[i]) {

								// Vetor compare recebe as páginas em ordem caso haja futura necessidade de
								// substituição
								compare[i] = memoria[i];
								CaixaDeTexto.append(
										(i + 1) + "º Espaço de Memória [" + memoria[i] + "] - Não existe na Memória\n");

							// Se a pagina já está no endereço que o índice indica:
							} else {

								// Variável compare recebe valor -1 para comparação futura
								compare[0] = -1;

								// imprime a página que está no índice e indica que já está naquele endereço
								CaixaDeTexto.append(
										(i + 1) + "º Espaço de Memória [" + memoria[i] + "] - Já existe na memória\n");

								// Indica que não houve falta de página e termina a busca na memória
								CaixaDeTexto.append("---- Não houve falta de página -----\n");
								break;
							}
						}

						// Verifica se o valor de compare é diferente de -1. Se sim, indica que houve
						// falta de página
						if (compare[0] != -1) {

							// Enredeço de memória mais antigo recebe a página que estava faltando
							memoria[k] = pagina;

							// Imprime mensagem indicando que houve falta de página
							CaixaDeTexto.append("---- Houve uma falta de página -----\n");

							// Contador de faltas de páginas acresce +1
							falta++;

							// Variável k (responsável por verificar qual a página mais antiga) acresce +1
							k++;

							// Caso a variável alcance o tamanho da memória, retorna o índice para a página
							// inicial (pois será a mais antiga)
							if (k == memoria.length)
								k = 0;
						}
					}
					// Define a variável k como 0 caso o usuário deseje executar o programa
					// novamente
					k = 0;

					// Imprime a mensagem de que o programa chegou ao fim de sua execução
					CaixaDeTexto.append("\nFim\n\n");

					// Verifica e imprime os valores dos endereços de memórias após todas as
					// substituições de páginas que ocorreram no programa
					for (i = 0; i < memoria.length; i++) {
						CaixaDeTexto.append((i + 1) + "º Espaço de Memória: [" + memoria[i] + "]\n");
					}

					// Imprime e indica no frame qual foi a quantidade total de falta de páginas
					CaixaDeTexto.append("\nTotal de Faltas de Páginas: [" + falta + "]");
					T4.setText(Integer.toString(falta));
				}
			}
			// Captura exceções que não tenham sido tratadas acima para que o programa não
			// feche ou trave sua execução
			catch (Throwable thr) {
			}
		}
	}
}
