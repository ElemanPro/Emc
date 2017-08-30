package com.example.elashry.eleman.Model;

import java.io.Serializable;



public class MaintenanceModel implements Serializable{
    String cname;
    String cphone;
    String caddress;
    String dtype;
    String wstate;
    String dbrand;
    String damagetype;
    String odate;

    public MaintenanceModel(String cname, String cphone, String caddress, String dtype, String wstate, String dbrand, String damagetype, String odate) {
        this.cname = cname;
        this.cphone = cphone;
        this.caddress = caddress;
        this.dtype = dtype;
        this.wstate = wstate;
        this.dbrand = dbrand;
        this.damagetype = damagetype;
        this.odate = odate;
    }

    public String getCname() {
        return cname;
    }

    public String getCphone() {
        return cphone;
    }

    public String getCaddress() {
        return caddress;
    }

    public String getDtype() {
        return dtype;
    }

    public String getWstate() {
        return wstate;
    }

    public String getDbrand() {
        return dbrand;
    }

    public String getDamagetype() {
        return damagetype;
    }

    public String getOdate() {
        return odate;
    }
}
