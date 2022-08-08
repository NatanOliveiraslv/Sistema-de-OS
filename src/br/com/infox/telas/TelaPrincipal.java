package br.com.infox.telas;

import java.awt.EventQueue;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.ComponentOrientation;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;


public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuItem menCadCli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static javax.swing.JMenu mnRelatorio;
	public static javax.swing.JMenuItem menCadUsuario;
	public static javax.swing.JLabel lblUsuario;

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setResizable(true);
		setTitle("X  - Sistema para controle de OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 410);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
			
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(UIManager.getColor("Button.background"));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		menCadCli = new JMenuItem("Cliente");
		menCadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				desktopPane.add(cliente);
			}
		});
		menCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(menCadCli);
		
		JMenuItem menCadOS = new JMenuItem("OS");
		menCadOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOS os = new TelaOS();
				os.setVisible(true);
				desktopPane.add(os);
			}
		});
		menCadOS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(menCadOS);
				
		mnRelatorio = new javax.swing.JMenu();
		mnRelatorio.setText("Relatório");
		mnRelatorio.setEnabled(false);
		menuBar.add(mnRelatorio);
		
		JMenuItem menRelSer = new JMenuItem("Serviços");
		menRelSer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		mnRelatorio.add(menRelSer);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem menAjuSob = new JMenuItem("Sobre");
		menAjuSob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
			}
		});
		menAjuSob.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
		mnAjuda.add(menAjuSob);
		
		JMenu mnOpcoes = new JMenu("Opções");
		menuBar.add(mnOpcoes);
		
		JMenuItem menOpcSai = new JMenuItem("Sair");
		menOpcSai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		menOpcSai.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnOpcoes.add(menOpcSai);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblData = new JLabel("Data");
		
		lblUsuario = new javax.swing.JLabel();
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setText("Usuário");
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addContainerGap(313, Short.MAX_VALUE)
					.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario))
					.addContainerGap(323, Short.MAX_VALUE))
		);
		desktopPane.setLayout(gl_desktopPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane)
		);
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
				lblData.setText(formatador.format(data));
			}
		});	
		
		menCadUsuario = new javax.swing.JMenuItem();
		menCadUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario usuario = new TelaUsuario();
				usuario.setVisible(true);
				desktopPane.add(usuario);
			}
		});
		menCadUsuario.setText("Usuários");
		menCadUsuario.setEnabled(false);
		menCadUsuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(menCadUsuario);
	}
}


