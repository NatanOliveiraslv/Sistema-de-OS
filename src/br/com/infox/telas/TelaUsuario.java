package br.com.infox.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.infox.dal.ModuloConexao;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class TelaUsuario extends JInternalFrame {
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtSenha;
	private JTextField txtLogin;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static javax.swing.JComboBox cbPerfil;

	/**
	 * Create the frame.
	 */
	public TelaUsuario() {
		setFrameIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/NTN - infox.png")));
		setResizable(true);
		conexao = ModuloConexao.conector();
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Usuários");
		setBounds(100, 100, 507, 441);

		JLabel lblId = new JLabel("* Id:");
		lblId.setBounds(23, 40, 46, 14);

		JLabel lblNome = new JLabel("* Nome do usuário:");
		lblNome.setBounds(23, 96, 115, 14);

		JLabel lblLogin = new JLabel("* Login");
		lblLogin.setBounds(268, 152, 46, 14);

		JLabel lblSenha = new JLabel("* Senha");
		lblSenha.setBounds(23, 216, 46, 14);

		JLabel lblPerfil = new JLabel("* Perfil");
		lblPerfil.setBounds(268, 216, 46, 14);

		JLabel lblFone = new JLabel("Fone:");
		lblFone.setBounds(23, 152, 46, 14);

		txtId = new JTextField();
		txtId.setBounds(23, 65, 86, 20);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(23, 121, 445, 20);
		txtNome.setColumns(10);

		txtFone = new JTextField();
		txtFone.setBounds(23, 177, 200, 20);
		txtFone.setColumns(10);

		txtSenha = new JTextField();
		txtSenha.setBounds(23, 237, 200, 20);
		txtSenha.setColumns(10);

		txtLogin = new JTextField();
		txtLogin.setBounds(268, 177, 200, 20);
		txtLogin.setColumns(10);

		cbPerfil = new javax.swing.JComboBox();
		cbPerfil.setBounds(268, 237, 200, 20);
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] { "admin", "user" }));

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setBounds(10, 319, 80, 80);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBackground(null);

		JButton btnConsultar = new JButton("");
		btnConsultar.setBounds(143, 319, 80, 80);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/read.png")));
		btnConsultar.setToolTipText("Consultar");
		btnConsultar.setBackground((Color) null);

		JButton btnAlterar = new JButton("");
		btnAlterar.setBounds(268, 319, 80, 80);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/Update.png")));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setBackground((Color) null);

		JButton btnRemover = new JButton("");
		btnRemover.setBounds(401, 319, 80, 80);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
		btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemover.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/delete.png")));
		btnRemover.setToolTipText("Remover");
		btnRemover.setBackground((Color) null);

		JLabel lblNewLabel = new JLabel("* Campos obrigatórios");
		lblNewLabel.setBounds(336, 51, 132, 14);
		getContentPane().setLayout(null);
		getContentPane().add(lblId);
		getContentPane().add(lblNome);
		getContentPane().add(lblLogin);
		getContentPane().add(lblSenha);
		getContentPane().add(lblPerfil);
		getContentPane().add(lblFone);
		getContentPane().add(txtId);
		getContentPane().add(txtNome);
		getContentPane().add(txtFone);
		getContentPane().add(txtSenha);
		getContentPane().add(txtLogin);
		getContentPane().add(cbPerfil);
		getContentPane().add(btnAdicionar);
		getContentPane().add(btnConsultar);
		getContentPane().add(btnAlterar);
		getContentPane().add(btnRemover);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 473, 285);
		getContentPane().add(panel);

	}

	private void consultar() {
		String sql = "select * from tbusuarios where iduser=?;";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtId.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtNome.setText(rs.getString(2));
				txtFone.setText(rs.getString(3));
				txtLogin.setText(rs.getString(4));
				txtSenha.setText(rs.getString(5));
				cbPerfil.setSelectedItem(rs.getString(6));
			} else {
				JOptionPane.showMessageDialog(null, "Usuario não cadastrado", "Alerta", JOptionPane.WARNING_MESSAGE);
				limpar();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void adicionar() {
		String sql = "insert into tbusuarios(iduser, usuario, fone, login, senha, perfil) values(?,?,?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtId.getText());
			pst.setString(2, txtNome.getText());
			pst.setString(3, txtFone.getText());
			pst.setString(4, txtLogin.getText());
			pst.setString(5, txtSenha.getText());
			pst.setString(6, cbPerfil.getSelectedItem().toString());

			if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty() || (txtLogin.getText().isEmpty())
					|| (txtSenha.getText().isEmpty()))) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
					limpar();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void alterar() {
		String sql = "update tbusuarios set usuario=?, fone=?, login=?, senha=?, perfil=? where iduser=?;";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtFone.getText());
			pst.setString(3, txtLogin.getText());
			pst.setString(4, txtSenha.getText());
			pst.setString(5, cbPerfil.getSelectedItem().toString());
			pst.setString(6, txtId.getText());

			if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty() || (txtLogin.getText().isEmpty())
					|| (txtSenha.getText().isEmpty()))) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
					limpar();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void remover() {
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_NO_OPTION) {
			String sql = "delete from tbusuarios where iduser=?;";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtId.getText());

				int apagado = pst.executeUpdate();

				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Usuario removido com sucesso");
					limpar();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
	}
}
