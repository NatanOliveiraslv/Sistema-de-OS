package br.com.infox.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import br.com.infox.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.util.Locale;

public class TelaCliente extends JInternalFrame {
	private JTextField txtEmail;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtFone;
	private JTextField txtPesquisar;
	private JTable tblClientes;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static javax.swing.JButton btnAdicionar;

	/**
	 * Create the frame.
	 */
	public TelaCliente() {
		setFrameIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icones/NTN - infox.png")));
		setResizable(true);
		setClosable(true);
		setLocale(new Locale("es", "BR"));
		conexao = ModuloConexao.conector();
		setTitle("Cliente");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 626, 452);
		getContentPane().setLayout(null);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(87, 287, 456, 20);
		getContentPane().add(txtEmail);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnAdicionar.setEnabled(true);
			}
		});
		txtNome.setColumns(10);
		txtNome.setBounds(87, 192, 456, 20);
		getContentPane().add(txtNome);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(87, 223, 482, 20);
		getContentPane().add(txtEndereco);

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(87, 256, 183, 20);
		getContentPane().add(txtFone);

		JLabel lblNome = new JLabel("* Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(21, 195, 56, 14);
		getContentPane().add(lblNome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(21, 290, 56, 14);
		getContentPane().add(lblEmail);

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setBounds(21, 226, 56, 14);
		getContentPane().add(lblEndereco);

		JLabel lblTelefone = new JLabel("* Telefone");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setBounds(21, 259, 56, 14);
		getContentPane().add(lblTelefone);

		btnAdicionar = new javax.swing.JButton("");
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icones/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(92, 318, 80, 80);
		btnAdicionar.setBackground(null);
		getContentPane().add(btnAdicionar);

		JButton btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icones/Update.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setBackground((Color) null);
		btnAlterar.setBounds(264, 318, 80, 80);
		getContentPane().add(btnAlterar);

		JButton btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
		btnRemover.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icones/delete.png")));
		btnRemover.setToolTipText("Remover");
		btnRemover.setBackground((Color) null);
		btnRemover.setBounds(436, 318, 80, 80);
		getContentPane().add(btnRemover);

		JLabel lblNewLabel = new JLabel("* Campos obrigatórios");
		lblNewLabel.setBounds(468, 11, 132, 14);
		getContentPane().add(lblNewLabel);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisar_cliente();
			}
		});
		txtPesquisar.setColumns(10);
		txtPesquisar.setBounds(10, 11, 301, 20);
		getContentPane().add(txtPesquisar);

		JLabel lblPesquisar = new JLabel("");
		lblPesquisar.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infox/icones/pesquisar.png")));
		lblPesquisar.setBackground(null);
		lblPesquisar.setBounds(321, 11, 32, 32);
		getContentPane().add(lblPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 590, 95);
		getContentPane().add(scrollPane);

		tblClientes = new JTable();
		tblClientes = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tblClientes.setLocale(new Locale("es", "BR"));
		tblClientes.setFocusable(false);
		tblClientes.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", "nome", "endere\u00E7o", "fone", "email" }) {
			boolean[] columnEditables = new boolean[] { true, true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setar_campos();
			}
		});
		scrollPane.setViewportView(tblClientes);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setColumns(10);
		txtId.setBounds(87, 161, 67, 20);
		getContentPane().add(txtId);

		JLabel lblIdClie = new JLabel("Id Cliente");
		lblIdClie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdClie.setBounds(21, 164, 56, 14);
		getContentPane().add(lblIdClie);

	}

	private void adicionar() {
		String sql = "insert into tbclientes(nomecli, endcli, fonecli, emailcli) values(?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtEndereco.getText());
			pst.setString(3, txtFone.getText());
			pst.setString(4, txtEmail.getText());

			if ((txtNome.getText().isEmpty()) || (txtFone.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
					limpar();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void pesquisar_cliente() {
		String sql = "select idcli as id, nomecli as nome, endcli as endereço, fonecli as fone, emailcli as email from tbclientes where nomecli like ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtPesquisar.getText() + "%");
			rs = pst.executeQuery();
			tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setar_campos() {
		int setar = tblClientes.getSelectedRow();
		txtId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
		txtEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
		txtFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
		txtEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
		btnAdicionar.setEnabled(false);
	}

	private void alterar() {
		String sql = "update tbclientes set nomecli=?, endcli=?, fonecli=?, emailcli=? where idcli = ?;";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtEndereco.getText());
			pst.setString(3, txtFone.getText());
			pst.setString(4, txtEmail.getText());
			pst.setString(5, txtId.getText());

			if ((txtNome.getText().isEmpty()) || (txtFone.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {

				int adicionado = pst.executeUpdate();

				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
					limpar();
					btnAdicionar.setEnabled(true);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void remover() {
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_NO_OPTION) {
			String sql = "delete from tbclientes where idcli=?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtId.getText());

				int apagado = pst.executeUpdate();

				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
					limpar();
					btnAdicionar.setEnabled(true);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void limpar() {
		txtPesquisar.setText(null);
		txtId.setText(null);
		txtNome.setText(null);
		txtEndereco.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
	}

}
