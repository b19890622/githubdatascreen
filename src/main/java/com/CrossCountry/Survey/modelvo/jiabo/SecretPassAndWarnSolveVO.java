package com.CrossCountry.Survey.modelvo.jiabo;

import java.util.List;

public class SecretPassAndWarnSolveVO {
    private List<CircularEntity> circularPoList;
    private List<HistogramEntity> histogramPoList;

    public List<CircularEntity> getCircularPoList() {
        return circularPoList;
    }

    public void setCircularPoList(List<CircularEntity> circularPoList) {
        this.circularPoList = circularPoList;
    }

    public List<HistogramEntity> getHistogramPoList() {
        return histogramPoList;
    }

    public void setHistogramPoList(List<HistogramEntity> histogramPoList) {
        this.histogramPoList = histogramPoList;
    }
}
