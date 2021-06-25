package Logic;
import Data.*;

public class GameLogic {
    private int[][] logicMap;
    private GameMap gameMap;
    public GameLogic(){
        gameMap = new GameMap();
    }

    //更新游戏地图，进行循环
    public void game_cycle(){
        logicMap = new int[GameMap.ROW][GameMap.COL];
        int neighbors;
        for(int i =0;i<GameMap.ROW;i++){
            for(int j = 0;j<GameMap.COL;j++){
                neighbors = gameMap.get_neighbor_count(i,j);

                //周围三个细胞就活
                if(neighbors == 3){
                    logicMap[i][j] = GameMap.LIVE;
                }

                //周围两个细胞就不修改
                else if(neighbors == 2){
                    logicMap[i][j] = gameMap.getMap(i,j);
                }

                //其他情况就是死
                else{
                    logicMap[i][j] = GameMap.DEAD;
                }
            }
        }

        for(int i = 0;i<GameMap.ROW;i++){
            for(int j = 0;j<GameMap.COL;j++){
                gameMap.setMap(i,j,logicMap[i][j]);
            }
        }
    }

    public int  getGameMap(int row,int col) {
        return gameMap.getMap(row,col);
    }

    public void setGameMap(int row,int col,int val) {
        gameMap.setMap(row,col,val);
    }

    public void reset(){
        gameMap.reset();
    }

    public void clearMap(){
        gameMap.clearMap();
    }

    //判断是否全死
    public boolean isAllDead() {
        return gameMap.isAllDead();
    }
}
