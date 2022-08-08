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
		conexao = ModuloConexao.conector();
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Usuários");
		setBounds(100, 100, 626, 452);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("* Id");
		lblId.setBounds(23, 25, 46, 14);
		getContentPane().add(lblId);

		JLabel lblNome = new JLabel("* Nome");
		lblNome.setBounds(23, 74, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblLogin = new JLabel("* Login");
		lblLogin.setBounds(298, 126, 46, 14);
		getContentPane().add(lblLogin);

		JLabel lblSenha = new JLabel("* Senha");
		lblSenha.setBounds(23, 190, 46, 14);
		getContentPane().add(lblSenha);

		JLabel lblPerfil = new JLabel("* Perfil");
		lblPerfil.setBounds(298, 190, 46, 14);
		getContentPane().add(lblPerfil);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(23, 126, 46, 14);
		getContentPane().add(lblFone);

		txtId = new JTextField();
		txtId.setBounds(69, 22, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(69, 71, 499, 20);
		getContentPane().add(txtNome);

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(69, 123, 219, 20);
		getContentPane().add(txtFone);

		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(69, 187, 219, 20);
		getContentPane().add(txtSenha);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(349, 120, 219, 20);
		getContentPane().add(txtLogin);

		cbPerfil = new javax.swing.JComboBox();
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] { "admin", "user" }));
		cbPerfil.setBounds(349, 187, 219, 20);
		getContentPane().add(cbPerfil);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(58, 286, 80, 80);
		btnAdicionar.setBackground(null);
		getContentPane().add(btnAdicionar);

		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/read.png")));
		btnConsultar.setToolTipText("Consultar");
		btnConsultar.setBackground((Color) null);
		btnConsultar.setBounds(196, 286, 80, 80);
		getContentPane().add(btnConsultar);

		JButton btnAlterar = new JButton("");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/Update.png")));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setBackground((Color) null);
		btnAlterar.setBounds(334, 286, 80, 80);
		getContentPane().add(btnAlterar);

		JButton btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
		btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemover.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infox/icones/delete.png")));
		btnRemover.setToolTipText("Remover");
		btnRemover.setBackground((Color) null);
		btnRemover.setBounds(472, 286, 80, 80);
		getContentPane().add(btnRemover);

		JLabel lblNewLabel = new JLabel("* Campos obrigatórios");
		lblNewLabel.setBounds(436, 25, 132, 14);
		getContentPane().add(lblNewLabel);

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
