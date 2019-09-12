import java.util.*;

public class Graf {
    private String out;
    private String in;
    private int weight;
    private Set<String> peaks = new HashSet<>();
    private Set<Graf> arcs = new HashSet<>();


    public Graf() {
    }

    public Graf(String out, String in, int weight) {
        this.out = out;
        this.in = in;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Graf) {
            Graf other =(Graf) obj;
            return this.out == other.out && this.in == other.in && this.weight == other.weight;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + out.length();
        result = prime * result + in.length();
        result = prime * result + weight;
        return result;
    }


    public void peakAdd(String name) {
        this.peaks.add(name);
    }

    public void removePeak(String name) {
        this.peaks.remove(name);
        for (Graf arc : arcs) {
            if ((arc.in == name) || (arc.out == name)) arcs.remove(arc);
        }
    }

    public void arcAdd(String out, String in, int weight) {
        if ((peaks.contains(out)) && (peaks.contains(in))) {
            Graf arcs = new Graf(out, in, weight);
            this.arcs.add(arcs); }
            else throw new IllegalArgumentException("Param cannot be illegal");
    }

    public void removeArcWithArc(Graf arc) {
        this.arcs.remove(arc);
    }

    public void removeArcWithPeak(String a, String b, int wei) {
        for (Graf arc1 : arcs)
            if (arc1.equals(new Graf(a,b,wei))) {
                this.arcs.remove(arc1);
                break;
            }
    }

    public void renamePeak(String oldName, String newName) {
        if (peaks.contains(oldName)) {
            this.peaks.remove(oldName);
            this.peaks.add(newName);
        }
        for (Graf arc : arcs) {
            if (arc.in.equals(oldName)) arc.in = newName;
            if (arc.out.equals(oldName)) arc.out = newName;
        }
    }

    public void transformWeightArc(String a, String b, int weight) {
        for (Graf arc : arcs) {
            if ((arc.in.equals(b)) && (arc.out.equals(a))) arc.weight = weight;
        }
    }

    Set arcsOut(String name) {
        Set<Graf> arcsOut = new HashSet<Graf>();
        for (Graf arc : arcs) {
            if (arc.out.equals(name)) arcsOut.add(arc);
        }
        return arcsOut;
    }

    Set arcsIn(String name) {
        Set<Graf> arcsIn = new HashSet<Graf>();
        for (Graf arc : arcs)
            if (arc.in.equals(name)) arcsIn.add(arc);
        return arcsIn;
    }

    public Integer getWeight(String A, String B) {
        for (Graf arc : arcs) if ((arc.in == B) && (arc.out == A)) return arc.weight;
        throw new IllegalArgumentException("Obj cannot be find");
    }

    public Set<Graf> getSetArc() {
        return arcs;
    }

    public Set<String> getSetPeaks() {
        return peaks;
    }
}
