package view;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LaunchView extends JFrame {

	private JButton parser;
	private JTextArea textArea;
	private JTextField fichero;
	private JLabel label_fichero;
	
	private JPanel panel;
	
	public LaunchView() {
		
		setBounds(200,200,1000,450);
		setTitle("Library Parser");	
		panel = new JPanel();
		
		// Botones
		parser = new JButton("Parse XML");
		parser.setPreferredSize(new Dimension(150, 26));
		
		// JTextField
		fichero = new JTextField("",10);
		
		// JLabel
		label_fichero = new JLabel("Fichero XML:");

		// JTextArea
		textArea = new JTextArea(20, 80);
		textArea.setBounds(50,50,50,50);
		textArea.setEditable(false);		
		
		// Añadimos los elementos gráficos
		panel.add(parser);
		panel.add(label_fichero);
		panel.add(fichero);

		panel.add(textArea);
		
        // Añadimos el JPanel al JFrame
        this.getContentPane().add(panel);		
		
	}	
	
	// Generamos los getters y setters necesarios
	
	public JButton getParser() {
		return parser;
	}

	public void setParser(JButton comparar) {
		this.parser = comparar;
	}


	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public void writeTextArea(String text) {
		textArea.setText(textArea.getText() + "\n" + text); // esto añadirá lineas a modo de log
	}
			
	public JTextField getFichero() {
		return fichero;
	}

	public void setFichero(JTextField fichero) {
		this.fichero = fichero;
	}

	public void showError(String m){
		JOptionPane.showMessageDialog(this.panel,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
}
