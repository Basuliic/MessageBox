package com.contact.manager.dao.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Класс для XML конвертации
 */
@XmlRootElement
public class Adverts {
    public Adverts() {
    }

    public Adverts(List<Advertisement> list) {
        this.list = list;
    }

    private List<Advertisement> list;

    @XmlElementWrapper(name = "advertisements")
    @XmlElement(name = "advertisement")
    public List<Advertisement> getList() {
        return list;
    }

    public void setList(List<Advertisement> list) {
        this.list = list;
    }
}
