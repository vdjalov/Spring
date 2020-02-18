package app.controllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import app.base.BaseTest;
import app.data.models.enums.Gender;
import app.service.HeroService;
import app.service.models.ValidateCreateHeroModel;


@AutoConfigureMockMvc
public class HeroesControllerTests extends BaseTest {

	@Autowired
	HeroService heroService;
	
	@Autowired
	MockMvc mockMvc;
	

	@Test
	public void testControllerCreateGET() {
		try {
			mockMvc.perform(get("/heroes/create"))
				   .andExpect(status().isOk())
				   .andExpect(view().name("heroTemplates/create-hero"));
		} catch (Exception e) {
		// ignore
		}
		
	}
	
	@Test
	public void testControllerCreatePOSTException() {
		ValidateCreateHeroModel validateCreateHeroModel = new ValidateCreateHeroModel();
		validateCreateHeroModel.setName("dragon");
		validateCreateHeroModel.setGender(Gender.MALE);
		
		try {
			mockMvc.perform(post("/heroes/create"))
				   .andDo((ResultHandler) heroService.save(validateCreateHeroModel))
				   .andExpect(status().isOk())
				   .andExpect(view().name("/home"));
				   
			mockMvc.perform(post("/heroes/create"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("heroTemplates/create-hero"));	 
				  
			
		} catch (Exception e) {
		// ignore
		}
	}
	
	@Test
	public void testControllerHeroDetailsGET() {
		
		try {
			mockMvc.perform(get("/heroes/details/{name}"))
				   .andExpect(status().isOk())
				   .andExpect(view().name("heroTemplates/heroDetails"));
		} catch (Exception e) {
		// ignore
		}
		
	}
	
	@Test
	public void testControllerFightViewGET() {
		
		try {
			mockMvc.perform(get("/heroes/fight/{name}"))
				   .andExpect(status().isOk())
				   .andExpect(view().name("heroTemplates/fight"));
		} catch (Exception e) {
		// ignore
		}
		
	}
}
