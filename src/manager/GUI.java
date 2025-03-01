package manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JPanel {
    private ArrayList<User> userList;
    private JComboBox<String> cbUsr;
    private DefaultListModel<String> lmTsk;
    private JList<String> listTsk;
    private JTextField tfTsk;
    private JButton btnAdd, btnMark, btnDel, btnExit;
    private JLabel animLbl;
    private Timer logoTimer;
    private int animX;
    private JTextField tfUser;
    private JButton btnAddUser;
    private JFrame logFrame;
    private JTextArea logArea;
    private JLabel charLbl;
    private Timer charTimer;
    private int charX;

    public GUI(User[] u) {
        userList = new ArrayList<>(Arrays.asList(u));
        setOpaque(false);
        setLayout(new BorderLayout());
        setupLogWindow();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        animLbl = new JLabel("To Do Manager", SwingConstants.LEFT);
        animLbl.setForeground(Color.BLACK);
        animLbl.setFont(new Font("Verdana", Font.BOLD, 26));
        animLbl.setVisible(false);

        JPanel animPanel = new JPanel(null);
        animPanel.setOpaque(false);
        animPanel.setPreferredSize(new Dimension(0, 40));
        animLbl.setSize(animLbl.getPreferredSize());
        animPanel.add(animLbl);

        charLbl = new JLabel("Im extra af 🏃‍♂️");
        charLbl.setFont(new Font("SansSerif", Font.PLAIN, 26));
        charLbl.setSize(charLbl.getPreferredSize());
        charX = -charLbl.getWidth();
        charLbl.setLocation(charX, (animPanel.getPreferredSize().height - charLbl.getHeight()) / 2);
        animPanel.add(charLbl);

        topPanel.add(animPanel);

        cbUsr = new JComboBox<>();
        updateUserComboBox();
        cbUsr.setBackground(new Color(240, 240, 240));
        cbUsr.setForeground(Color.BLACK);
        cbUsr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cbUsr.setToolTipText("Select a user (change default users in Main.java)");
        cbUsr.addActionListener(e -> {
            updateTaskList();
            log("User switched to " + cbUsr.getSelectedItem());
        });
        JPanel cbPanel = new JPanel();
        cbPanel.setOpaque(false);
        cbPanel.add(cbUsr);
        topPanel.add(cbPanel);

        JPanel userAddPanel = new JPanel();
        userAddPanel.setOpaque(false);
        userAddPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tfUser = new JTextField(15);
        tfUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfUser.setToolTipText("Enter new user's name");
        btnAddUser = new JButton("Add User");
        btnAddUser.setBackground(new Color(0, 100, 200));
        btnAddUser.setForeground(Color.WHITE);
        btnAddUser.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = tfUser.getText().trim();
                if (!userName.isEmpty()) {
                    boolean exists = false;
                    for (User u : userList) {
                        if (u.getNm().equalsIgnoreCase(userName)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        User newUser = new User(userName);
                        userList.add(newUser);
                        updateUserComboBox();
                        cbUsr.setSelectedIndex(userList.size() - 1);
                        updateTaskList();
                        log("Added new user: " + userName);
                    } else {
                        JOptionPane.showMessageDialog(GUI.this, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    tfUser.setText("");
                }
            }
        });
        userAddPanel.add(new JLabel("New User:"));
        userAddPanel.add(tfUser);
        userAddPanel.add(btnAddUser);
        topPanel.add(userAddPanel);

        add(topPanel, BorderLayout.NORTH);

        lmTsk = new DefaultListModel<>();
        listTsk = new JList<>(lmTsk);
        listTsk.setBackground(new Color(40, 40, 40));
        listTsk.setForeground(Color.WHITE);
        listTsk.setFont(new Font("Monospaced", Font.PLAIN, 18));
        JScrollPane taskScrollPane = new JScrollPane(listTsk);
        taskScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(taskScrollPane, BorderLayout.CENTER);

        JPanel botPanel = new JPanel();
        botPanel.setOpaque(false);
        botPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        tfTsk = new JTextField(30);
        tfTsk.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfTsk.setToolTipText("Enter a new task");
        btnAdd = new JButton("Add Task");
        btnMark = new JButton("Mark Completed");
        btnDel = new JButton("Delete Task");
        btnExit = new JButton("End Program");
        btnAdd.setBackground(new Color(0, 128, 0));
        btnAdd.setForeground(Color.WHITE);
        btnMark.setBackground(new Color(128, 0, 0));
        btnMark.setForeground(Color.WHITE);
        btnDel.setBackground(new Color(128, 128, 0));
        btnDel.setForeground(Color.WHITE);
        btnExit.setBackground(new Color(70, 70, 70));
        btnExit.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnMark.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnDel.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
        botPanel.add(tfTsk);
        botPanel.add(btnAdd);
        botPanel.add(btnMark);
        botPanel.add(btnDel);
        botPanel.add(btnExit);
        add(botPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskDesc = tfTsk.getText().trim();
                if (!taskDesc.isEmpty() && cbUsr.getSelectedIndex() != -1) {
                    int selectedUser = cbUsr.getSelectedIndex();
                    userList.get(selectedUser).addTsk(new Task(taskDesc));
                    tfTsk.setText("");
                    updateTaskList();
                    log("Task added: " + taskDesc);
                }
            }
        });
        btnMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedTask = listTsk.getSelectedIndex();
                if (selectedTask != -1 && cbUsr.getSelectedIndex() != -1) {
                    int selectedUser = cbUsr.getSelectedIndex();
                    userList.get(selectedUser).markTskCmplt(selectedTask);
                    updateTaskList();
                    log("Task marked completed: index " + selectedTask);
                }
            }
        });
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedTask = listTsk.getSelectedIndex();
                if (selectedTask != -1 && cbUsr.getSelectedIndex() != -1) {
                    int selectedUser = cbUsr.getSelectedIndex();
                    userList.get(selectedUser).delTsk(selectedTask);
                    updateTaskList();
                    log("Task deleted: index " + selectedTask);
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(GUI.this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    log("Program exiting by user request.");
                    System.exit(0);
                }
            }
        });

        updateTaskList();
        charTimer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                charX += 5;
                if (charX > animPanel.getWidth()) {
                    charTimer.stop();
                    animPanel.remove(charLbl);
                    animLbl.setVisible(true);
                    animX = -animLbl.getWidth();
                    logoTimer.start();
                } else {
                    charLbl.setLocation(charX, charLbl.getY());
                }
                animPanel.repaint();
            }
        });
        logoTimer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int targetX = (getWidth() - animLbl.getWidth()) / 2;
                if (animX < targetX) {
                    animX += 3;
                    if (animX >= targetX) {
                        animX = targetX;
                        animLbl.setLocation(animX, animLbl.getY());
                        logoTimer.stop();
                    } else {
                        animLbl.setLocation(animX, animLbl.getY());
                    }
                } else {
                    animLbl.setLocation(targetX, animLbl.getY());
                    logoTimer.stop();
                }
                repaint();
            }
        });
        charTimer.start();
        log("Application started.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, w, h, Color.LIGHT_GRAY);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        super.paintComponent(g);
    }

    private void setupLogWindow() {
        JFrame frame = new JFrame("Backend Log (if u dont trust it u can just check the terminal but i swear u can :)");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JLabel logHeader = new JLabel("Backend Log", SwingConstants.CENTER);
        logHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
        logHeader.setForeground(Color.BLACK);
        logHeader.setOpaque(true);
        logHeader.setBackground(Color.LIGHT_GRAY);
        logHeader.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(logHeader, BorderLayout.NORTH);
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(new Color(20, 20, 20));
        logArea.setForeground(new Color(0, 255, 0));
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.add(scrollPane, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - frame.getWidth() - 50;
        int y = screenSize.height - frame.getHeight() - 50;
        frame.setLocation(x, y);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        logFrame = frame;
    }

    private void updateTaskList() {
        lmTsk.clear();
        int selectedUser = cbUsr.getSelectedIndex();
        if (selectedUser < 0)
            return;
        String[] tasks = userList.get(selectedUser).getTskLst().split("\n");
        for (String task : tasks) {
            if (!task.isEmpty()) {
                lmTsk.addElement(task);
            }
        }
    }

    private void updateUserComboBox() {
        cbUsr.removeAllItems();
        for (User u : userList) {
            cbUsr.addItem(u.getNm());
        }
    }

    private void log(String message) {
        String timestamp = LocalTime.now().withNano(0).toString();
        String logMsg = "[" + timestamp + "] " + message;
        logArea.append(logMsg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
        System.out.println(logMsg);
    }
}
