package com.CrossCountry.Survey.modelvo.wangkun;

import java.util.List;

/**
 * @auther wangkun
 * @date 2018/12/31 14:39
 */
public class WarnFigureListVO {
    private List<WarnParamVO> todayFigure ;
    private List<WarnParamVO> yesdayFigure ;

    public List<WarnParamVO> getTodayFigure() {
        return todayFigure;
    }

    public void setTodayFigure(List<WarnParamVO> todayFigure) {
        this.todayFigure = todayFigure;
    }

    public List<WarnParamVO> getYesdayFigure() {
        return yesdayFigure;
    }

    public void setYesdayFigure(List<WarnParamVO> yesdayFigure) {
        this.yesdayFigure = yesdayFigure;
    }
}
