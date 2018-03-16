package com.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.model.Person;

@Component
public class CallRestService implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		consumePostRestCall();
		consumeGetRestCalls();
		consumePutRestCalls();
		//consumeGetAllPersons();
	}

	public static void consumeGetRestCalls() {
		String url = "http://localhost:8080/person/findPerson/1";
		RestTemplate rt = new RestTemplate();
		Person person = rt.getForObject(url, Person.class);
		if (person != null) {
			System.out.println("Found Person "+person.getId()+" - "+person.toString());
		} else {
			System.out.println("Unable to get json");
		}
	}
	
	public void consumeGetAllPersons() {
		String url = "http://localhost:8080/person/findAllPersons";
		RestTemplate rt = new RestTemplate();
		List<Person> persons = (List<Person>)rt.getForObject(url, Person.class);
		System.out.println("Getting all persons\n");
		if (persons != null && persons.size()>0) {
			for(Person person :persons) {
				System.out.println(person.toString());
			}
			System.out.println("Retrieved all persons \n");
		} else {
			System.out.println("Unable to get json");
		}
	}

	public void consumePutRestCalls() {
		String url = "http://localhost:8080/person/update/1";
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		System.out.println("Updating persons begin\n");
		Person updatedPerson = new Person("1", "James", "Gilly", 23);
		rt.put(url, updatedPerson, params);
		System.out.println(updatedPerson.toString());
		System.out.println("Updating persons complete\n");
	}

	public void consumePostRestCall() {
		String url = "http://localhost:8080/person/add";
		RestTemplate rt = new RestTemplate();
		System.out.println("Adding persons");
		Person updatedPerson1 = new Person("1", "TestName1", "TestLastName1", 21);
		Person updatedPerson2 = new Person("2", "TestName2", "TestLastName2", 22);
		Person updatedPerson3 = new Person("3", "TestName3", "TestLastName3", 23);
		rt.postForObject(url, updatedPerson1, Person.class);
		rt.postForObject(url, updatedPerson2, Person.class);
		rt.postForObject(url, updatedPerson3, Person.class);
		System.out.println(updatedPerson1.toString()+"\n");
		System.out.println(updatedPerson2.toString()+"\n");
		System.out.println(updatedPerson3.toString()+"\n");
		System.out.println("Adding persons complete");
	}
}
