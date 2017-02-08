import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Interface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame fen = new JFrame();
		
		//titre fenêtre//
		fen.setTitle("Projet JAVA");
		//Taille fenêtre//
		fen.setSize(500,500);
		//Position au centre//
		fen.setLocationRelativeTo(null);
		//Termine quand on clique sur croix Rouge//
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		fen.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Importer votre image");
		JMenu menu1 = new JMenu("Aide");
		JMenuItem menuItem1 = new JMenuItem("Importer");
		JMenuItem menuItem = new JMenuItem("Feuille d'aide");
		
		menuBar.add(menu);
		menuBar.add(menu1);
		menu.add(menuItem1);
		menu1.add(menuItem);
		menuItem1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser dialogue = new JFileChooser();
				FileNameExtensionFilter filtre = new FileNameExtensionFilter("Images",".png","jpg");
				dialogue.setFileFilter(filtre);
				dialogue.setAcceptAllFileFilterUsed(false);
				File fichier;
				String Nomfichier;
				
				if(dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					fichier = dialogue.getSelectedFile();
					Nomfichier = dialogue.getSelectedFile().getName();
					System.out.println("Fichier sélectionné"+ fichier.getAbsolutePath());
					
				dialogue.setVisible(true);
				}
			}
		});
		menuItem.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				JFrame fen2 = new JFrame();
				
				//Titre fenêtre d'aide//
				fen2.setTitle("Besoin d'aide ?");
				//Taille fenêtre d'aide//
				fen2.setSize(400,200);
				//Se termine avec le click sur la croix rouge//
				fen2.setDefaultCloseOperation(fen2.DISPOSE_ON_CLOSE);
				
				//Texte d'aide//
				JPanel pannel = new JPanel();
				pannel.setBackground(Color.white);
				JLabel label = new JLabel("<html>Voici notre feuille d'aide pour vous aider à comprendre<br>Ici seront les instructions pour faire fonctionner le programme</html>");
				pannel.add(label);
				
				
				fen2.getContentPane().add(pannel);
				fen2.setVisible(true);
			}
		});
		
		//Rendre visible//
		fen.setVisible(true);
		
		
		
		
	}

}
