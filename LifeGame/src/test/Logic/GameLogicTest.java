package test.Logic; 

import Logic.GameLogic;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import Data.GameMap;

/** 
* GameLogic Tester. 
* 
* @author <Authors name> 
* @since <pre>5ÔÂ 18, 2021</pre> 
* @version 1.0 
*/ 
public class GameLogicTest { 
GameLogic gamelogic = new GameLogic();
@Before
public void before() throws Exception {
    System.out.println("test start");
} 

@After
public void after() throws Exception {
    System.out.println("test end");
} 

/** 
* 
* Method: game_cycle() 
* 
*/ 
@Test
public void testGame_cycle() throws Exception { 
//TODO: Test goes here...
    gamelogic.setGameMap(4,5,1);
    gamelogic.setGameMap(5,4,1);
    gamelogic.setGameMap(6,6,1);
    gamelogic.game_cycle();
    int[][] arr = new int[70][70];
    arr[5][5] = 1;
    assertEquals(GameMap.getCellMap(),arr);

}

/** 
* 
* Method: reset() 
* 
*/ 
@Test
public void testReset() throws Exception { 
//TODO: Test goes here...
    int[][] arr = GameMap.getCellMap();
    int[][] arr1 = new int[70][70];
    assertEquals(arr1,arr);
    gamelogic.reset();
    arr = GameMap.getCellMap();
    assertNotEquals(arr1,arr);
} 

/** 
* 
* Method: clearMap() 
* 
*/ 
@Test
public void testClearMap() throws Exception { 
//TODO: Test goes here...
    gamelogic.reset();
    int[][] arr = new int[70][70];
    assertNotEquals(arr,GameMap.getCellMap());
    gamelogic.clearMap();
    assertEquals(GameMap.getCellMap(),arr);

} 

/** 
* 
* Method: isAllDead() 
* 
*/ 
@Test
public void testIsAllDead() throws Exception { 
//TODO: Test goes here...
    gamelogic.reset();
    assertEquals(gamelogic.isAllDead(),false);
    gamelogic.clearMap();
    assertEquals(gamelogic.isAllDead(),true);

} 


} 
