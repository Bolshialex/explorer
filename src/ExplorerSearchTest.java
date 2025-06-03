import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }
    
    @Test
    public void testReachableArea_noneUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,2,0,3},
            {1,1,1,2,2,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        // Explorer is on an island
        assertEquals(1, actual);
    }

    @Test
    public void testReachableArea_allReachable() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,0,1},
            {1,1,1,1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(30, actual);
    }
    // Add more tests here!
    // Come up with varied cases


    // findExplorer Method Testing
    @Test
    public void testFindExplorer_centerOfGrid() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] expected = {1, 1};
        assertArrayEquals(expected, ExplorerSearch.findExplorer(island));
    }
        
    @Test
    public void testFindExplorer_topLeft() {
        int[][] island = {
            {0, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int[] expected = {0, 0};
        assertArrayEquals(expected, ExplorerSearch.findExplorer(island));
    }

    @Test
    public void testFindExplorer_bottomRight() {
        int[][] island = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 0}
        };
        int[] expected = {2, 2};
        assertArrayEquals(expected, ExplorerSearch.findExplorer(island));
    }


    @Test
    public void testFindExplorer_actualIsland() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int[] expected = {3, 4};
        assertArrayEquals(expected, ExplorerSearch.findExplorer(island));
    }

    @Test
    public void testFindExplorer_noExplorer() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,3,1},
            {1,1,1,2,1,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.findExplorer(island);
        });
        assertEquals("Explorer Lost. Send Help!", exception.getMessage());
    }

    // availablePath Method Testing
    @Test
    public void testAvailablePath_allDirections(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,0,1,3,1},
            {1,1,1,2,1,1},
        };
        int[] location = {3, 2};
        List<int[]> path = ExplorerSearch.availablePath(island, location);
        Set<String> pathSet = new HashSet<>();

        for (int[] pair : path) {
            pathSet.add(Arrays.toString(pair));
        }
        assertEquals(4, path.size());
        assertTrue(pathSet.contains("[2, 2]"));
        assertTrue(pathSet.contains("[3, 1]"));
        assertTrue(pathSet.contains("[3, 3]"));
        assertTrue(pathSet.contains("[4, 2]"));

    }

    @Test
    public void testAvailablePath_onePathLeft(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,2,1,3,3},
            {3,1,0,2,3,1},
            {1,1,3,2,1,1},
        };
        int[] location = {3, 2};
        List<int[]> path = ExplorerSearch.availablePath(island, location);
        Set<String> pathSet = new HashSet<>();

        for (int[] pair : path) {
            pathSet.add(Arrays.toString(pair));
        }
        assertEquals(1, path.size());
        assertTrue(pathSet.contains("[3, 1]"));
    }

    @Test
    public void testAvailablePath_onePathDown(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,2,1,3,3},
            {3,2,0,2,3,1},
            {1,1,1,2,1,1},
        };
        int[] location = {3, 2};
        List<int[]> path = ExplorerSearch.availablePath(island, location);
        Set<String> pathSet = new HashSet<>();

        for (int[] pair : path) {
            pathSet.add(Arrays.toString(pair));
        }
        assertEquals(1, path.size());
        assertTrue(pathSet.contains("[4, 2]"));
    }
        
    
    @Test
    public void testAvailablePath_outOfBounds(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,2,1,3,3},
            {3,1,1,2,3,1},
            {1,1,3,2,1,0},
        };
        int[] location = {4, 5};
        List<int[]> path = ExplorerSearch.availablePath(island, location);
        Set<String> pathSet = new HashSet<>();

        for (int[] pair : path) {
            pathSet.add(Arrays.toString(pair));
        }
        assertEquals(2, path.size());
        assertTrue(pathSet.contains("[3, 5]"));
        assertTrue(pathSet.contains("[4, 4]"));
    }


        @Test
    public void testAvailablePath_noPath(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,2,1,3,3},
            {3,3,0,2,3,1},
            {1,1,3,2,1,1},
        };
        int[] location = {3, 2};
        List<int[]> path = ExplorerSearch.availablePath(island, location);
        Set<String> pathSet = new HashSet<>();

        for (int[] pair : path) {
            pathSet.add(Arrays.toString(pair));
        }
        assertEquals(0, path.size());
        assertTrue(pathSet.isEmpty());
    }

}
