package com.inven.sistemainventariobackend.modulos.ubigeo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Provincia {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("department_id")
    private String department_id;

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
}
