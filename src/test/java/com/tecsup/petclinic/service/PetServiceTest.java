package com.tecsup.petclinic.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.domain.Pet;
import com.tecsup.petclinic.exception.PetNotFoundException;

@SpringBootTest
public class PetServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(PetServiceTest.class);

	@Autowired
	private PetService petService;

	/**
	 * 
	 */
	@Test
	public void testFindPetById() {

		long ID = 1;
		String NAME = "Leo";
		Pet pet = null;
		
		try {
			
			pet = petService.findById(ID);
			
		} catch (PetNotFoundException e) {
			assertThat(e.getMessage(), false);
		}
		logger.info("" + pet);

		assertThat(NAME, is(pet.getName()));

	}

	/**
	 * 
	 */
	@Test
	public void testFindPetByName() {

		String FIND_NAME = "Leo";
		int SIZE_EXPECTED = 1;

		List<Pet> pets = petService.findByName(FIND_NAME);

		assertThat(SIZE_EXPECTED, is(pets.size()));
	}

	/**
	 * 
	 */
	@Test
	public void testFindPetByTypeId() {

		int TYPE_ID = 5;
		int SIZE_EXPECTED = 2;

		List<Pet> pets = petService.findByTypeId(TYPE_ID);

		assertThat(SIZE_EXPECTED, is(pets.size()));
	}

	/**
	 * 
	 */
	@Test
	public void testFindPetByOwnerId() {

		int OWNER_ID = 10;
		int SIZE_EXPECTED = 2;

		List<Pet> pets = petService.findByOwnerId(OWNER_ID);

		assertThat(SIZE_EXPECTED, is(pets.size()));
		
	}

	/**
	 *  To get ID generate , you need 
	 *  setup in id primary key in your
	 *  entity this annotation :
	 *  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
	@Test
	public void testCreatePet() {

		String PET_NAME = "Ponky";
		int OWNER_ID = 1;
		int TYPE_ID = 1;

		Pet pet = new Pet(PET_NAME, 1, 1);
		pet = petService.create(pet);
		logger.info("" + pet);

		assertThat(pet.getId(), notNullValue());
		assertThat(PET_NAME, is(pet.getName()));
		assertThat(OWNER_ID, is(pet.getOwnerId()));
		assertThat(TYPE_ID,is(pet.getTypeId()));

	}

	/**
	 * 
	 */
	@Test
	public void testUpdatePet() {

		String PET_NAME = "Bear";
		int OWNER_ID = 1;
		int TYPE_ID = 1;
		long create_id = -1;

		String UP_PET_NAME = "Bear2";
		int UP_OWNER_ID = 2;
		int UP_TYPE_ID = 2;

		Pet pet = new Pet(PET_NAME, OWNER_ID, TYPE_ID);

		// Create record
		logger.info(">" + pet);
		Pet readPet = petService.create(pet);
		logger.info(">>" + readPet);

		create_id = readPet.getId();

		// Prepare data for update
		readPet.setName(UP_PET_NAME);
		readPet.setOwnerId(UP_OWNER_ID);
		readPet.setTypeId(UP_TYPE_ID);

		// Execute update
		Pet upgradePet = petService.update(readPet);
		logger.info(">>>>" + upgradePet);

		assertThat(create_id ,notNullValue());
		assertThat(create_id, is(upgradePet.getId()));
		assertThat(UP_PET_NAME, is(upgradePet.getName()));
		assertThat(UP_OWNER_ID, is(upgradePet.getTypeId()));
		assertThat(UP_TYPE_ID, is(upgradePet.getOwnerId()));
	}

	/**
	 * 
	 */
	@Test
	public void testDeletePet() {

		String PET_NAME = "Bird";
		int OWNER_ID = 1;
		int TYPE_ID = 1;

		Pet pet = new Pet(PET_NAME, OWNER_ID, TYPE_ID);
		pet = petService.create(pet);
		logger.info("" + pet);

		try {
			petService.delete(pet.getId());
		} catch (PetNotFoundException e) {
			assertThat(e.getMessage(), false);
		}
			
		try {
			petService.findById(pet.getId());
			assertThat(true, is(false));
		} catch (PetNotFoundException e) {
			assertThat(true, is(true));
		} 				

	}
}
