package br.com.infox.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.infox.dal.ModuloConexao;
import java.awt.Toolkit;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
							.getInstalledLookAndFeels()) {
						if ("Windows".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| javax.swing.UnsupportedLookAndFeelException ex) {
					System.err.println(ex);
				}
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/br/com/infox/icones/NTN - infox.png")));
		conexao = ModuloConexao.conector();
		setResizable(false);
		setTitle("X - System - Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setBounds(27, 24, 36, 14);
		contentPane.add(lblNewLabel);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(27, 65, 30, 14);
		contentPane.add(lblSenha);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(73, 24, 235, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(73, 65, 235, 20);
		contentPane.add(txtSenha);

		JLabel lbStatus = new JLabel("");
		lbStatus.setBounds(27, 112, 30, 30);
		contentPane.add(lbStatus);

		if (conexao != null) {
			lbStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/infox/icones/dbok.png")));
		} else {
			lbStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/infox/icones/dberro.png")));
		}

		JButton btLogin = new JButton("Login");
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btLogin.setBounds(219, 119, 89, 23);
		contentPane.add(btLogin);
	}

	private void logar() {
		String sql = "select * from tbusuarios where login=? and senha=?;";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			String captura = new String(txtSenha.getPassword());
			pst.setString(2, captura);
			rs = pst.executeQuery();

			if (rs.next()){
				String perfil = rs.getString(6);
				if(perfil.equals("admin")) {
					TelaPrincipal obj = new TelaPrincipal();
					TelaPrincipal.mnRelatorio.setEnabled(true);
					TelaPrincipal.menCadUsuario.setEnabled(true);
					TelaPrincipal.lblUsuario.setText(rs.getString(2));
					TelaPrincipal.lblUsuario.setForeground(Color.red);
					obj.setVisible(true);
					this.dispose();
					conexao.close();
				}else {
					TelaPrincipal obj = new TelaPrincipal();
					TelaPrincipal.lblUsuario.setText(rs.getString(2));
					obj.setVisible(true);
					this.dispose();
					conexao.close();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)", "Alerta",JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sem conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

}
