package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;

@SpringBootTest
public class OwnerServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	 * 
	 */
	@Test
	public void FindByFirstname() {
		String FIND_NAME = "Betty";
		int SIZE_EXPECTED = 1;
		List<Owner> own = ownerService.findByFirstname(FIND_NAME);

		assertThat(SIZE_EXPECTED, is(own.size()));
	}

	@Test
	public void OwnerCreate() {
		String FIRST_NAME = "Sofia";
		String LAST_NAME = "Flores";
		String ADDRESS = "123 W.Liberty St. ";
		String CITY = "Madison";
		String TELEPHONE = "975355486";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);
		assertThat(owner.getId(), notNullValue());
		assertThat(FIRST_NAME, is(owner.getFirstname()));
		assertThat(LAST_NAME, is(owner.getLastname()));
		assertThat(ADDRESS, is(owner.getAddress()));
		assertThat(CITY, is(owner.getCity()));
		assertThat(TELEPHONE, is(owner.getTelephone()));
	}

	@Test
	public void OwnerUpdate() {

		String FIRST_NAME = "Sofia";
		String LAST_NAME = "Flores";
		String ADDRESS = "123 W.Liberty St. ";
		String CITY = "Madison";
		String TELEPHONE = "975355486";
		long create_id = -1;

		String FIRST_NAME_NEW = "Sofia";
		String LAST_NAME_NEW = "Hernandez";
		String ADDRESS_NEW = "321 W.Liberty St. ";
		String CITY_NEW = "Madison";
		String TELEPHONE_NEW = "965432375";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		// Create record
		logger.info(">" + owner);
		Owner readOwner = ownerService.create(owner);
		logger.info(">>" + readOwner);

		create_id = readOwner.getId();

		// Prepare data for update
		readOwner.setFirstname(FIRST_NAME_NEW);
		readOwner.setLastname(LAST_NAME_NEW);
		readOwner.setAddress(ADDRESS_NEW);
		readOwner.setCity(CITY_NEW);
		readOwner.setTelephone(TELEPHONE_NEW);

		// Execute update
		Owner upgradeOwner = ownerService.update(readOwner);
		logger.info(">>>>" + upgradeOwner);

		assertThat(create_id, notNullValue());
		assertThat(create_id, is(upgradeOwner.getId()));
		assertThat(FIRST_NAME_NEW, is(upgradeOwner.getFirstname()));
		assertThat(LAST_NAME_NEW, is(upgradeOwner.getLastname()));
		assertThat(ADDRESS_NEW, is(upgradeOwner.getAddress()));
		assertThat(CITY_NEW, is(upgradeOwner.getCity()));
		assertThat(TELEPHONE_NEW, is(upgradeOwner.getTelephone()));
	}

	@Test
	public void OwnerDelete() {
		String FIRST_NAME = "Sofia";
		String LAST_NAME = "Hernandez";
		String ADDRESS = "321 W.Liberty St.";
		String CITY = "Madison";
		String TELEPHONE = "965432375";
		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);
		try {
			ownerService.delete(owner.getId());
		} catch (OwnerNotFoundException e) {
			assertThat(true, is(true));
		}
	}
}
