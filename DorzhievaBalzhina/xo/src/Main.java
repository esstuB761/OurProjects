import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Запускаем игру");
        JFrame window = new JFrame("Крестики-Нолики");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400,400);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        TicTac game = new TicTac();
        window.add(game);
        System.out.println("Конец игры");
    }

}
