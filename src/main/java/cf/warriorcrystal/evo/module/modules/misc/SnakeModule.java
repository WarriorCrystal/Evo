package cf.warriorcrystal.evo.module.modules.misc;

import java.awt.EventQueue;
import javax.swing.JFrame;

import cf.warriorcrystal.evo.module.Module;
import cf.warriorcrystal.evo.util.snake.Board;

public class SnakeModule extends Module {
    public SnakeModule() {
        super("Snake", Category.MISC);
    }

    public void onEnable(){
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
        disable();
    }

    public class Snake extends JFrame {

        public Snake() {

            initUI();
        }

        private void initUI() {

            add(new Board());

            setResizable(false);
            pack();

            setTitle("Evo Snake");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
}
