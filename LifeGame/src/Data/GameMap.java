package Data;

import java.util.Collection;
import java.util.Random;

public class GameMap {
    //常量
    public static final int DEAD = 0;
    public static final int LIVE = 1;
    public static final int ROW = 70;
    public static final int COL = 70;

    //细胞数组
    private static int[][] cellMap;

    //初始化游戏地图
    public GameMap(){
        cellMap = new int[ROW][COL];
        for(int i = 0;i<ROW;i++){
            for(int j = 0;j<COL;j++){
                cellMap[i][j] = DEAD;
            }
        }
    }

    //随机生成细胞
    public void reset(){
        Random rand = new Random();
        int row,col;
        int count = rand.nextInt(ROW*ROW/4)+ROW*ROW/4;
        for(int i = 0;i<count;i++){
            row = rand.nextInt(ROW);
            col = rand.nextInt(COL);
            cellMap[row][col] = LIVE;
        }
    }

    //计算周围细胞数
    public int get_neighbor_count(int row,int col){
        int num=0;
        //左边
        if(col!=0){
            num+=cellMap[row][col-1];
        }
        //左上角
        if(row!=0&&col!=0){
            num+=cellMap[row-1][col-1];
        }
        //上边
        if(row!=0){
            num+=cellMap[row-1][col];
        }
        //右上
        if(row!=0&&col!=COL-1){
            num+=cellMap[row-1][col+1];
        }
        //右边
        if(col!=COL-1){
            num+=cellMap[row][col+1];
        }
        //右下
        if(row!=ROW-1&&col!=COL-1){
            num+=cellMap[row+1][col+1];
        }
        //下边
        if(row!=ROW-1){
            num+=cellMap[row+1][col];
        }
        //左下
        if(col!=0&&row!=ROW-1){
            num+=cellMap[row+1][col-1];
        }
        return num;
    }

    //修改地图元素
    public void setMap(int row,int col,int val){
        this.cellMap[row][col] = val;
    }

    //获取地图元素
    public int getMap(int row,int col){
        return this.cellMap[row][col];
    }

    public  static int[][] getCellMap() {
        return cellMap;
    }

    //判断地图是否为空
    public boolean isAllDead(){
        for(int i =0;i<ROW;i++){
            for(int j = 0;j<COL;j++){
                if(cellMap[i][j] == LIVE) {
                    return false;
                }
            }
        }
        return true;
    }

    //清空地图
    public void clearMap(){
        for(int i = 0;i<ROW;i++){
            for(int j =0;j< COL;j++){
                cellMap[i][j] = DEAD;
            }
        }
    }

}
