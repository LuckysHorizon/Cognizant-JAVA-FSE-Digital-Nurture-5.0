package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Spy Tests")
class SpyTest {

    @Test
    void testSpyOnList() {
        List<String> realList = new ArrayList<>();
        List<String> spyList = spy(realList);

        spyList.add("One");
        spyList.add("Two");

        assertEquals(2, spyList.size());
        assertTrue(spyList.contains("One"));
        verify(spyList, times(2)).add(anyString());
    }

    @Test
    void testSpyOverrideMethod() {
        List<String> spyList = spy(new ArrayList<>());
        doReturn(100).when(spyList).size();

        spyList.add("Element");
        assertEquals(100, spyList.size());
        assertTrue(spyList.contains("Element"));
    }

    @Test
    void testSpyVerifyRealMethodCalls() {
        List<String> spyList = spy(new ArrayList<>());
        spyList.add("Alpha");
        spyList.add("Beta");
        spyList.add("Gamma");

        verify(spyList).add("Alpha");
        verify(spyList).add("Beta");
        verify(spyList).add("Gamma");
        assertEquals(3, spyList.size());
    }

    @Test
    void testSpyPartialMocking() {
        List<String> spyList = spy(new ArrayList<>());
        spyList.add("Real");
        doReturn("Mocked").when(spyList).get(0);

        assertEquals("Mocked", spyList.get(0));
        assertEquals(1, spyList.size());
    }
}
