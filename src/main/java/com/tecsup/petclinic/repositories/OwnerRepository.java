package com.tecsup.petclinic.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.tecsup.petclinic.entities.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	List<Owner> findByFirstname(String firstname);

	List<Owner> findByLastname(String lastname);

	List<Owner> findByTelephone(String telephone);

}
