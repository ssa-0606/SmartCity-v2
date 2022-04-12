package com.example.smartcity_v2.beans;

public class ServiceList {

    private int id;
    private String serviceName;
    private String serviceType;
    private int sort;
    private String imgUrl;

    public ServiceList(int id, String serviceName, String serviceType, int sort, String imgUrl) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.sort = sort;
        this.imgUrl = imgUrl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
