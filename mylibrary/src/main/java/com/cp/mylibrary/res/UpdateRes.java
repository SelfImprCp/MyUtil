package com.cp.mylibrary.res;

/**
 * @author Administrator
 */
public class UpdateRes extends Response {

    private String error;
    private String errorMsg;
    private String version;
    private String url;
    private String desc;
    private boolean forceupdate;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isForceupdate() {
        return forceupdate;
    }

    public void setForceupdate(boolean forceupdate) {
        this.forceupdate = forceupdate;
    }


}
