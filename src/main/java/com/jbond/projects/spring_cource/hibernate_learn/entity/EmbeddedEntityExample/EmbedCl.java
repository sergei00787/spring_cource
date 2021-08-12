package com.jbond.projects.spring_cource.hibernate_learn.entity.EmbeddedEntityExample;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@NoArgsConstructor
public class EmbedCl {

    private String propStr;
    private int propInt;
    private EnmsCl propEnums;

    public EmbedCl(String propStr, int propInt, EnmsCl propEnums) {
        this.propStr = propStr;
        this.propInt = propInt;
        this.propEnums = propEnums;
    }

    @Column(name = "prop_str")
    public String getPropStr() {
        return propStr;
    }

    public void setPropStr(String propStr) {
        this.propStr = propStr;
    }

    @Column(name = "prop_int")
    public int getPropInt() {
        return propInt;
    }

    public void setPropInt(int propInt) {
        this.propInt = propInt;
    }

    @Column(name = "prop_enums")
    @Enumerated(value = EnumType.STRING)
    public EnmsCl getPropEnums() {
        return propEnums;
    }

    public void setPropEnums(EnmsCl propEnums) {
        this.propEnums = propEnums;
    }
}
