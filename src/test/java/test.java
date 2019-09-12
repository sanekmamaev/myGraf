import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class test {
    private Graf graf = new Graf();

    private Set<String> setOfPeak(String... peak) {
        Set<String> setOfPeak = new HashSet<>();
        Collections.addAll(setOfPeak, peak);
        return setOfPeak;
    }

    private Set<Graf> setOfArc(Graf... arcs) {
        Set<Graf> setOfArc = new HashSet<>();
        Collections.addAll(setOfArc, arcs);
        return setOfArc;
    }


    @Test
    public void peakAddAndRemove() {
        graf.peakAdd("A");
        graf.peakAdd("B");
        assertEquals(graf.getSetPeaks(), setOfPeak("A", "B"));
        graf.peakAdd("C");
        assertEquals(setOfPeak("A", "B", "C"), graf.getSetPeaks());
        graf.removePeak("B");
        assertEquals(setOfPeak("A", "C"), graf.getSetPeaks());
        graf.removePeak("F");
        assertEquals(setOfPeak("A", "C"), graf.getSetPeaks());
    }

    @Test
    public void arcAddAndRemove() {
        graf.peakAdd("A");
        graf.peakAdd("B");
        graf.arcAdd("A", "B", 5);
        graf.arcAdd("B", "A", 7);
        assertEquals(graf.getSetArc(), setOfArc(new Graf("A", "B", 5), new Graf("B", "A", 7)));
        graf.removeArcWithArc(new Graf("A", "B", 5));
        assertEquals(setOfArc(new Graf("B", "A", 7)), graf.getSetArc());
        graf.arcAdd("A", "B", 8);
        graf.removeArcWithPeak("A", "B", 8);
        assertEquals(setOfArc(new Graf("B", "A", 7)), graf.getSetArc());
    }

    @Test
    public void renamePeak() {
        graf.peakAdd("A");
        graf.peakAdd("B");
        graf.arcAdd("A", "B", 5);
        graf.renamePeak("A", "C");
        assertEquals(setOfPeak("C", "B"), graf.getSetPeaks());
        assertEquals(graf.getSetArc(), setOfArc(new Graf("C", "B", 5)));
    }

    @Test
    public void changeWeightArc() {
        graf.peakAdd("A");
        graf.peakAdd("B");
        graf.arcAdd("A", "B", 5);
        graf.transformWeightArc("A", "B", 8);
        assertEquals(setOfArc(new Graf("A", "B", 8)), graf.getSetArc());
    }

    @Test
    public void arcOut() {
        graf.peakAdd("A");
        graf.peakAdd("B");
        graf.peakAdd("C");
        graf.arcAdd("A", "B", 5);
        graf.arcAdd("A", "C", 7);
        graf.arcAdd("B", "C", 5);
        assertEquals(graf.arcsOut("A"), setOfArc(new Graf("A", "B", 5), new Graf("A", "C", 7)));
        assertEquals(graf.arcsOut("B"), setOfArc(new Graf("B", "C", 5)));
    }

    @Test
    public void arcIn() {
        graf.peakAdd("A");
        graf.peakAdd("B");
        graf.peakAdd("C");
        graf.arcAdd("A", "B", 5);
        graf.arcAdd("A", "C", 7);
        graf.arcAdd("B", "C", 5);
        assertEquals(graf.arcsIn("C"), setOfArc(new Graf("A", "C", 7), new Graf("B", "C", 5)));
        assertEquals(graf.arcsIn("B"), setOfArc( new Graf("A", "B", 5)));
    }

    @Test
    public void getWeight() {
        graf.peakAdd("A");
        graf.peakAdd("B");
        graf.arcAdd("A", "B", 7);
        assertTrue(7 == graf.getWeight("A", "B"));
    }
}
