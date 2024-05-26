package com.ensah.Algorithme;

public class Interval {
    private float start;
    private float end;

    public Interval(float start, float end) {
        this.start = start;
        this.end = end;
    }

    public float getStart() {
        return start;
    }

    public float getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }

    public static boolean getIntersection(Interval interval1, Interval interval2) {
    	float newStart = Math.max(interval1.getStart(), interval2.getStart());
    	float newEnd = Math.min(interval1.getEnd(), interval2.getEnd());

        if (newStart <= newEnd) {
            return true;
        } else {
            return false;
        }
    }
}
