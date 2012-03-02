package com.managementconsole.beando;

public class ReportBean {
    public ReportBean() {
    }
  //audit  
    private String userName;
    private String appName;
    private String module;
    private String deviceName;
    private String platform;
    private String deviceUUID;
    private String version;
    private String action;
    private String date;
    private String processingTime;
    private String insertTime;
    
//Inventory
     private String appIdInv;
     private String appNameInv;
     private String descriptionInv;
     private String versionInv;
     private String startDateInv;
     private String endDateInv;
     
//security 
    private String securityroupSec;
    private String appIdSec;
    private String startDateSec;
    private String endDateSec;
    
//history    
 private String userNameHis;
    private String securityroupHis;
    private String appIdHis;
    private String startDateHis;
    private String endDateHis;
    private String deviceUUIDHis;
    private String eraseDateHis;
    private String moduleID;
    private String downloadVersion;
    private String downloadDate;
    
    
    
    
    
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setAppIdInv(String appIdInv) {
        this.appIdInv = appIdInv;
    }

    public String getAppIdInv() {
        return appIdInv;
    }

    public void setAppNameInv(String appNameInv) {
        this.appNameInv = appNameInv;
    }

    public String getAppNameInv() {
        return appNameInv;
    }

    public void setDescriptionInv(String descriptionInv) {
        this.descriptionInv = descriptionInv;
    }

    public String getDescriptionInv() {
        return descriptionInv;
    }

    public void setVersionInv(String versionInv) {
        this.versionInv = versionInv;
    }

    public String getVersionInv() {
        return versionInv;
    }

    public void setStartDateInv(String startDateInv) {
        this.startDateInv = startDateInv;
    }

    public String getStartDateInv() {
        return startDateInv;
    }

    public void setEndDateInv(String endDateInv) {
        this.endDateInv = endDateInv;
    }

    public String getEndDateInv() {
        return endDateInv;
    }

    public void setSecurityroupSec(String securityroupSec) {
        this.securityroupSec = securityroupSec;
    }

    public String getSecurityroupSec() {
        return securityroupSec;
    }

    public void setAppIdSec(String appIdSec) {
        this.appIdSec = appIdSec;
    }

    public String getAppIdSec() {
        return appIdSec;
    }

    public void setStartDateSec(String startDateSec) {
        this.startDateSec = startDateSec;
    }

    public String getStartDateSec() {
        return startDateSec;
    }

    public void setEndDateSec(String endDateSec) {
        this.endDateSec = endDateSec;
    }

    public String getEndDateSec() {
        return endDateSec;
    }

    public void setUserNameHis(String userNameHis) {
        this.userNameHis = userNameHis;
    }

    public String getUserNameHis() {
        return userNameHis;
    }

    public void setSecurityroupHis(String securityroupHis) {
        this.securityroupHis = securityroupHis;
    }

    public String getSecurityroupHis() {
        return securityroupHis;
    }

    public void setAppIdHis(String appIdHis) {
        this.appIdHis = appIdHis;
    }

    public String getAppIdHis() {
        return appIdHis;
    }

    public void setStartDateHis(String startDateHis) {
        this.startDateHis = startDateHis;
    }

    public String getStartDateHis() {
        return startDateHis;
    }

    public void setEndDateHis(String endDateHis) {
        this.endDateHis = endDateHis;
    }

    public String getEndDateHis() {
        return endDateHis;
    }

    public void setDeviceUUIDHis(String deviceUUIDHis) {
        this.deviceUUIDHis = deviceUUIDHis;
    }

    public String getDeviceUUIDHis() {
        return deviceUUIDHis;
    }

    public void setEraseDateHis(String eraseDateHis) {
        this.eraseDateHis = eraseDateHis;
    }

    public String getEraseDateHis() {
        return eraseDateHis;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setDownloadVersion(String downloadVersion) {
        this.downloadVersion = downloadVersion;
    }

    public String getDownloadVersion() {
        return downloadVersion;
    }

    public void setDownloadDate(String downloadDate) {
        this.downloadDate = downloadDate;
    }

    public String getDownloadDate() {
        return downloadDate;
    }
}
