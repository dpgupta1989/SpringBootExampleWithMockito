import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.test.Topic.Topic;
import com.springboot.test.Topic.TopicController;
import com.springboot.test.Topic.TopicService;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = TopicController.class, secure = false)
//@WebAppConfiguration
public class TopicControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
    private TopicController topicController;
	
	@Mock
	private TopicService topicService;
	
	List<Topic> mocklist = new ArrayList<Topic>(Arrays.asList(
			new Topic("Spring", "Spring Course", "Spring Description"),
			new Topic("Java", "Java Course", "Java Description")
			));
			
	@Test
	public void test_getAllTopics() {
		Mockito.when(topicService.getAllTopics()).thenReturn(mocklist);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/topics")
				.accept(MediaType.APPLICATION_JSON);
		
		System.out.println("@@@@@@requestBuilder Object :" + requestBuilder);
		try {
//			Below line is to create an object mockMvc object for the specified controller "topicController"
			mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
			
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
			
			System.out.println("Result: " + result.getResponse().getOutputStream());
			System.out.println(" Output of Mock call topicService.getAllTopics() :" + topicService.getAllTopics().get(1).getId());
//			String expectedData = "{\"course\": \"Spring Course\",\"lession\": \"Spring Description\",\"id\": \"Spring\"},{\"course\": \"Java Course\",\"lession\": \"Java Description\",\"id\": \"Java\"}";
		} catch (Exception e) {
			System.out.println("!!!!!!!!!! Exception Occur");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test_postTopic() throws Exception{
		String mockResponse = "Topic added successfully";
		Topic mockTopic = Mockito.mock(Topic.class);
		
		//Mocking postTopic method to respond back the sample response
		Mockito.when(topicController.postTopic(mockTopic)).thenReturn(mockResponse);
		System.out.println("Mock Call for postTopic method :" + topicController.postTopic(mockTopic));
		
		String exampleRequestJson = "{\"course\": \"Spring Course\",\"lession\": \"Spring Description\",\"id\": \"Spring\"},{\"course\": \"Java Course\",\"lession\": \"Java Description\",\"id\": \"Java\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/topics")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleRequestJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void test_getTopic(){
		Topic mockTopic = Mockito.mock(Topic.class);
		Mockito.when(topicController.getTopic("Java")).thenReturn(mockTopic);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/topics/Java")
				.accept(MediaType.APPLICATION_JSON);
		
		try {
			mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
