package com.inven.sistemainventariobackend.modulos.ubigeo;

public class Distrito {
    private String id;
    private String name;
    private String department_id;
    private String province_id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_id() {
        return department_id;
    }
    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getProvince_id() {
        return province_id;
    }
    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }
}
