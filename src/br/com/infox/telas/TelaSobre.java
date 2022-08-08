package br.com.infox.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TelaSobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaSobre() {
		setResizable(false);
		setTitle("Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema para controle de Ordem de Serviços");
		lblNewLabel.setBounds(10, 11, 280, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDesenvolvidoPorNatan = new JLabel("Desenvolvido por Natan Oliveira da Silva");
		lblDesenvolvidoPorNatan.setBounds(10, 46, 280, 14);
		contentPane.add(lblDesenvolvidoPorNatan);
		
		JLabel lblSobLicenaGpl = new JLabel("Sob Licença GPL");
		lblSobLicenaGpl.setBounds(10, 79, 280, 14);
		contentPane.add(lblSobLicenaGpl);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaSobre.class.getResource("/br/com/infox/icones/Gnu-logo.png")));
		lblNewLabel_1.setBounds(246, 31, 100, 144);
		contentPane.add(lblNewLabel_1);
	}

}
