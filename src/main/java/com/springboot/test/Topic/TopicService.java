package com.springboot.test.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	Topic obj = new Topic("Spring", "Spring Course", "Spring Description");
	Topic obj2 = new Topic("Java", "Java Course", "Java Description");
	List<Topic> list = new ArrayList<>(Arrays.asList(obj,obj2));
	
	public List<Topic> getAllTopics() {
		return list;
	}

	public Topic getTopic(String id) {
		Topic obj2 = new Topic("Java", "Java Course", "Java Description");
		return obj2;
	}

	public String addTopic(Topic topic) {
		list.add(topic);
		return "Topic Added Successfully";
	}
}
