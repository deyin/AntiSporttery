package io.dylan.antisporttery.pojos;

import java.util.Date;
import java.util.List;

import io.dylan.antisporttery.utils.DateUtils;

public class Odds {

    private String id;
    private String num;
    private String date;
    private String time;
    private String bDate;
    private String status;
    private String hot;
    private String lId;
    private String lCn;
    private String hId;
    private String hCn;
    private String aId;
    private String aCn;
    private String indexShow;
    private String show;
    private Hhad hhad;
    private Had had;
    private String lCnAbbr;
    private String hCnAbbr;
    private String aCnAbbr;
    private String hOrder;
    private String aOrder;
    private String hIdDc;
    private String aIdDc;
    private String lBackgroundColor;
    private String weather;
    private String weatherCity;
    private String temperature;
    private String weatherPic;
    private List<Object> matchInfo = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBDate() {
        return bDate;
    }

    public void setBDate(String bDate) {
        this.bDate = bDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getLId() {
        return lId;
    }

    public void setLId(String lId) {
        this.lId = lId;
    }

    public String getLCn() {
        return lCn;
    }

    public void setLCn(String lCn) {
        this.lCn = lCn;
    }

    public String getHId() {
        return hId;
    }

    public void setHId(String hId) {
        this.hId = hId;
    }

    public String getHCn() {
        return hCn;
    }

    public void setHCn(String hCn) {
        this.hCn = hCn;
    }

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getACn() {
        return aCn;
    }

    public void setACn(String aCn) {
        this.aCn = aCn;
    }

    public String getIndexShow() {
        return indexShow;
    }

    public void setIndexShow(String indexShow) {
        this.indexShow = indexShow;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public Hhad getHhad() {
        return hhad;
    }

    public void setHhad(Hhad hhad) {
        this.hhad = hhad;
    }

    public Had getHad() {
        return had;
    }

    public void setHad(Had had) {
        this.had = had;
    }

    public String getLCnAbbr() {
        return lCnAbbr;
    }

    public void setLCnAbbr(String lCnAbbr) {
        this.lCnAbbr = lCnAbbr;
    }

    public String getHCnAbbr() {
        return hCnAbbr;
    }

    public void setHCnAbbr(String hCnAbbr) {
        this.hCnAbbr = hCnAbbr;
    }

    public String getACnAbbr() {
        return aCnAbbr;
    }

    public void setACnAbbr(String aCnAbbr) {
        this.aCnAbbr = aCnAbbr;
    }

    public String getHOrder() {
        return hOrder;
    }

    public void setHOrder(String hOrder) {
        this.hOrder = hOrder;
    }

    public String getAOrder() {
        return aOrder;
    }

    public void setAOrder(String aOrder) {
        this.aOrder = aOrder;
    }

    public String getHIdDc() {
        return hIdDc;
    }

    public void setHIdDc(String hIdDc) {
        this.hIdDc = hIdDc;
    }

    public String getAIdDc() {
        return aIdDc;
    }

    public void setAIdDc(String aIdDc) {
        this.aIdDc = aIdDc;
    }

    public String getLBackgroundColor() {
        return lBackgroundColor;
    }

    public void setLBackgroundColor(String lBackgroundColor) {
        this.lBackgroundColor = lBackgroundColor;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherCity() {
        return weatherCity;
    }

    public void setWeatherCity(String weatherCity) {
        this.weatherCity = weatherCity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeatherPic() {
        return weatherPic;
    }

    public void setWeatherPic(String weatherPic) {
        this.weatherPic = weatherPic;
    }

    public List<Object> getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(List<Object> matchInfo) {
        this.matchInfo = matchInfo;
    }

    public static class Hhad {

        private String a;
        private String d;
        private String h;
        private String goalline;
        private String pCode;
        private String oType;
        private String pId;
        private String pStatus;
        private String single;
        private String allup;
        private String fixedodds;
        private String cbt;
        private String _int;
        private String vbt;
        private String hTrend;
        private String aTrend;
        private String dTrend;
        private String lTrend;

        private transient boolean winSelected;
        private transient boolean drawSelected;
        private transient boolean loseSelected;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public String getGoalline() {
            return goalline;
        }

        public void setGoalline(String goalline) {
            this.goalline = goalline;
        }

        public String getPCode() {
            return pCode;
        }

        public void setPCode(String pCode) {
            this.pCode = pCode;
        }

        public String getOType() {
            return oType;
        }

        public void setOType(String oType) {
            this.oType = oType;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getPStatus() {
            return pStatus;
        }

        public void setPStatus(String pStatus) {
            this.pStatus = pStatus;
        }

        public String getSingle() {
            return single;
        }

        public void setSingle(String single) {
            this.single = single;
        }

        public String getAllup() {
            return allup;
        }

        public void setAllup(String allup) {
            this.allup = allup;
        }

        public String getFixedodds() {
            return fixedodds;
        }

        public void setFixedodds(String fixedodds) {
            this.fixedodds = fixedodds;
        }

        public String getCbt() {
            return cbt;
        }

        public void setCbt(String cbt) {
            this.cbt = cbt;
        }

        public String getInt() {
            return _int;
        }

        public void setInt(String _int) {
            this._int = _int;
        }

        public String getVbt() {
            return vbt;
        }

        public void setVbt(String vbt) {
            this.vbt = vbt;
        }

        public String getHTrend() {
            return hTrend;
        }

        public void setHTrend(String hTrend) {
            this.hTrend = hTrend;
        }

        public String getATrend() {
            return aTrend;
        }

        public void setATrend(String aTrend) {
            this.aTrend = aTrend;
        }

        public String getDTrend() {
            return dTrend;
        }

        public void setDTrend(String dTrend) {
            this.dTrend = dTrend;
        }

        public String getLTrend() {
            return lTrend;
        }

        public void setLTrend(String lTrend) {
            this.lTrend = lTrend;
        }

        public void setWinSelected(boolean winSelected) {
            this.winSelected = winSelected;
        }

        public boolean isWinSelected() {
            return winSelected;
        }

        public void setDrawSelected(boolean drawSelected) {
            this.drawSelected = drawSelected;
        }

        public boolean isDrawSelected() {
            return drawSelected;
        }

        public void setLoseSelected(boolean loseSelected) {
            this.loseSelected = loseSelected;
        }

        public boolean isLoseSelected() {
            return loseSelected;
        }
    }

    public static class Had {

        private String a;
        private String d;
        private String h;
        private String goalline;
        private String pCode;
        private String oType;
        private String pId;
        private String pStatus;
        private String single;
        private String allup;
        private String fixedodds;
        private String cbt;
        private String _int;
        private String vbt;
        private String hTrend;
        private String aTrend;
        private String dTrend;
        private String lTrend;

        private transient boolean winSelected;

        public boolean isWinSelected() {
            return winSelected;
        }

        public void setWinSelected(boolean winSelected) {
            this.winSelected = winSelected;
        }

        public boolean isDrawSelected() {
            return drawSelected;
        }

        public void setDrawSelected(boolean drawSelected) {
            this.drawSelected = drawSelected;
        }

        public boolean isLoseSelected() {
            return loseSelected;
        }

        public void setLoseSelected(boolean loseSelected) {
            this.loseSelected = loseSelected;
        }

        private transient boolean drawSelected;
        private transient boolean loseSelected;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public String getGoalline() {
            return goalline;
        }

        public void setGoalline(String goalline) {
            this.goalline = goalline;
        }

        public String getPCode() {
            return pCode;
        }

        public void setPCode(String pCode) {
            this.pCode = pCode;
        }

        public String getOType() {
            return oType;
        }

        public void setOType(String oType) {
            this.oType = oType;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getPStatus() {
            return pStatus;
        }

        public void setPStatus(String pStatus) {
            this.pStatus = pStatus;
        }

        public String getSingle() {
            return single;
        }

        public void setSingle(String single) {
            this.single = single;
        }

        public String getAllup() {
            return allup;
        }

        public void setAllup(String allup) {
            this.allup = allup;
        }

        public String getFixedodds() {
            return fixedodds;
        }

        public void setFixedodds(String fixedodds) {
            this.fixedodds = fixedodds;
        }

        public String getCbt() {
            return cbt;
        }

        public void setCbt(String cbt) {
            this.cbt = cbt;
        }

        public String getInt() {
            return _int;
        }

        public void setInt(String _int) {
            this._int = _int;
        }

        public String getVbt() {
            return vbt;
        }

        public void setVbt(String vbt) {
            this.vbt = vbt;
        }

        public String getHTrend() {
            return hTrend;
        }

        public void setHTrend(String hTrend) {
            this.hTrend = hTrend;
        }

        public String getATrend() {
            return aTrend;
        }

        public void setATrend(String aTrend) {
            this.aTrend = aTrend;
        }

        public String getDTrend() {
            return dTrend;
        }

        public void setDTrend(String dTrend) {
            this.dTrend = dTrend;
        }

        public String getLTrend() {
            return lTrend;
        }

        public void setLTrend(String lTrend) {
            this.lTrend = lTrend;
        }

    }

    public Date getDeadLine() {
        return DateUtils.parseToDate(getBDate(), "yyyy-MM-dd");
    }

    public Date getMatchDate() {
        return DateUtils.parseToDate(getDate(), "yyyy-MM-dd");
    }
}