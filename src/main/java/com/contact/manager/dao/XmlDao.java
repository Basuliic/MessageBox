package com.contact.manager.dao;

import com.contact.manager.dao.model.Advertisement;
import com.contact.manager.dao.model.Adverts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * DAO для объявлений в формате XML
 */
public class XmlDao {
    private static final String
            PATH = "data/adverts.xml";
    private static XmlDao dao;

    private XmlDao() {
    }

    /**
     * Метод получения экземпляра класса XmlDao
     *
     * @return экземпляр класса XmlDao
     */
    public static XmlDao getXmlDao() {
        if (dao == null) {
            dao = new XmlDao();
        }
        return dao;
    }

    /**
     * Метод сохранения объявлений в файл
     */
    public void saveAdverts(List<Advertisement> list) {
        try {
            Adverts adverts = new Adverts(list);
            File file = new File(PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(Adverts.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // форматированный вывод
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(adverts, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}