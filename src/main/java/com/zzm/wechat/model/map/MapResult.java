package com.zzm.wechat.model.map;

public class MapResult {
    private int status;
    private MapResultData result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MapResultData getResult() {
        return result;
    }

    public void setResult(MapResultData result) {
        this.result = result;
    }

    public String city() {
        return result.city().replace("市", "");
    }

    @Override
    public String toString() {
        return "MapResult{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}
