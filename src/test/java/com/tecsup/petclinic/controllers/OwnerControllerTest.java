package com.tecsup.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dto.OwnerDTO;
/**
 * 
 */
@AutoConfigureMockMvc
@SpringBootTest
public class OwnerControllerTest {
	private static final Logger logger 
	= LoggerFactory.getLogger(OwnerControllerTest.class);

	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetOwners() throws Exception{
		int ID =1;
		this.mockMvc.perform(get("/owners"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].id", is(ID)));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	
	@Test
	public void testFindOwner()  throws Exception{
		 int ID_SEARCH =3;
		 String FIRST_NAME = "Eduardo";
		 String LAST_NAME = "Rodriquez";
		 String ADDRESS = "2693 Commerce St.";
		 String CITY = "McFarland";
		 String PHONE = "6085558763";
		 
		 
			mockMvc.perform(get("/owners/" + ID_SEARCH))  // Finding object with ID = 1
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id" ,is(3)))
			.andExpect(jsonPath("$.firstname", is(FIRST_NAME)))
			.andExpect(jsonPath("$.lastname", is(LAST_NAME)))
			.andExpect(jsonPath("$.address", is(ADDRESS)))
			.andExpect(jsonPath("$.city", is(CITY)))
			.andExpect(jsonPath("$.telephone", is(PHONE)));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	
	@Test
	public void testFindOwner2() throws Exception {

		int ID_FIRST = 3;
		mockMvc.perform(get("/owners" + ID_FIRST)) // Finding object with ID = 666
				.andExpect(status().isNotFound());

	}
	
	/**
	 * @throws Exception
	 */
	
	@Test
    public void testCreateOwner() throws Exception {
		 
		 String FIRST_NAME = "Harold";
		 String LAST_NAME = "Davis";
		 String ADDRESS = "563 Friendly St.";
		 String CITY = "Windsor";
		 String PHONE = "6085553198";

		OwnerDTO newOwner = new OwnerDTO(FIRST_NAME, LAST_NAME, ADDRESS, CITY, PHONE);

		
	    
		logger.info(newOwner.toString());
		logger.info(om.writeValueAsString(newOwner));
	    
	    mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.firstname", is(FIRST_NAME)))
				.andExpect(jsonPath("$.lastname", is(LAST_NAME)))
				.andExpect(jsonPath("$.address", is(ADDRESS)))
				.andExpect(jsonPath("$.city", is(CITY)))
				.andExpect(jsonPath("$.telephone", is(PHONE)));
    
	}
	

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteOwner() throws Exception {
    	 String FIRST_NAME = "Harold";
		 String LAST_NAME = "Davis";
		 String ADDRESS = "563 Friendly St.";
		 String CITY = "Windsor";
		 String PHONE = "6085553198";
		
		 OwnerDTO newOwner = new OwnerDTO( FIRST_NAME, LAST_NAME, ADDRESS, CITY, PHONE);
		
		ResultActions mvcActions = mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
	            
		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/owners/" + id ))
                 /*.andDo(print())*/
                .andExpect(status().isOk());
    }
}
