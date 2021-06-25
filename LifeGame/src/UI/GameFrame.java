package UI;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Data.*;
import Logic.*;

class GamePanel extends JPanel{
    public int[][] map;

    public int cubeHeight,cubeWidth;

    public GamePanel(GridLayout gridLayout){
        super(gridLayout);
        map = new int[GameMap.ROW][GameMap.COL];
    }
    public void paint(Graphics graphics){
//        super.paint(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        //g2d.setColor(Color.orange);
        //g2d.fillRect(0,0,100,100);
//        graphics
        cubeWidth = this.getWidth()/GameMap.COL;
        cubeHeight = this.getHeight()/GameMap.ROW;
        //画画
        for(int i=0;i<GameMap.ROW;i++){
            for(int j=0;j<GameMap.COL;j++){
                if(map[i][j]==0) {
                    g2d.setColor(Color.white);
                    g2d.fillRect(i*cubeWidth,j*cubeHeight,cubeWidth,cubeHeight);
                }
                else {
                    g2d.setColor(Color.orange);
                    g2d.fillRect(i * cubeWidth, j * cubeHeight, cubeHeight, cubeHeight);
                }
            }
        }
        g2d.setColor(Color.black);
        for(int i=0;i<GameMap.ROW+1;i++){
            g2d.drawLine(0,i*cubeHeight,GameMap.COL*cubeWidth,i*cubeHeight);
        }
        for(int i=0;i<GameMap.COL+1;i++){
            g2d.drawLine(i*cubeWidth,0,i*cubeWidth,GameMap.ROW*cubeHeight);
        }
    }
}

public class GameFrame extends JFrame{
    private GamePanel mappanel;
    private JPanel mainpanel,textpanel,textpanel1,textpanel2,textpanel11,textpanel12,textpanel13,textpanel14,hintpanel,timepanel;
//    private JButton[][] Map;
    private JSlider timeSlider;
    private  JLabel hint1,hint2,hint3,hint4,hint5,speedLabel;
    private JButton start,nextround,pause,hintbtn1,hintbtn2,hintbtn3,hintbtn4,random,clean;
    private GameLogic gameLogic;
    private static int isStart = 0;
    private final static int delay = 2000;
    private Timer schedule1;
    private int flag;

