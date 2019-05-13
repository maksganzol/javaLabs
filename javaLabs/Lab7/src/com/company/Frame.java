package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private int xM1, yM1, xV1, yV1, xM2, yM2, xV2, yV2, xM3, yM3, xV3, yV3;
    private Panel panel;
    private JLabel label0, l1, l2, l3, l4;
    private JTextField tf1,tf11, tf2,tf22, tf3,tf33, tf4,tf44;
    private JButton button;
    public Frame(){
        super("Lab7");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700, 500);
        setResizable(false);

        panel = new Panel();
        add(panel);
        label0 = new JLabel("Задано прямокутні координати вершин трикутника і точки. Визначити, чи лежить точка на однієї з медіан трикутника. ");
        panel.add(label0);
        tf1 = new JTextField("0", 2);
        tf11 = new JTextField("0", 2);
        tf2 = new JTextField("0",2);
        tf22 = new JTextField("0", 2);
        tf3 = new JTextField("0",2);
        tf33 = new JTextField("0", 2);
        tf4 = new JTextField("0",2);
        tf44 = new JTextField("0", 2);
        l1 = new JLabel("Вершина 1");
        l2 = new JLabel("Вершина 2");
        l3 = new JLabel("Вершина 3");
        l4 = new JLabel("Точка");
        button = new JButton("Draw");
        button.addActionListener(new MyActionListener());
        panel.add(l1);
        panel.add(tf1);
        panel.add(tf11);
        panel.add(l2);
        panel.add(tf2);
        panel.add(tf22);
        panel.add(l3);
        panel.add(tf3);
        panel.add(tf33);
        panel.add(l4);
        panel.add(tf4);
        panel.add(tf44);
        panel.add(button);

    }


    class Panel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.drawLine(350, 50 , 350, 500); //Рисуем оси координат
            g.drawLine(0, 250, 700, 250);
            for(int i = 0; i < 700; i+=10) //Рисуем единичные отрезки на оси ОХ
                g.drawLine(i, 248, i, 252);
            for(int i = 50; i < 700; i+=10) //Рисуем единичные отрезки на оси ОХ
                g.drawLine(348, i, 352, i);

            g.drawLine(Integer.parseInt(tf1.getText())*10 + 350, -Integer.parseInt(tf11.getText())*10 + 250, //Стророна треугольника
                       Integer.parseInt(tf2.getText())*10 + 350, -Integer.parseInt(tf22.getText())*10 + 250);
            g.drawLine(Integer.parseInt(tf1.getText())*10 + 350, -Integer.parseInt(tf11.getText())*10 + 250,
                    Integer.parseInt(tf3.getText())*10 + 350, -Integer.parseInt(tf33.getText())*10 + 250);
            g.drawLine(Integer.parseInt(tf2.getText())*10 + 350, -Integer.parseInt(tf22.getText())*10 + 250,
                    Integer.parseInt(tf3.getText())*10 + 350, -Integer.parseInt(tf33.getText())*10 + 250);


            g.setColor(Color.RED);
            g.drawRect(Integer.parseInt(tf4.getText())*10+350, -Integer.parseInt(tf44.getText())*10+250,
                    1, 1);

            g.setColor(Color.BLUE);
            if(isOnMediane(Integer.parseInt(tf4.getText()), Integer.parseInt(tf44.getText())))
                g.drawString("Dot is on mediane", 500, 300);
            else g.drawString("Dot is NOT on Mediane", 500, 300);

            g.setColor(Color.GRAY);
            g.drawLine(Integer.parseInt(tf1.getText())*10 + 350, -Integer.parseInt(tf11.getText())*10 + 250,
                    xM3*10+350, -yM3*10+250);
            g.drawLine(Integer.parseInt(tf2.getText())*10 + 350, -Integer.parseInt(tf22.getText())*10 + 250,
                    xM2*10+350, -yM2*10+250);
            g.drawLine(Integer.parseInt(tf3.getText())*10 + 350, -Integer.parseInt(tf33.getText())*10 + 250,
                    xM1*10+350, -yM1*10+250);
        }
}

    public boolean isOnMediane(int x, int y){
        xM1 = (Integer.parseInt(tf1.getText()) + Integer.parseInt(tf2.getText())) / 2; //Ищем середину первой стороны
        yM1 = (Integer.parseInt(tf11.getText()) + Integer.parseInt(tf22.getText())) / 2; //Ищес середину второй строны
        xV1 = Integer.parseInt(tf3.getText()) - xM1; //Находим первую координату вектора прямой, на которой находится медиана
        yV1 = Integer.parseInt(tf33.getText()) - yM1; //Находим втрую координату вектора

        //Аналогично, с другими сторонами треугольника
        xM2 = (Integer.parseInt(tf1.getText()) + Integer.parseInt(tf3.getText())) / 2;
        yM2 = (Integer.parseInt(tf11.getText()) + Integer.parseInt(tf33.getText())) / 2;
        xV2 = Integer.parseInt(tf2.getText()) - xM2;
        yV2 = Integer.parseInt(tf22.getText()) - yM2;

        xM3 = (Integer.parseInt(tf2.getText()) + Integer.parseInt(tf3.getText())) / 2;
        yM3 = (Integer.parseInt(tf22.getText()) + Integer.parseInt(tf33.getText())) / 2;
        xV3 = Integer.parseInt(tf1.getText()) - xM3;
        yV3 = Integer.parseInt(tf11.getText()) - yM3;
        /*
        (x-a)/A = (y-b)/B - Уравнение нахождения точки на прямой
        а, b - координаты точки, через которую проходит прямая,
        A, B - координаты вектора, который находится на прямой
        x, y - координаты искомой точки
         */
        return ((x-xM1)*yV1 == (y-yM1)*xV1 || (x-xM2)*yV2 == (y-yM2)*xV2 || (x-xM3)*yV3 == (y-yM3)*xV3);

    }

    public class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

}
