import static org.junit.Assert.*;
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

    // Add more tests here!
    // Come up with varied cases
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

}