    public GameFrame(){
        setTitle("生命游戏");
        setBounds(0,0,800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Initframe();
        setVisible(true);
    }

    private void Initframe() {
        Initmainpanel();
        setContentPane(mainpanel);
        gameLogic = new GameLogic();
        schedule1 = new Timer(delay/2,new Ontime1());
    }

    private void Initmappanel(){
//        mappanel = new JPanel(new GridLayout(GameMap.ROW,GameMap.COL));
//        Map = new JButton[GameMap.ROW][GameMap.COL];
//        for(int i = 0;i<GameMap.ROW;i++){
//            for(int j = 0;j<GameMap.COL;j++){
//                Map[i][j] = new JButton();
//                Map[i][j].setBackground(Color.white);
//                Map[i][j].addActionListener(new setCellsListener(i,j));
//                mappanel.add(Map[i][j]);
//            }
//        }
        mappanel = new GamePanel(new GridLayout(GameMap.ROW,GameMap.COL));

    }



    private void Inittextpanel(){
        textpanel = new JPanel(new GridLayout(2,1));
        textpanel1=new JPanel(new GridLayout(2,1));
        textpanel2=new JPanel(new GridLayout(5,1));
        hintpanel = new JPanel(new GridLayout(1,4));

        hint1 = new JLabel("点击随机按钮或下方网格设置细胞,开始后无法设置",SwingConstants.CENTER);
        Font font = new Font("宋体",Font.BOLD,25);
        hint1.setFont(font);
        hintbtn1 = new JButton();
        hintbtn1.setBackground(Color.BLUE);
        hint2 = new JLabel("活细胞",SwingConstants.CENTER);

        hintbtn2 = new JButton();
        hintbtn2.setBackground(Color.orange);

        hint3 = new JLabel("新生细胞",SwingConstants.CENTER);

        hintbtn3 = new JButton();
        hintbtn3.setBackground(Color.white);

        hint4 = new JLabel("死细胞",SwingConstants.CENTER);

        hintbtn4 = new JButton();
        hintbtn4.setBackground(Color.GRAY);

        hint5 = new JLabel("本轮死亡细胞",SwingConstants.CENTER);

        //timeSlider
        timeSlider = new JSlider(1,50,25);
        timeSlider.setMajorTickSpacing(5);
        timeSlider.setMinorTickSpacing(1);
        timeSlider.setPaintTicks(true);

        textpanel11 = new JPanel(new GridLayout(2,1));
        textpanel12 = new JPanel(new GridLayout(2,1));
        textpanel13 = new JPanel(new GridLayout(2,1));
        textpanel14 = new JPanel(new GridLayout(2,1));

        textpanel11.add(hintbtn1);
        textpanel12.add(hintbtn2);
        textpanel13.add(hintbtn3);
        textpanel14.add(hintbtn4);


        textpanel11.add(hint2);
        textpanel12.add(hint3);
        textpanel13.add(hint4);
        textpanel14.add(hint5);

        hintpanel.add(textpanel11);
        hintpanel.add(textpanel12);
        hintpanel.add(textpanel13);
        hintpanel.add(textpanel14);

        start = new JButton("开始");
        nextround = new JButton("下次一迭代");
        pause = new JButton("暂停");
        random = new JButton("随机");
        clean = new JButton("清空地图");

        //注册监听器
        start.addActionListener(new startListener());
        random.addActionListener(new randomListener());
        pause.addActionListener(new pauseListener());
        nextround.addActionListener(new nextroundListener());
        clean.addActionListener(new cleanListener());
        timeSlider.addChangeListener(new timeSliderListener());


        //textpanel1.add(hint1);
        textpanel1.add(hintpanel);
        textpanel1.add(timeSlider);
        textpanel2.add(start);
        textpanel2.add(nextround);
        textpanel2.add(pause);
        textpanel2.add(random);
        textpanel2.add(clean);
        textpanel.add(textpanel1);
        textpanel.add(textpanel2);
    }

    private void Initmainpanel(){
        mainpanel = new JPanel(new BorderLayout());
        Inittextpanel();
        Initmappanel();



        mainpanel.add(mappanel,BorderLayout.CENTER);
        mainpanel.add(textpanel,BorderLayout.EAST);
        mainpanel.add(hint1,BorderLayout.NORTH);
    }

    //设置细胞状态
    class setCellsListener implements ActionListener{
        private int row;
        private int col;

        public setCellsListener(int row,int col){
            this.row = row;
            this.col = col;
        }
        @Override
        public void actionPerformed(ActionEvent e){
//            if(Map[row][col].getBackground() ==Color.white) {
//                Map[row][col].setBackground(Color.orange);
//                gameLogic.setGameMap(row, col, GameMap.LIVE);
//            }
//            else{
//                Map[row][col].setBackground(Color.white);
//                gameLogic.setGameMap(row, col, GameMap.DEAD);
//            }
            if(mappanel.map[row][col]==0){
                mappanel.map[row][col] =1;
                gameLogic.setGameMap(row,col,GameMap.LIVE);
            }
            else{
                mappanel.map[row][col] = 0;
                gameLogic.setGameMap(row,col,GameMap.DEAD);
            }

            mappanel.repaint();
        }
    }

    //开始游戏
    class startListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            isStart = 1;
            flag = 1;
            schedule1.start();

            //禁用按钮
            //System.out.println("1");
            start.setEnabled(false);
            random.setEnabled(false);
//            for(int i = 0;i<GameMap.ROW;i++){
//                for(int j = 0;j<GameMap.COL;j++){
//                    Map[i][j].setEnabled(false);
//                }
//            }
            //System.out.println("2");
        }
    }

    //随机生成
    class randomListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            gameLogic.clearMap();
            gameLogic.reset();
            flushMap();
        }
    }

    //下一次迭代
    class nextroundListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(isStart==1){
                isStart = 0;
                schedule1.stop();
            }
            gameLogic.game_cycle();
            flushMap();

            pause.setText("重新开始");
        }
    }

    //清空地图
    class cleanListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            gameLogic.clearMap();
            flushMap();
            start.setEnabled(true);
            random.setEnabled(true);
