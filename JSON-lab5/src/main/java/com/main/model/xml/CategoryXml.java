//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.04 at 07:40:02 PM MSK 
//


package com.main.model.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for category.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="category"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="\u041f\u0435\u0440\u0432\u0430\u044f"/&gt;
 *     &lt;enumeration value="\u0412\u0442\u043e\u0440\u0430\u044f"/&gt;
 *     &lt;enumeration value="\u0422\u0440\u0435\u0442\u044c\u044f"/&gt;
 *     &lt;enumeration value="\u0412\u044b\u0441\u0448\u0430\u044f"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "category")
@XmlEnum
public enum CategoryXml {

    @XmlEnumValue("\u041f\u0435\u0440\u0432\u0430\u044f")
    FIRST("\u041f\u0435\u0440\u0432\u0430\u044f"),
    @XmlEnumValue("\u0412\u0442\u043e\u0440\u0430\u044f")
    SECOND("\u0412\u0442\u043e\u0440\u0430\u044f"),
    @XmlEnumValue("\u0422\u0440\u0435\u0442\u044c\u044f")
    THIRD("\u0422\u0440\u0435\u0442\u044c\u044f"),
    @XmlEnumValue("\u0412\u044b\u0441\u0448\u0430\u044f")
    PHD("\u0412\u044b\u0441\u0448\u0430\u044f");
    private final String value;

    CategoryXml(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoryXml fromValue(String v) {
        for (CategoryXml c: CategoryXml.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}