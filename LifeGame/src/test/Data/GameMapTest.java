package test.Data; 

import Data.GameMap;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;


/** 
* GameMap Tester. 
* 
* @author <Authors name> 
* @since <pre>5ÔÂ 18, 2021</pre> 
* @version 1.0 
*/ 
public class GameMapTest { 
    GameMap gameMap = new GameMap();
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
* Method: reset() 
* 
*/ 
@Test
public void testReset() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: get_neighbor_count(int row, int col) 
* 
*/ 
@Test
public void testGet_neighbor_count() throws Exception { 
//TODO: Test goes here...
    gameMap.setMap(3,2,1);
    gameMap.setMap(3,4,1);
    gameMap.setMap(4,4,1);
    gameMap.setMap(2,2,1);
    gameMap.setMap(2,3,1);
    gameMap.setMap(4,3,1);
    gameMap.setMap(2,4,1);
    gameMap.setMap(4,2,1);
    assertEquals(gameMap.get_neighbor_count(3,3),8);

} 

/** 
* 
* Method: setMap(int row, int col, int val) 
* 
*/ 
@Test
public void testSetMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getMap(int row, int col) 
* 
*/ 
@Test
public void testGetMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCellMap() 
* 
*/ 
@Test
public void testGetCellMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: isAllDead() 
* 
*/ 
@Test
public void testIsAllDead() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: clearMap() 
* 
*/ 
@Test
public void testClearMap() throws Exception { 
//TODO: Test goes here... 
} 


} 
