package src;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class BaseSystemCalculator_Layout extends JFrame {

    private JButton BtCalculate, BtReset_Layout_Two;
    private JButton BtConvert, BtReset_Layout_One;
    private JTextField Number_One_Layout_Two, Number_Two_Layout_Two, ketQuaField_Layout_Two;
    private JTextField Number_One_Layout_One, ketQuaField_Layout_One;
    private JComboBox<String> Operation;
    private JLabel lblCalculate, lblBanQuyen, lblChuyenDoi;
    private JTextField Current_Base_System, Target_Radix_System;
    private JTextField First_Radix_System, Second_Base_System, Result_Radix_System;

    private final String FirstColorCode = "#BBBBBB";
    Font fontBigSize = new Font("arial", Font.BOLD, 30);
    Font fontSmallSize = new Font("arial", Font.BOLD, 16);

    public BaseSystemCalculator_Layout(String title) {
        this.setTitle(title);
        addControls();
        addEvents();
        ImageIcon icon = new ImageIcon("Data/Logo-icon.png"); // Đảm bảo đường dẫn chính xác
        Image image = icon.getImage();

        setIconImage(image);
    }

    private void addControls() {
        addControls_Layout_One();
        addControls_Layout_Two();
        addControls_Layout_Menu();
    }

    private void addControls_Layout_Menu() {
        Container container = getContentPane();
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        JPanel panel1 = addControls_Layout_One();
        JPanel panel2 = addControls_Layout_Two();

        cardPanel.add(panel1, "Panel 1");
        cardPanel.add(panel2, "Panel 2");

        container.add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton button1 = new JButton("CHUYỂN ĐỔI");
        JButton button2 = new JButton("TÍNH CƠ SỐ");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        container.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBounds(0, 0, 550, 60);

        buttonPanel.setPreferredSize(new Dimension(550, 60));
        container.add(buttonPanel, BorderLayout.NORTH);
        container.add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "Panel 1");
        updateButtonColors(button1, button2, "Panel 1");

        button1.addActionListener(e -> {
            cardLayout.show(cardPanel, "Panel 1");
            updateButtonColors(button1, button2, "Panel 1");
        });
        button2.addActionListener(e -> {
            cardLayout.show(cardPanel, "Panel 2");
            updateButtonColors(button1, button2, "Panel 2");
        });
    }

    private JPanel addControls_Layout_One() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(Color.WHITE);

        lblChuyenDoi = new JLabel("CHUYỂN ĐỔI HỆ CƠ SỐ");
        lblChuyenDoi.setFont(fontBigSize);
        lblChuyenDoi.setForeground(Color.BLACK);
        lblChuyenDoi.setBounds(100, 40, 400, 30);
        panel1.add(lblChuyenDoi);

        Number_One_Layout_One = new JTextField();
        Number_One_Layout_One = createPlaceholderTextField("NHẬP SỐ CẦN CHUYỂN");
        Number_One_Layout_One.setBounds(20, 80, 320, 40);
        panel1.add(Number_One_Layout_One);

        Current_Base_System = new JTextField();
        Current_Base_System = createPlaceholderTextField("2-36");
        createLabel(panel1, "HỆ CƠ SỐ HIỆN TẠI", 100, 130);
        Current_Base_System.setBounds(20, 130, 70, 30);
        Current_Base_System.setBackground(Color.decode(FirstColorCode));
        panel1.add(Current_Base_System);

        Target_Radix_System = new JTextField();
        Target_Radix_System = createPlaceholderTextField("2-36");
        createLabel(panel1, "HỆ CƠ SỐ CẦN CHUYỂN", 100, 170);
        Target_Radix_System.setBounds(20, 170, 70, 30);
        Target_Radix_System.setBackground(Color.decode(FirstColorCode));
        panel1.add(Target_Radix_System);

        ketQuaField_Layout_One = createPlaceholderTextField("SỐ SAU KHI ĐÃ CHUYỂN");
        ketQuaField_Layout_One.setBounds(20, 210, 320, 40);
        ketQuaField_Layout_One.setEditable(false);
        ketQuaField_Layout_One.setFont(new Font("Arial", Font.BOLD, 19));
        ketQuaField_Layout_One.setBackground(Color.decode(FirstColorCode));
        panel1.add(ketQuaField_Layout_One);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(0, 260, 550, 2);
        panel1.add(separator1);

        BtConvert = new JButton("Chuyển đổi");
        BtConvert.setBounds(100, 280, 130, 30);
        BtConvert.setBackground(Color.decode(FirstColorCode));
        BtConvert.setFont(fontSmallSize);
        BtConvert.setFocusPainted(false);
        panel1.add(BtConvert);

        BtReset_Layout_One = new JButton("Thiết Lập lại");
        BtReset_Layout_One.setBounds(320, 280, 130, 30);
        BtReset_Layout_One.setBackground(Color.decode(FirstColorCode));
        BtReset_Layout_One.setFont(fontSmallSize);
        BtReset_Layout_One.setFocusPainted(false);
        panel1.add(BtReset_Layout_One);
        add(panel1);

        try {
            ImageIcon originalIcon = new ImageIcon(new URL("https://i.imgur.com/Ge5XtVz.png"));
            Image originalImage = originalIcon.getImage();

            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);

            int newWidth = 250;
            int newHeight = (originalHeight * newWidth) / originalWidth;

            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel logoLabel = new JLabel(scaledIcon);
            logoLabel.setBounds(150, 330, newWidth, newHeight);
            panel1.add(logoLabel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể tải hình ảnh: " + e.getMessage());
        }

        return panel1;

    }

    private JPanel addControls_Layout_Two() {
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(Color.WHITE);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        lblCalculate = new JLabel("PHÉP TÍNH HỆ CƠ SỐ");
        lblCalculate.setFont(fontBigSize);
        lblCalculate.setForeground(Color.BLACK);
        lblCalculate.setBounds(100, 20, 400, 30);
        panel2.add(lblCalculate);

        Number_One_Layout_Two = new JTextField();
        Number_One_Layout_Two = createPlaceholderTextField("NHẬP SỐ THỨ NHẤT");
        Number_One_Layout_Two.setBounds(130, 60, 320, 40);
        panel2.add(Number_One_Layout_Two);

        First_Radix_System = new JTextField(3);
        First_Radix_System = createPlaceholderTextField("2-36");
        createLabel(panel2, "HỆ CƠ SỐ SỐ THỨ NHẤT", 230, 110);
        First_Radix_System.setBounds(380, 110, 70, 30);
        First_Radix_System.setBackground(Color.decode(FirstColorCode));
        panel2.add(First_Radix_System);

        Operation = new JComboBox<>(new String[] { "+", "-", "*", "/" });
        createLabel(panel2, "CHỌN PHÉP TÍNH", 210, 150);
        Operation.setBounds(130, 150, 70, 30);
        Operation.setBackground(Color.decode(FirstColorCode));
        Operation.setRenderer(renderer);
        panel2.add(Operation);

        Number_Two_Layout_Two = new JTextField();
        Number_Two_Layout_Two = createPlaceholderTextField("NHẬP SỐ THỨ HAI");
        Number_Two_Layout_Two.setBounds(130, 235, 320, 40);
        Number_Two_Layout_Two.setBackground(Color.decode(FirstColorCode));
        panel2.add(Number_Two_Layout_Two);

        Second_Base_System = new JTextField(3);
        createLabel(panel2, "HỆ CƠ SỐ SỐ THỨ HAI", 240, 190);
        Second_Base_System = createPlaceholderTextField("2-36");
        Second_Base_System.setBounds(380, 190, 70, 30);
        Second_Base_System.setBackground(Color.decode(FirstColorCode));
        panel2.add(Second_Base_System);

        Result_Radix_System = new JTextField(3);
        createLabel(panel2, "HỆ CƠ SỐ CỦA KẾT QUẢ", 230, 290);
        Result_Radix_System = createPlaceholderTextField("2-36");
        Result_Radix_System.setBounds(380, 290, 70, 30);
        Result_Radix_System.setBackground(Color.decode(FirstColorCode));
        panel2.add(Result_Radix_System);

        ketQuaField_Layout_Two = new JTextField();
        ketQuaField_Layout_Two = createPlaceholderTextField("SỐ ĐÃ THỰC HIỆN PHÉP TÍNH");
        ketQuaField_Layout_Two.setBounds(130, 340, 320, 40);
        ketQuaField_Layout_Two.setEditable(false);
        ketQuaField_Layout_Two.setFont(new Font("Arial", Font.BOLD, 19));
        ketQuaField_Layout_Two.setBackground(Color.decode(FirstColorCode));
        // ketQuaField_Layout_Two.setForeground(Color.BLACK);
        panel2.add(ketQuaField_Layout_Two);

        BtCalculate = new JButton("Tính Toán");
        BtCalculate.setBounds(100, 390, 120, 30);
        BtCalculate.setBackground(Color.decode(FirstColorCode));
        BtCalculate.setFont(fontSmallSize);
        BtCalculate.setFocusPainted(false);
        panel2.add(BtCalculate);

        BtReset_Layout_Two = new JButton("Thiết Lập lại");
        BtReset_Layout_Two.setBounds(320, 390, 130, 30);
        BtReset_Layout_Two.setBackground(Color.decode(FirstColorCode));
        BtReset_Layout_Two.setFont(fontSmallSize);
        BtReset_Layout_Two.setFocusPainted(false);
        panel2.add(BtReset_Layout_Two);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 283, 550, 2);
        panel2.add(separator);

        // Thêm logo
        try {
            ImageIcon originalIcon = new ImageIcon(new URL("https://i.imgur.com/Ge5XtVz.png"));
            Image originalImage = originalIcon.getImage();

            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);

            int newWidth = 170;
            int newHeight = (originalHeight * newWidth) / originalWidth;

            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel logoLabel = new JLabel(scaledIcon);
            logoLabel.setBounds(200, 440, newWidth, newHeight);
            panel2.add(logoLabel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể tải hình ảnh: " + e.getMessage());
        }

        lblBanQuyen = new JLabel("PHẦN MỀM ĐƯỢC VIẾT BỞI DLP TEAM");
        lblBanQuyen.setFont(fontSmallSize);
        lblBanQuyen.setForeground(Color.BLACK);
        lblBanQuyen.setBounds(110, 500, 550, 130);
        panel2.add(lblBanQuyen);
        add(panel2);
        return panel2;

    }

    private void updateButtonColors(JButton button1, JButton button2, String currentPanel) {
        if ("Panel 1".equals(currentPanel)) {
            button1.setBackground(Color.WHITE);
            button1.setFont(fontSmallSize);
            button1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            button1.setFocusPainted(false);

            button2.setBackground(Color.WHITE);
            button2.setFont(fontSmallSize);
            button2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            button2.setFocusPainted(false);

        } else if ("Panel 2".equals(currentPanel)) {
            button1.setBackground(Color.WHITE);
            button1.setFont(fontSmallSize);
            button1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            button1.setFocusPainted(false);

            button2.setBackground(Color.WHITE);
            button2.setFont(fontSmallSize);
            button2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
    }

    private JTextField createPlaceholderTextField(String placeholder) {
        PlaceholderTextField textField = new PlaceholderTextField(placeholder, fontSmallSize);
        textField.setBackground(Color.decode(FirstColorCode));
        return textField;
    }

    private void createLabel(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 400, 30);
        panel.add(label);
    }

    private void addEvents() {
        // layout1
        BtReset_Layout_One.addActionListener(e -> resetFieldsLayoutOne());
        BtConvert.addActionListener(e -> {
            String numConvertText = Number_One_Layout_One.getText();
            String Current_Base_System_Text = Current_Base_System.getText();
            String Target_Radix_System_Text = Target_Radix_System.getText();

            if (numConvertText.equals("NHẬP SỐ CẦN CHUYỂN") || numConvertText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int numConvert = Integer.parseInt(numConvertText);
                int CurrentBaseSystem = Integer.parseInt(Current_Base_System_Text);
                int TargetRadixSystem = Integer.parseInt(Target_Radix_System_Text);

                if (CurrentBaseSystem < 2 || CurrentBaseSystem > 36 || TargetRadixSystem < 2
                        || TargetRadixSystem > 36) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị hợp lệ cho hệ cơ số (từ 2 đến 36).",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ketQuaField_Layout_One.setText(result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ trong hệ cơ số đã chọn!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        });

        // layout2
        BtReset_Layout_Two.addActionListener(e -> resetFieldsLayoutTwo());
        BtCalculate.addActionListener(e -> {
            String numOneText = Number_One_Layout_Two.getText();
            String numTwoText = Number_Two_Layout_Two.getText();
            String firstRadixText = First_Radix_System.getText();
            String secondRadixText = Second_Base_System.getText();
            String resultRadixText = Result_Radix_System.getText();
            int operation = Operation.getSelectedIndex();

            if (numOneText.equals("NHẬP SỐ THỨ NHẤT") || numTwoText.equals("NHẬP SỐ THỨ HAI")
                    || numOneText.trim().isEmpty() || numTwoText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Chuyển đổi hệ cơ số từ String sang int
                int firstRadix = Integer.parseInt(firstRadixText);
                int secondRadix = Integer.parseInt(secondRadixText);
                int resultRadix = Integer.parseInt(resultRadixText);

                if (firstRadix < 2 || firstRadix > 36 || secondRadix < 2 || secondRadix > 36 || resultRadix < 2
                        || resultRadix > 36) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị hợp lệ cho hệ cơ số (từ 2 đến 36).",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Integer.parseInt(numOneText, firstRadix);
                    Integer.parseInt(numTwoText, secondRadix);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ trong hệ cơ số đã chọn!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String print_result = Calculator_Algorithm(numOneText, firstRadix,
                        numTwoText, secondRadix, resultRadix,
                        operation);
                ketQuaField_Layout_Two.setForeground(Color.BLACK);
                ketQuaField_Layout_Two.setText(print_result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ trong hệ cơ số đã chọn!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        });
    }

    private void resetFieldsLayoutOne() {
        if (Number_One_Layout_One instanceof PlaceholderTextField) {
            ((PlaceholderTextField) Number_One_Layout_One).reset();
        } else {
            Number_One_Layout_One.setText("");
        }

        Current_Base_System.setText("");
        Target_Radix_System.setText("");
        Current_Base_System.setText("2-36");
        Target_Radix_System.setText("2-36");
        ketQuaField_Layout_One.setText("SỐ ĐÃ CHUYỂN");
        ketQuaField_Layout_One.setForeground(Color.GRAY);

    }

    private void resetFieldsLayoutTwo() {
        Number_One_Layout_Two.setText("NHẬP SỐ THỨ NHẤT");
        Number_One_Layout_Two.setForeground(Color.GRAY);

        Number_Two_Layout_Two.setText("NHẬP SỐ THỨ HAI");
        Number_Two_Layout_Two.setForeground(Color.GRAY);

        ketQuaField_Layout_Two.setText("");
        First_Radix_System.setText("2-36");
        Second_Base_System.setText("2-36");
        Result_Radix_System.setText("2-36");

        Operation.setSelectedIndex(0);

        ketQuaField_Layout_Two.setText("SỐ ĐÃ THỰC HIỆN PHÉP TÍNH");
        ketQuaField_Layout_Two.setForeground(Color.GRAY);

        resetPlaceholder(Number_One_Layout_Two);
        resetPlaceholder(Number_Two_Layout_Two);
        resetPlaceholder(First_Radix_System);
        resetPlaceholder(Second_Base_System);
        resetPlaceholder(Result_Radix_System);
        resetPlaceholder(ketQuaField_Layout_Two);
    }

    private void resetPlaceholder(JTextField textField) {
        if (textField instanceof PlaceholderTextField) {
            PlaceholderTextField pf = (PlaceholderTextField) textField;
            pf.setText(pf.getPlaceholder());
            pf.setForeground(Color.GRAY);
        } else {
            textField.setText("");
        }
    }

    public void showWindow() {
        this.setSize(550, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        BaseSystemCalculator_Layout display = new BaseSystemCalculator_Layout("Máy Tinh Hệ Cơ Số");
        display.showWindow();
    }

    // Hiển thị chử trong JTexx
    public class PlaceholderTextField extends JTextField {

        private final String placeholder;

        public PlaceholderTextField(String placeholder, Font font) {
            this.placeholder = placeholder;
            setForeground(Color.GRAY);
            setText(placeholder);
            setHorizontalAlignment(JTextField.RIGHT);
            setFont(font);

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(placeholder)) {
                        setForeground(Color.BLACK);
                        setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setForeground(Color.GRAY);
                        setText(placeholder);
                    }
                }
            });
        }

        // Phương thức reset
        public void reset() {
            setForeground(Color.GRAY);
            setText(placeholder); // Khôi phục placeholder
        }

        public String getPlaceholder() {
            return placeholder;
        }
    }

    public static String Calculator_Algorithm(String first_number, int first_base_number,
            String second_number, int second_base_number, int final_base_number,
            int operation) {

        int Dec_First_Number = Integer.parseInt(first_number, first_base_number);
        String first_number_result = Integer.toString(Dec_First_Number, 10);
        int Dec_Second_Number = Integer.parseInt(second_number, second_base_number);
        String second_number_result = Integer.toString(Dec_Second_Number, 10);
        String result;
        int Addition, Subtraction, Multiplication;
        switch (operation) {
            case 0:// +
                Addition = Integer.parseInt(first_number_result) + Integer.parseInt(second_number_result);
                result = Integer.toString(Addition, final_base_number);
                return result;
            case 1:// -
                Subtraction = Integer.parseInt(first_number_result) - Integer.parseInt(second_number_result);
                result = Integer.toString(Subtraction, final_base_number);
                return result;
            case 2:// x
                Multiplication = Integer.parseInt(first_number_result) * Integer.parseInt(second_number_result);
                result = Integer.toString(Multiplication, final_base_number);
                return result;
            case 3:// :
                if (Dec_Second_Number == 0) {
                    JOptionPane.showMessageDialog(null, "phép tính không thể chia cho 0");
                    return null;
                }
                if (Dec_First_Number % Dec_Second_Number != 0) {
                    JOptionPane.showMessageDialog(null, "Phép toán không thể chia hết");
                    return null;
                } else {
                    int integer_division = Dec_First_Number / Dec_Second_Number;
                    result = Integer.toString(integer_division, final_base_number);
                    return result;
                }
            default:
                JOptionPane.showMessageDialog(null, "Phép toán không hợp lệ");
                return null;
        }
    }
}
