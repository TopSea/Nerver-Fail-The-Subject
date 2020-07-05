package pupil.math;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pupil.math.PupilMath.*;

public class GUI extends JFrame implements ActionListener {

    private JButton jb1, jb2, jb3;
    private JPanel jp1, jp2;
    private JLabel[] jl;
    private JLabel jl1;
    private JTextField[] jt;
    private final Font font = new Font("宋体", Font.BOLD, 14);
    private final Font font1 = new Font("宋体", Font.BOLD, 16);
    private static final String[] answers = new String[10];

    public static void main(String[] args) {
        GUI gui = new GUI();
    }

    public GUI() {
        jp1 = new JPanel(new GridLayout(5,4,3,3));
        jp2 = new JPanel();

        jl = new JLabel[10];
        jt = new JTextField[10];
        for(int i = 0; i < jl.length; i++) {
            int result = randNum();
            String formula = randMulDvi(6, result, String.valueOf(result));
            jl[i] = new JLabel(splitFormula(formula), SwingConstants.CENTER);
            jl[i].setFont(font);
            jp1.add(jl[i]);
            answers[i] = splitResult((formula));
            jt[i] = new JTextField();
            jp1.add(jt[i]);
        }

        jb1 = new JButton("清除答案");
        jb1.addActionListener(this);
        jb1.setActionCommand("clear");
        jb2 = new JButton("参考答案");
        jb2.addActionListener(this);
        jb2.setActionCommand("answers");
        jb3 = new JButton("新的题目");
        jb3.addActionListener(this);
        jb3.setActionCommand("new");

        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);

        jl1 = new JLabel("请认真作答:");
        jl1.setFont(font1);
        this.add(jl1, BorderLayout.NORTH);
        this.add(jp1);
        this.add(jp2, BorderLayout.SOUTH);
        this.setIconImage(new ImageIcon("D:\\IdeaProjects\\DBDesign\\点赞.png").getImage());
        this.setSize(750, 300);
        this.setVisible(true);
        this.setTitle("Formulas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "clear":
                for (int i = 0; i < jt.length; i++) {
                    jt[i].setText("");
                }
                break;
            case "answers":
                for (int i = 0; i < jt.length; i++) {
                    if (jt[i].getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(this, "还没有做完，不能查看参考答案哦 ^-^ ");

                        break;
                    } else {
                        if (jt[i].getText().trim().equals(answers[i])) {
                            jt[i].setText(jt[i].getText().trim() + "  " + answers[i]);
                            jt[i].setForeground(Color.GREEN);
                        } else {
                            jt[i].setText(jt[i].getText().trim() + "  " + answers[i]);
                        }
                    }
                }
                break;
            case "new":
                for (int i = 0; i < jl.length; i++) {
                    int result = randNum();
                    String formula = randMulDvi(6, result, String.valueOf(result));
                    jl[i].setText(splitFormula(formula));
                    jl[i].setFont(font);
                    answers[i] = splitResult((formula));
                }
                break;
            default:
                break;
        }
    }
}