//            for(int i = 0;i<GameMap.ROW;i++){
//                for(int j = 0;j<GameMap.COL;j++){
//                    Map[i][j].setEnabled(true);
//                }
//            }
            schedule1.stop();
        }
    }


    //用于刷新地图
    private void flushMap(){
        for(int i = 0;i<GameMap.ROW;i++){
            for(int j = 0;j<GameMap.COL;j++){
                if(gameLogic.getGameMap(i,j) == GameMap.LIVE){
//                    Map[i][j].setBackground(Color.orange);
                    mappanel.map[i][j] = 1;
                }
                else{
//                    Map[i][j].setBackground(Color.white);
                    mappanel.map[i][j] = 0;
                }
            }
        }
        mappanel.repaint();
    }

    //衍化地图
    class Ontime1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*if(flag == 1) {
                gameLogic.game_cycle();
                for (int i = 0; i < GameMap.ROW; i++) {
                    for (int j = 0; j < GameMap.COL; j++) {
                        //如果细胞是新生的就是黄色
                        if (Color.white == Map[i][j].getBackground() && gameLogic.getGameMap(i, j) == GameMap.LIVE) {
                            Map[i][j].setBackground(Color.orange);
                        }
                        //细胞是活着的（非新生）就是蓝色
                        else if (Color.orange == Map[i][j].getBackground() || gameLogic.getGameMap(i, j) == GameMap.LIVE) {
                            Map[i][j].setBackground(Color.BLUE);
                        }
                        // 细胞是刚死的就是灰色
                        else if (Color.blue == Map[i][j].getBackground() && gameLogic.getGameMap(i, j) == GameMap.DEAD) {
                            Map[i][j].setBackground(Color.GRAY);
                        }
                        //细胞是死的就是白色
                        else if (Color.gray == Map[i][j].getBackground() || gameLogic.getGameMap(i, j) == GameMap.DEAD) {
                            Map[i][j].setBackground(Color.white);
                        }
                    }
                }
                flag = 0;
            }
            else {
                for(int i = 0;i<GameMap.ROW;i++) {
                    for (int j = 0; j < GameMap.COL; j++) {
                        //细胞是活着的（非新生）就是蓝色
                        if (Color.orange == Map[i][j].getBackground()) {
                            Map[i][j].setBackground(Color.BLUE);
                        }
                        //细胞是死的就是白色
                        else if (Color.gray == Map[i][j].getBackground()) {
                            Map[i][j].setBackground(Color.white);
                        }
                    }
                }
                flag = 1;
            }
        }*/
            gameLogic.game_cycle();
            for (int i = 0; i < GameMap.ROW; i++) {
                for (int j = 0; j < GameMap.COL; j++) {
                    if (gameLogic.getGameMap(i, j) == GameMap.LIVE) {
//                        Map[i][j].setBackground(Color.orange);
                        mappanel.map[i][j] = 1;
                    } else {
//                        Map[i][j].setBackground(Color.white);
                        mappanel.map[i][j] = 0;
                    }
                }
            }
            mappanel.repaint();
        }
    }

    class pauseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isStart == 1) {
                schedule1.stop();
                pause.setText("重新开始");
                isStart = 0;
            }else{
                schedule1.restart();
                pause.setText("暂停");
                isStart = 1;
            }
        }
    }

    class timeSliderListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e){
            schedule1.setDelay(delay/timeSlider.getValue());
        }
    }


}




