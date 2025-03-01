package manager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        User[] usrs = new User[2];
        usrs[0] = new User("mike");
        usrs[1] = new User("tanushi");
        usrs[0].addTsk(new Task("Buy darts"));
        usrs[0].addTsk(new Task("Finish homework"));
        usrs[1].addTsk(new Task("Call mudder"));
        
        for (User user : usrs) {
            System.out.println("User: " + user.getNm());
            System.out.println("Tasks:");
            System.out.println(user.getTskLst());
            System.out.println("---------------------");
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frm = new JFrame("To Do List Manager");
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frm.setUndecorated(true);
            frm.add(new GUI(usrs));
            frm.setVisible(true);
        });
    }
}
