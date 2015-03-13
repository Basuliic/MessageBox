package com.contact.manager;

import com.contact.manager.dao.JsonDao;
import com.contact.manager.dao.TopicsDao;
import com.contact.manager.dao.model.Topic;
import com.contact.manager.front.UiImpl;
import com.contact.manager.service.FileService;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Tests {
    @Mock
    private JsonDao jsonDao;
    @Mock
    private TopicsDao topicsDao;
    //    @Rule
//    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    @Spy
    @InjectMocks
    private FileService service;
    @Spy
    @InjectMocks
    private UiImpl ui = new UiImpl();

    private Gson gson = new Gson();

    @Ignore
    @Test
    public void test() throws Exception {
        Topic topic1 = new Topic();
        topic1.setTopic("topic1");
        Topic topic2 = new Topic();
        topic2.setTopic("topic2");
        List<Topic> list = new ArrayList<>(asList(topic1, topic2));
        when(topicsDao.getTopics()).thenReturn(list);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ui.menu();
//        systemInMock.provideText("7");

        verify(jsonDao).saveAdverts(anyList());
    }

}
