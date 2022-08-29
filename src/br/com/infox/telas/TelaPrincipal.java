package br.com.infox.telas;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.infox.dal.ModuloConexao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem menCadCli;
	Connection conexao = null;

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
	public static javax.swing.JDesktopPane desktopPane;
	private static TelaCliente cliente = null;
	private static TelaOS os = null;
	private static TelaSobre sobre = null;
	private static TelaUsuario usuario = null;

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/br/com/infox/icones/NTN - infox.png")));
		conexao = ModuloConexao.conector();
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setResizable(true);
		setTitle("X  - Sistema para controle de OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
//		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		desktopPane = new javax.swing.JDesktopPane();
		desktopPane.setBackground(UIManager.getColor("Button.background"));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		menCadCli = new JMenuItem("Cliente");
		menCadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cliente == null) {
					cliente = new TelaCliente();
					desktopPane.add(cliente);
					cliente.setVisible(true);
					cliente.moveToFront();
				} else {
					if (cliente.isVisible()) {
						cliente.moveToFront();
						try {
							cliente.setIcon(false);
						} catch (PropertyVetoException e1) {
						}
					} else {
						desktopPane.add(cliente);
						cliente = null;
						cliente = new TelaCliente();
						desktopPane.add(cliente);
						cliente.setVisible(true);
						cliente.moveToFront();
					}
				}
			}
		});
		menCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(menCadCli);

		JMenuItem menCadOS = new JMenuItem("OS");
		menCadOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (os == null) {
					os = new TelaOS();
					desktopPane.add(os);
					os.setVisible(true);
					os.moveToFront();
				} else {
					if (os.isVisible()) {
						os.moveToFront();
						try {
							os.setIcon(false);
						} catch (PropertyVetoException e1) {
						}
					} else {
						desktopPane.add(os);
						os = null;
						os = new TelaOS();
						desktopPane.add(os);
						os.setVisible(true);
						os.moveToFront();
					}
				}
			}

		});
		menCadOS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(menCadOS);

		mnRelatorio = new javax.swing.JMenu();
		mnRelatorio.setText("Relatório");
		mnRelatorio.setEnabled(false);
		menuBar.add(mnRelatorio);

		JMenuItem menRelCli = new JMenuItem("Clientes");
		menRelCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				geraRelCli();
			}
		});
		menRelCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
		mnRelatorio.add(menRelCli);

		JMenuItem menRelSer = new JMenuItem("Serviços");
		menRelSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				geraRelServico();
			}
		});
		menRelSer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		mnRelatorio.add(menRelSer);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem menAjuSob = new JMenuItem("Sobre");
		menAjuSob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sobre == null) {
					sobre = new TelaSobre();
					sobre.setVisible(true);
				} else {
					if (sobre.isVisible()) {
					} else {
						sobre.setVisible(true);
					}
				}
			}
		});
		menAjuSob.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
		mnAjuda.add(menAjuSob);

		JMenu mnOpcoes = new JMenu("Opções");
		menuBar.add(mnOpcoes);

		JMenuItem menOpcSai = new JMenuItem("Sair");
		menOpcSai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (sair == JOptionPane.YES_OPTION) {
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
		
		JLabel t = new JLabel("");
		t.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/infox/icones/NTN - infox.png")));
		t.setHorizontalAlignment(NORMAL);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGap(243)
					.addComponent(t, GroupLayout.PREFERRED_SIZE, 288, Short.MAX_VALUE)
					.addGap(243))
				.addGroup(Alignment.TRAILING, gl_desktopPane.createSequentialGroup()
					.addContainerGap(536, Short.MAX_VALUE)
					.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario))
					.addGap(132)
					.addComponent(t, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
					.addGap(148))
		);
		desktopPane.setLayout(gl_desktopPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(desktopPane));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(desktopPane));
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
				if (usuario == null) {
					usuario = new TelaUsuario();
					desktopPane.add(usuario);
					usuario.setVisible(true);
					usuario.moveToFront();
				} else {
					if (usuario.isVisible()) {
						usuario.moveToFront();
						try {
							usuario.setIcon(false);
						} catch (PropertyVetoException e1) {
						}
					} else {
						usuario = null;
						usuario = new TelaUsuario();
						desktopPane.add(usuario);
						usuario.setVisible(true);
						usuario.moveToFront();
					}
				}
			}
		});
		menCadUsuario.setText("Usuários");
		menCadUsuario.setEnabled(false);
		menCadUsuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(menCadUsuario);
	}

	private void geraRelCli() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				JasperPrint print = JasperFillManager.fillReport("src/MyReports/Clientes1.jasper", null, conexao);

				JFrame tela = new JFrame("Relatório");
				tela.setBounds(100, 100, 800, 600);

				JRViewer painel = new JRViewer(print);
				tela.getContentPane().add(painel);
				tela.setExtendedState(MAXIMIZED_BOTH);
				tela.setVisible(true);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void geraRelServico() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				JasperPrint print = JasperFillManager.fillReport("src/MyReports/servicos.jasper", null, conexao);

				JFrame tela = new JFrame("Relatório");
				tela.setBounds(100, 100, 800, 600);

				JRViewer painel = new JRViewer(print);
				tela.getContentPane().add(painel);
				tela.setExtendedState(MAXIMIZED_BOTH);
				tela.setVisible(true);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
