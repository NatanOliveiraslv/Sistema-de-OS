package br.com.infox.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import br.com.infox.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class TelaOS extends JInternalFrame {
	private JTextField txtOs;
	private JTextField txtData;
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTable tbClientes;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtServico;
	private JTextField txtValor;
	private JTextField txtTecnico;
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
					TelaOS frame = new TelaOS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String tipo;
	private javax.swing.JRadioButton rdbtnorcamento;
	private javax.swing.JRadioButton rdbtnOs;
	private javax.swing.JComboBox cboOs;
	private javax.swing.JButton btnAdicionar;
	private javax.swing.JButton btnPesquisar;
	private javax.swing.JButton btnAlterar;
	private javax.swing.JButton btnRemover;
	private javax.swing.JButton btnImprimir;

	/**
	 * Create the frame.
	 */
	public TelaOS() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				rdbtnorcamento.setSelected(true);
				tipo = "Orçamento";
			}
		});
		conexao = ModuloConexao.conector();
		setMaximizable(true);
		setTitle("OS");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 626, 452);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 247, 102);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("N° OS");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(86, 11, 46, 14);
		panel.add(lblData);

		txtOs = new JTextField();
		txtOs.setEnabled(false);
		txtOs.setBounds(10, 28, 66, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);

		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.BOLD, 9));
		txtData.setEnabled(false);
		txtData.setColumns(10);
		txtData.setBounds(86, 28, 148, 20);
		panel.add(txtData);

		rdbtnorcamento = new javax.swing.JRadioButton("Orçamento");
		rdbtnorcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";
			}
		});
		rdbtnorcamento.setBounds(10, 72, 109, 23);
		panel.add(rdbtnorcamento);

		rdbtnOs = new javax.swing.JRadioButton("Ordem de serviço");
		rdbtnOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "OS";
			}
		});
		rdbtnOs.setBounds(125, 72, 109, 23);
		panel.add(rdbtnOs);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnorcamento);
		grupo.add(rdbtnOs);

		JLabel lblNewLabel_1 = new JLabel("Situação");
		lblNewLabel_1.setBounds(10, 134, 46, 14);
		getContentPane().add(lblNewLabel_1);

		cboOs = new javax.swing.JComboBox();
		cboOs.setModel(new DefaultComboBoxModel(new String[] { " ", "Na bancada", "Entrega OK", "Orçamento REPROVADO",
				"Aguardando aprovação", "Aguardando peças", "Abandonado pelo cliente", "Retornou" }));
		cboOs.setBounds(59, 130, 198, 18);
		getContentPane().add(cboOs);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(267, 11, 333, 181);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisar_cliente();
			}
		});
		txtPesquisar.setBounds(10, 26, 150, 20);
		panel_1.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/infox/icones/pesquisar.png")));
		lblNewLabel_2.setBounds(167, 11, 35, 35);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("* Id");
		lblNewLabel_3.setBounds(212, 29, 35, 14);
		panel_1.add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(235, 26, 88, 20);
		panel_1.add(txtId);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 312, 107);
		panel_1.add(scrollPane);

		tbClientes = new JTable();
		tbClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setar_campos();
			}
		});
		tbClientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Fone" }));
		scrollPane.setViewportView(tbClientes);

		JLabel lblNewLabel_4 = new JLabel("* Equipamento");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(10, 206, 84, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("* Defeito");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setBounds(10, 231, 84, 14);
		getContentPane().add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("Serviço");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setBounds(10, 256, 84, 14);
		getContentPane().add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_3 = new JLabel("Técnico");
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_3.setBounds(10, 281, 84, 14);
		getContentPane().add(lblNewLabel_4_3);

		JLabel lblNewLabel_4_3_1 = new JLabel("Valor Total");
		lblNewLabel_4_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_3_1.setBounds(313, 281, 84, 14);
		getContentPane().add(lblNewLabel_4_3_1);

		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(104, 203, 496, 20);
		getContentPane().add(txtEquipamento);
		txtEquipamento.setColumns(10);

		txtDefeito = new JTextField();
		txtDefeito.setColumns(10);
		txtDefeito.setBounds(104, 228, 496, 20);
		getContentPane().add(txtDefeito);

		txtServico = new JTextField();
		txtServico.setColumns(10);
		txtServico.setBounds(104, 253, 496, 20);
		getContentPane().add(txtServico);

		txtValor = new JTextField();
		txtValor.setText("0");
		txtValor.setColumns(10);
		txtValor.setBounds(407, 278, 193, 20);
		getContentPane().add(txtValor);

		txtTecnico = new JTextField();
		txtTecnico.setColumns(10);
		txtTecnico.setBounds(104, 278, 193, 20);
		getContentPane().add(txtTecnico);

		btnAdicionar = new javax.swing.JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitir_os();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/infox/icones/create.png")));
		btnAdicionar.setToolTipText("Emitir OS");
		btnAdicionar.setBackground((Color) null);
		btnAdicionar.setBounds(35, 331, 80, 80);
		getContentPane().add(btnAdicionar);

		btnPesquisar = new javax.swing.JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar_os();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/infox/icones/read.png")));
		btnPesquisar.setToolTipText("Pesquisar OS");
		btnPesquisar.setBackground((Color) null);
		btnPesquisar.setBounds(150, 331, 80, 80);
		getContentPane().add(btnPesquisar);

		btnAlterar = new javax.swing.JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar_os();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/infox/icones/Update.png")));
		btnAlterar.setToolTipText("Alterar OS");
		btnAlterar.setBackground((Color) null);
		btnAlterar.setBounds(265, 331, 80, 80);
		getContentPane().add(btnAlterar);

		btnRemover = new javax.swing.JButton("");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir_os();
			}
		});
		btnRemover.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/infox/icones/delete.png")));
		btnRemover.setToolTipText("Remover OS");
		btnRemover.setBackground((Color) null);
		btnRemover.setBounds(380, 331, 80, 80);
		getContentPane().add(btnRemover);

		btnImprimir = new javax.swing.JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				geraRelServico();
			}
		});
		btnImprimir.setEnabled(false);
		btnImprimir.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/infox/icones/print.png")));
		btnImprimir.setToolTipText("Imprimir OS");
		btnImprimir.setBackground((Color) null);
		btnImprimir.setBounds(495, 331, 80, 80);
		getContentPane().add(btnImprimir);
	}

	private void pesquisar_cliente() {
		String sql = "select idcli as Id, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtPesquisar.getText() + "%");
			rs = pst.executeQuery();
			tbClientes.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void setar_campos() {
		int setar = tbClientes.getSelectedRow();
		txtId.setText(tbClientes.getModel().getValueAt(setar, 0).toString());
	}

	private void emitir_os() {
		String sql = "insert into tbos(tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, tipo);
			pst.setString(2, cboOs.getSelectedItem().toString());
			pst.setString(3, txtEquipamento.getText());
			pst.setString(4, txtDefeito.getText());
			pst.setString(5, txtServico.getText());
			pst.setString(6, txtTecnico.getText());
			pst.setString(7, txtValor.getText());
			pst.setString(8, txtId.getText());
			if (txtId.getText().isEmpty() || txtEquipamento.getText().isEmpty() || txtDefeito.getText().isEmpty()
					|| cboOs.getSelectedItem().equals(" ")) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {
				int adicionado = pst.executeUpdate();
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
					recuperarOs();
					btnAdicionar.setEnabled(false);
					btnPesquisar.setEnabled(false);
					btnImprimir.setEnabled(true);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void pesquisar_os() {
		String num_os = JOptionPane.showInputDialog("Numero da OS");
		String sql = "select os,date_format(data_os,'%d/%m/%y  - %H:%i'),tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli from tbos where os ="
				+ num_os;
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOs.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				String rbtTipo = rs.getString(3);
				if (rbtTipo.equals("OS")) {
					rdbtnOs.setSelected(true);
					tipo = "OS";
				} else {
					rdbtnorcamento.setSelected(true);
					tipo = "Orçamento";
				}
				cboOs.setSelectedItem(rs.getString(4));
				txtEquipamento.setText(rs.getString(5));
				txtDefeito.setText(rs.getString(6));
				txtServico.setText(rs.getString(7));
				txtTecnico.setText(rs.getString(8));
				txtValor.setText(rs.getString(9));
				txtId.setText(rs.getString(10));
				btnAdicionar.setEnabled(false);
				btnPesquisar.setEnabled(false);
				txtPesquisar.setEnabled(false);
				tbClientes.setVisible(false);
				btnAlterar.setEnabled(true);
				btnRemover.setEnabled(true);
				btnImprimir.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null, "OS não cadastrada");

			}
		} catch (java.sql.SQLSyntaxErrorException e) {
			JOptionPane.showMessageDialog(null, "OS não cadastrada", "Erro", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void alterar_os() {
		String sql = "update tbos set tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where os=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, tipo);
			pst.setString(2, cboOs.getSelectedItem().toString());
			pst.setString(3, txtEquipamento.getText());
			pst.setString(4, txtDefeito.getText());
			pst.setString(5, txtServico.getText());
			pst.setString(6, txtTecnico.getText());
			pst.setString(7, txtValor.getText());
			pst.setString(8, txtOs.getText());

			if (txtId.getText().isEmpty() || txtEquipamento.getText().isEmpty() || txtDefeito.getText().isEmpty()
					|| cboOs.getSelectedItem().equals(" ")) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {
				int adicionado = pst.executeUpdate();
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "OS alterada com sucesso");
					limpar();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void excluir_os() {
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certaza que desja remover esta OS?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String sql = "delete from tbos where os= ?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtOs.getText());
				int apagado = pst.executeUpdate();
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "OS excluída com sucesso");
					limpar();
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void limpar() {
		((DefaultTableModel) tbClientes.getModel()).setRowCount(0);
		cboOs.setSelectedItem(" ");
		txtPesquisar.setText(null);
		txtOs.setText(null);
		txtData.setText(null);
		txtId.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtServico.setText(null);
		txtTecnico.setText(null);
		txtValor.setText(null);

		btnAdicionar.setEnabled(true);
		btnPesquisar.setEnabled(true);
		txtPesquisar.setEnabled(true);
		tbClientes.setVisible(true);

		btnAlterar.setEnabled(false);
		btnRemover.setEnabled(false);
		btnImprimir.setEnabled(false);
	}
	
	private void recuperarOs() {
		String sql = "Select max(os) from tbos";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				txtOs.setText(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void geraRelServico(){
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório?", "Atenção",JOptionPane.YES_NO_OPTION);
		if(confirma == JOptionPane.YES_OPTION) {
			try {
				HashMap filtro = new HashMap();
				filtro.put("os",Integer.parseInt(txtOs.getText())); 
		        JasperPrint print = JasperFillManager.fillReport("src/MyReports/OS.jasper", filtro, conexao);
		        
				JFrame tela = new JFrame("Relatório");
				tela.setBounds(100, 100, 626, 452);
				
				JRViewer painel = new JRViewer(print);
				tela.getContentPane().add(painel);
				tela.setVisible(true);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
