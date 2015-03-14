package com.contact.manager;

import com.contact.manager.api.Request;
import com.contact.manager.dao.JsonDao;
import com.contact.manager.dao.TopicsDao;
import com.contact.manager.dao.XmlDao;
import com.contact.manager.dao.model.Advertisement;
import com.contact.manager.dao.model.Topic;
import com.contact.manager.front.UiImpl;
import com.contact.manager.service.FileService;
import com.contact.manager.tools.ResultCaptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Tests {
    @Mock
    private JsonDao jsonDao;
    @Mock
    private XmlDao xmlDao;
    @Mock
    private TopicsDao topicsDao;
    @Spy
    @InjectMocks
    private FileService service;
    @Spy
    @InjectMocks
    private UiImpl ui;

    private Advertisement ad1, ad2;
    private List<Advertisement> advertisementList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Topic topic1 = new Topic();
        topic1.setTopic("topic1");
        Topic topic2 = new Topic();
        topic2.setTopic("topic2");
        List<Topic> list = new ArrayList<>(asList(topic1, topic2));
        service.setTopicList(list);
        advertisementList = new ArrayList<>();
        ad1 = new Advertisement();
        ad1.setAuthor("author1");
        ad1.setTitle("title1-----");
        ad1.setTopic("topic1");
        ad1.setText("text1\ntext");
        advertisementList.add(ad1);
        ad2 = new Advertisement();
        ad2.setAuthor("author2");
        ad2.setTitle("title2-----");
        ad2.setTopic("topic2");
        ad2.setText("text2\ntext");
        advertisementList.add(ad2);
        service.setAdvertList(advertisementList);
        when(jsonDao.readAdverts()).thenReturn(advertisementList);
    }

    @Test
    public void readOwnAdvertsTest() throws Exception {
        //захват возвращаемого значения
        ResultCaptor<List<Advertisement>> resultCaptor = new ResultCaptor<>();
        doAnswer(resultCaptor).when(service).getOwnAdverts(any(Request.class));
        //ввод в консоль
        ui.setConsole(new Scanner("author1\n1\n"));
        //регистрация
        ui.signIn();
        //вызов проверяемого метода
        ui.menu();
        Advertisement result = resultCaptor.getResult().get(0);
        assertTrue(result.equals(ad1));
    }

    @Test
    public void readAdvertsByTopicTest() throws Exception {
        //захват возвращаемого значения
        ResultCaptor<List<Advertisement>> resultCaptor = new ResultCaptor<>();
        doAnswer(resultCaptor).when(service).getAdvertsByTopic(any(Request.class));
        //ввод в консоль
        ui.setConsole(new Scanner("author1\n2\n2\n"));
        //регистрация
        ui.signIn();
        //вызов проверяемого метода
        ui.menu();
        Advertisement result = resultCaptor.getResult().get(0);
        assertTrue(result.equals(ad2));
    }

    @Test
    public void readAdvertsByAuthorTest() throws Exception {
        //захват возвращаемого значения
        ResultCaptor<List<Advertisement>> resultCaptor = new ResultCaptor<>();
        doAnswer(resultCaptor).when(service).getAdvertsByAuthor(any(Request.class));
        //ввод в консоль
        ui.setConsole(new Scanner("guest\n3\nauthor1\n"));
        //регистрация
        ui.signIn();
        //вызов проверяемого метода
        ui.menu();
        Advertisement result = resultCaptor.getResult().get(0);
        assertTrue(result.equals(ad1));
    }

    @Test
    public void newTopicTest() throws Exception {
        Advertisement adv3 = new Advertisement();
        adv3.setAuthor("Vasya");
        adv3.setTitle("title3-----");
        adv3.setTopic("topic1");
        adv3.setText("text3\n---------------");
        //ввод в консоль adv3
        ui.setConsole(new Scanner("Vasya\n4\n1\ntitle3-----\ntext3\n---------------/q\n"));
        ui.signIn();
        //проверяемый метод
        ui.menu();
        List<Advertisement> advertList = service.getAdvertList();
        assertTrue(advertList.contains(adv3));
    }

    @Test
    public void changeTopicTest() throws Exception {
        Advertisement adv3 = new Advertisement();
        adv3.setAuthor("Vasya");
        adv3.setTitle("title1-----");
        adv3.setTopic("topic1");
        adv3.setText("text3\n---------------");
        //ввод в консоль adv3
        ui.setConsole(new Scanner("Vasya\n5\n1\ntitle1-----\ntext3\n---------------/q\n"));
        ui.signIn();
        //проверяемый метод
        ui.menu();
        List<Advertisement> advertList = service.getAdvertList();
        assertTrue(advertList.contains(adv3));

        //возвращаем прежнее сотояние
        service.setAdvertList(advertisementList);
    }

    @Test
    public void deleteTopicTest() throws Exception {
        //ввод в консоль
        ui.setConsole(new Scanner("Vasya\n6\n1\ntitle1-----\nauthor1\n"));
        ui.signIn();
        //проверяемый метод
        ui.menu();
        List<Advertisement> advertList = service.getAdvertList();
        assertFalse(advertList.contains(ad1));

        //возвращаем прежнее сотояние
        service.setAdvertList(advertisementList);
    }

    @Test
    public void saveOnExitTest() throws Exception {
        ui.setConsole(new Scanner("2\n7\n"));
        //выбираем сохранение в XML и JSON
        ui.chooseOut();
        //проверяемый метод
        ui.exit();
        //проверяем вызов сохранения
        verify(jsonDao).saveAdverts(anyList());
        verify(xmlDao).saveAdverts(anyList());
    }

}
