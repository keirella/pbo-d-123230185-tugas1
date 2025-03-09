package pbo.d.pkg123230185.tugas1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class dashboard extends JFrame {

    final JTextField fnamadepan = new JTextField(20);
    JLabel lnamadepan = new JLabel("Nama Depan: ");

    final JTextField fnamabelakang = new JTextField(20);
    JLabel lnamabelakang = new JLabel("Nama Belakang: ");

    JLabel ljeniskelamin = new JLabel("Jenis Kelamin");
    JRadioButton rbpria = new JRadioButton("Pria");
    JRadioButton rbwanita = new JRadioButton("Wanita");

    JButton saveButton = new JButton("Simpan");
    JButton convertButton = new JButton("Convert to .txt File");

    private JLabel messageLabel;
    private JTextArea dataTextArea;
    private ArrayList<String> dataList;

    public dashboard() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        ButtonGroup group = new ButtonGroup();
        group.add(rbpria);
        group.add(rbwanita);

        lnamadepan.setBounds(20, 20, 100, 25);
        fnamadepan.setBounds(180, 20, 170, 25);
        lnamabelakang.setBounds(20, 50, 100, 25);
        fnamabelakang.setBounds(180, 50, 170, 25);
        ljeniskelamin.setBounds(20, 80, 100, 25);
        rbpria.setBounds(130, 80, 60, 25);
        rbwanita.setBounds(190, 80, 80, 25);

        dataTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(dataTextArea);
        dataTextArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 120, 450, 200);

        saveButton.setBounds(150, 330, 80, 25);
        convertButton.setBounds(240, 330, 150, 25);

        add(lnamadepan);
        add(fnamadepan);
        add(lnamabelakang);
        add(fnamabelakang);
        add(ljeniskelamin);
        add(rbpria);
        add(rbwanita);
        add(scrollPane);
        add(saveButton);
        add(convertButton);

        dataList = new ArrayList<>();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = fnamadepan.getText();
                String lastName = fnamabelakang.getText();
                String gender = rbpria.isSelected() ? "Pria" : "Wanita";

                if (firstName.isEmpty() || lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(dashboard.this, "Data belum lengkap", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String data = firstName + " " + lastName + " (" + gender + ")";
                    dataList.add(data);
                    updateTextArea();
                    clearFields();
                    CustomPopup popup = new CustomPopup(dashboard.this, "Data berhasil disimpan");
                    popup.showPopup();
                }
            }
        });

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dataList.isEmpty()) {
                    JOptionPane.showMessageDialog(dashboard.this, "Tidak ada data", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    saveToFile();
                    showSuccessDialog();
                }
            }
        });

        setVisible(true);
    }

    private void updateTextArea() {
        StringBuilder sb = new StringBuilder();
        for (String data : dataList) {
            sb.append(data).append("\n");
        }
        dataTextArea.setText(sb.toString());
    }

    private void clearFields() {
        fnamadepan.setText("");
        fnamabelakang.setText("");
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter("data_nim.txt")) {
            for (String data : dataList) {
                writer.write(data + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSuccessDialog() {
        CustomPopup popup = new CustomPopup(dashboard.this, "Data berhasil dikonversi ke file");
        popup.showPopup();
        showFileContent();
    }

    private void showFileContent() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data_nim.txt"))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(dashboard.this, content.toString(), "Isi File data_nim.txt", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(dashboard.this, "Gagal membaca file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new dashboard();
    }
}

class CustomPopup extends JDialog {

    public CustomPopup(JFrame parent, String message) {
        super(parent, "Notifikasi", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void showPopup() {
        setVisible(true);
    }
}