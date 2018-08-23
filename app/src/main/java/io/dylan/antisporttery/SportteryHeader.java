package io.dylan.antisporttery;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class SportteryHeader implements ItemObject, Comparable<SportteryHeader> {

    private Date deadLine;

    private int matchCount = 0;

    public SportteryHeader(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    @Override
    public int getItemViewType() {
        return TYPE_HEADER;
    }

    public void incraseMatchCount() {
        ++matchCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportteryHeader that = (SportteryHeader) o;
        return Objects.equals(deadLine, that.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deadLine);
    }

    @Override
    public int compareTo(@NonNull SportteryHeader o) {
        return this.deadLine.compareTo(o.deadLine);
    }
}
