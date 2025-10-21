import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;

public class ClienteForm extends JFrame {

    private JTextField txtNome, txtEndereco, txtCidade, txtTelefone;
    private JComboBox<String> cbEstado;
    private JRadioButton rbAtivo, rbInativo;
    private ButtonGroup bgStatus;
    private JButton btnSalvar, btnCancelar, btnVoltar;

    public ClienteForm() {
        setTitle("Cadastrar Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // --- Botão "Gravar Dados" ---
        btnSalvar = new JButton("Gravar Dados", loadIcon("icons/save-icon.png", 35, 35));
        btnSalvar.setBounds(30, 10, 160, 50);
        configurarBotaoLimpo(btnSalvar);
        btnSalvar.addActionListener(e -> salvarCliente());
        add(btnSalvar);

        // --- Botão "Cancelar Cadastro" ---
        btnCancelar = new JButton("Cancelar Cadastro", loadIcon("icons/cancel-icon.png", 35, 35));
        btnCancelar.setBounds(200, 10, 200, 50);
        configurarBotaoLimpo(btnCancelar);
        btnCancelar.addActionListener(e -> limparCampos());
        add(btnCancelar);

        // --- Botão "Voltar" ---
        btnVoltar = new JButton("Voltar", loadIcon("icons/back-icon.png", 30, 30));
        btnVoltar.setBounds(30, 320, 120, 35);
        configurarBotaoLimpo(btnVoltar);
        btnVoltar.addActionListener(e -> dispose());
        add(btnVoltar);

        // --- Título ---
        JLabel lblTitulo = new JLabel("Preencha os dados corretamente  e clique em Gravar Dados");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setBounds(80, 70, 500, 25);
        add(lblTitulo);

        // --- Campos do formulário ---
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setBounds(60, 110, 120, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(180, 110, 320, 25);
        add(txtNome);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(60, 145, 120, 25);
        add(lblEndereco);

        txtEndereco = new JTextField();
        txtEndereco.setBounds(180, 145, 320, 25);
        add(txtEndereco);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(60, 180, 120, 25);
        add(lblCidade);

        txtCidade = new JTextField();
        txtCidade.setBounds(180, 180, 320, 25);
        add(txtCidade);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(60, 215, 120, 25);
        add(lblEstado);

        String[] estados = { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
        cbEstado = new JComboBox<>(estados);
        cbEstado.setBounds(180, 215, 100, 25);
        add(cbEstado);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(60, 250, 120, 25);
        add(lblTelefone);

        try {
            MaskFormatter mascaraTelefone = new MaskFormatter("(##) ####-####");
            mascaraTelefone.setPlaceholderCharacter('_'); // underline como na imagem
            txtTelefone = new JFormattedTextField(mascaraTelefone);
            txtTelefone.setBounds(180, 250, 120, 25);
            add(txtTelefone);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(60, 285, 120, 25);
        add(lblStatus);

        rbAtivo = new JRadioButton("Ativo");
        rbInativo = new JRadioButton("Inativo");
        rbAtivo.setBackground(Color.WHITE);
        rbInativo.setBackground(Color.WHITE);

        rbAtivo.setBounds(180, 285, 70, 25);
        rbInativo.setBounds(250, 285, 80, 25);

        bgStatus = new ButtonGroup();
        bgStatus.add(rbAtivo);
        bgStatus.add(rbInativo);

        add(rbAtivo);
        add(rbInativo);
    }

    // --- Remove fundo e borda dos botões ---
    private void configurarBotaoLimpo(JButton botao) {
        botao.setContentAreaFilled(false); // sem fundo cinza
        botao.setBorderPainted(false); // sem borda
        botao.setFocusPainted(false); // sem foco
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setHorizontalAlignment(SwingConstants.LEFT);
    }

    // --- Ação: salvar dados ---
    private void salvarCliente() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo Nome Completo!");
            return;
        }
        if (txtEndereco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo Endereço!");
            return;
        }
        if (txtCidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo Cidade!");
            return;
        }
        if (cbEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione o Estado!");
            return;
        }
        if (txtTelefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo Telefone!");
            return;
        }
        if (!rbAtivo.isSelected() && !rbInativo.isSelected()) {
            JOptionPane.showMessageDialog(this, "Selecione o Status!");
            return;
        }

        JOptionPane.showMessageDialog(this, "✅ Dados gravados com sucesso!");
    }

    // --- Ação: limpar campos ---
    private void limparCampos() {
        txtNome.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtTelefone.setText("");
        cbEstado.setSelectedIndex(0);
        bgStatus.clearSelection();
    }

    // --- Carregar ícones ---
    private ImageIcon loadIcon(String path, int w, int h) {
        java.io.File file = new java.io.File(path);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("❌ Ícone não encontrado: " + file.getAbsolutePath());
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteForm().setVisible(true));
    }
}
