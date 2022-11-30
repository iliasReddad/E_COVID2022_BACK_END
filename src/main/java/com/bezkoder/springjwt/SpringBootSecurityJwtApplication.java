package com.bezkoder.springjwt;

import com.bezkoder.springjwt.controllers.ContentController;
import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.repository.ContactRepository;
import com.bezkoder.springjwt.repository.ContentRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class SpringBootSecurityJwtApplication  implements CommandLineRunner {
	@Autowired
	ContactRepository contactRepository ;
	@Autowired
	ContentRepository contentRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

//		Role adminrole = new Role();
//		adminrole.setName(ERole.ROLE_ADMIN);
//
//		Role moderatorrole = new Role();
//		moderatorrole.setName(ERole.ROLE_MODERATOR);
//
//		Role userrole = new Role();
//		userrole.setName(ERole.ROLE_USER);
//
// 		List<Role> list = new ArrayList<>();
//  	Collections.addAll(list, adminrole, moderatorrole, userrole);
//		roleRepository.saveAll(list);
//
//
//
//		User admin = new User();
//		admin.setUsername("ilias");
//		admin.setPassword(encoder.encode("adminadmin"));
//		admin.setEmail("admin@email.com");
//		admin.setRoles(Collections.singleton(adminrole));
//		userRepository.save(admin);
//
//		User moderator = new User();
//		moderator.setUsername("hamza");
//		moderator.setPassword(encoder.encode("hamzahamza"));
//		moderator.setEmail("hamza@email.com");
//		moderator.setRoles(Collections.singleton(moderatorrole));
//		userRepository.save(moderator);
//
//
//		Contact contact = new Contact();
//		contact.setEmail("contactAdmin@uir.ac.ma");
//		contact.setAdresse("Université Internationale de Rabat Technopolis Rabat-Shore Rocade , Rabat-Salé  , 11000 , Sala Al Jadida");
//		contact.setPhone("+212 (0)530-103000");
//		contactRepository.save(contact);
//
//		Content content = new Content();
//		content.setTitre("Welcome to e-covid_2022");
//		content.setSous_titre("A moroccan platform for covid_19 management");
//		content.setParagraphe("The e-covid19 platform is a moroccan platform for covid-19 management. It presents a decision-making assistance tool based on AI aimed to provide real-time social media analysis, automated sentiment analysis and topic modeling.");
//		content.setButton("VIEW VIDEO");
//		contentRepository.save(content);





	}
}
