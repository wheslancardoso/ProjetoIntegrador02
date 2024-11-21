package easyticket;
import javax.swing.SwingUtilities;

public class EasyTicketApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
