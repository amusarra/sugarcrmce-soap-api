/**
 * SugarsoapTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.dontesta.sugarcrm.client.soap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.sugarcrm.www.sugarcrm.Link_name_to_fields_array;
import com.sugarcrm.www.sugarcrm.Name_value;
import com.sugarcrm.www.sugarcrm.SugarsoapBindingStub;
import com.sugarcrm.www.sugarcrm.User_auth;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SugarSOAPTestCase {
	@Rule public TestName name = new TestName();
	@Rule public TestWatcher watchman = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			super.starting(description);
			logger.info("{} being run...", description);
		}
	};
	
	private Properties p = new Properties();
	
	private static String USER_NAME = null;
	private static String USER_PASSWORD = null;
	private static String SERVICE_ENDPOINT = null;
	
	private static final String APPLICATION_NAME = Class.class.getName();
	private static final Integer TIMEOUT = 6000;

	final Logger logger =
		    LoggerFactory.getLogger(SugarSOAPTestCase.class);
	
	
	private static final String LEAD_MODULE_NAME = "Leads";
	private static final String ACCOUNT_MODULE_NAME = "Accounts";
	private static final String CONTACT_MODULE_NAME = "Contacts";
	
	private static String sessionId = null;
	private static String userId = null;
	private static String leadId = null;
	private static String accountId = null;
	private static String contactId = null;
	
	private static SugarsoapBindingStub binding;

	@Before
	public void setupBinding() {
		try {
			p.load((new ClassPathResource("sugarcrmclient.properties")).getInputStream());
			USER_NAME = p.getProperty("sugarcrm.username");
			USER_PASSWORD = p.getProperty("sugarcrm.password");
			SERVICE_ENDPOINT = p.getProperty("sugarcrm.soap.endpoint");
		} catch (IOException e) {
			fail(e.getMessage());
		}
		try {
			if (binding == null) {
				if (SERVICE_ENDPOINT != null) {
					binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort(new URL(SERVICE_ENDPOINT));
				} else {
					binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
							.getsugarsoapPort();
				}
				// Time out after a minute
				binding.setTimeout(TIMEOUT);
			}
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}
		assertNotNull("binding is null", binding);
	}

	@Test
	public void testAsugarsoapPortLogin() throws Exception {

		// Test operation
		com.sugarcrm.www.sugarcrm.Entry_value value = null;
		MessageDigest messageDiget = MessageDigest.getInstance("MD5");
		messageDiget.update(USER_PASSWORD.getBytes());
		User_auth userAuthInfo = new User_auth();
		userAuthInfo.setUser_name(USER_NAME);
		userAuthInfo.setPassword((new BigInteger(1, messageDiget.digest()))
				.toString(16));
		try {
			value = binding.login(userAuthInfo, APPLICATION_NAME,
					new com.sugarcrm.www.sugarcrm.Name_value[0]);

			assertNotNull(name.getMethodName() + " => sessionId is null",
					value.getId());
			sessionId = value.getId();
			
			Name_value[] name_value = value.getName_value_list();
			for (Name_value name_value2 : name_value) {
				if (name_value2.getName().equals("user_id")) {
					userId = name_value2.getValue();
					logger.info("Logged user id: " + userId);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testBsugarsoapPortSet_entry() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.New_set_entry_result value = null;
		Map<String, List<Name_value>> nameValueList = new HashMap<String, List<Name_value>>();
		List<Name_value> nameValueForModuleLeads = new ArrayList<Name_value>();
		List<Name_value> nameValueForModuleAccounts = new ArrayList<Name_value>();
		List<Name_value> nameValueForModuleContacts = new ArrayList<Name_value>();

		// Lead data
		nameValueForModuleLeads.add(new Name_value("first_name", "Antonio"));
		nameValueForModuleLeads.add(new Name_value("last_name", "Rossi"));
		nameValueForModuleLeads.add(new Name_value("title", "R&D Manager"));
		nameValueForModuleLeads.add(new Name_value("phone_office", "03906748479"));
		nameValueForModuleLeads.add(new Name_value("email1", "antonio.musarra@gmail.com"));
		nameValueForModuleLeads.add(new Name_value("department", "R&D"));
		nameValueForModuleLeads.add(new Name_value("website", "http://www.dontesta.it"));
		nameValueForModuleLeads.add(new Name_value("description",
				"Add from SugarCRM Client JUnit Test ("
						+ SugarSOAPTestCase.class + ")"));
		nameValueForModuleLeads.add(new Name_value("assigned_user_id", userId));
		nameValueList.put(LEAD_MODULE_NAME, nameValueForModuleLeads);
		
		// Account data
		nameValueForModuleAccounts.add(new Name_value("name", "Dontesta Corporation Ltd"));
		nameValueForModuleAccounts.add(new Name_value("email1", "antonio.musarra@gmail.com"));
		nameValueForModuleAccounts.add(new Name_value("phone_office", "03906748479"));
		nameValueForModuleAccounts.add(new Name_value("website", "http://www.dontesta.it"));
		nameValueForModuleAccounts.add(new Name_value("billing_address_city", "Bronte"));
		nameValueForModuleAccounts.add(new Name_value("billing_address_state", "CT"));
		nameValueForModuleAccounts.add(new Name_value("billing_address_postalcode", "95034"));
		nameValueForModuleAccounts.add(new Name_value("billing_address_country", "IT"));
		nameValueForModuleAccounts.add(new Name_value("description",
				"Add from SugarCRM Client JUnit Test ("
						+ SugarSOAPTestCase.class + ")"));
		nameValueForModuleAccounts.add(new Name_value("assigned_user_id", userId));
		nameValueList.put(ACCOUNT_MODULE_NAME, nameValueForModuleAccounts);
		
		// Contact data
		nameValueForModuleContacts.add(new Name_value("first_name", "Antonio"));
		nameValueForModuleContacts.add(new Name_value("last_name", "Musarra"));
		nameValueForModuleContacts.add(new Name_value("title", "IT Sales Manager"));
		nameValueForModuleContacts.add(new Name_value("primary_address_city", "Bronte"));
		nameValueForModuleContacts.add(new Name_value("primary_address_state", "CT"));
		nameValueForModuleContacts.add(new Name_value("primary_address_postalcode", "95034"));
		nameValueForModuleContacts.add(new Name_value("primary_address_country", "IT"));
		nameValueForModuleContacts.add(new Name_value("phone_work", "03906748479"));
		nameValueForModuleContacts.add(new Name_value("email1", "antonio.musarra@gmail.com"));
		nameValueForModuleContacts.add(new Name_value("department", "Sales"));
		nameValueForModuleContacts.add(new Name_value("website", "http://www.dontesta.it"));
		nameValueForModuleContacts.add(new Name_value("description",
				"Add from SugarCRM Client JUnit Test ("
						+ SugarSOAPTestCase.class + ")"));
		nameValueForModuleContacts.add(new Name_value("assigned_user_id", userId));
		nameValueList.put(CONTACT_MODULE_NAME, nameValueForModuleContacts);
		
		try {
			for (Entry<String, List<Name_value>> name_value : nameValueList.entrySet()) {
				for (Name_value nameValue : name_value.getValue()) {
					logger.debug("Key " + name_value.getKey() + " Name/Value "
							+ nameValue.getName() + "/" + nameValue.getValue());
				}
				Name_value[] nameValueArray = new Name_value[name_value.getValue().size()];
				for (int i = 0; i < nameValueArray.length; i++) {
					nameValueArray[i] = name_value.getValue().get(i);
				}
				value = binding.set_entry(sessionId,
						name_value.getKey(),
						nameValueArray);
				
				if (name_value.getKey().equals(LEAD_MODULE_NAME)) {
					leadId = value.getId();
					logger.info("{} inserted correctly", name_value.getKey() + " with id:" + leadId);
					assertNotNull(
							name.getMethodName() + " => Lead entry Id is null",
							leadId);
				} else if (name_value.getKey().equals(ACCOUNT_MODULE_NAME)) {
					accountId = value.getId();
					logger.info("{} inserted correctly", name_value.getKey() + " with id:" + accountId);
					assertNotNull(
							name.getMethodName() + " => Account entry Id is null",
							accountId);
				} else if (name_value.getKey().equals(CONTACT_MODULE_NAME)) {
					contactId = value.getId();
					logger.info("{} inserted correctly", name_value.getKey() + " with id:" + contactId);
					assertNotNull(
							name.getMethodName() + " => Contact entry Id is null",
							contactId);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCsugarsoapPortGet_entry() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.Get_entry_result_version2 value = null;
		Link_name_to_fields_array[] link_name_to_fields_array = null;
		String[] select_fields = null;

		try {
			value = binding.get_entry(sessionId, "Leads",
					leadId, select_fields,
					link_name_to_fields_array, true);

			assertNotNull(name.getMethodName() + " => Get Entry Result Version2 is null", value);
			assertEquals(name.getMethodName() + " => Entry list size", 1, value.getEntry_list().length);
			assertEquals(name.getMethodName() + " => Entry id", leadId, value.getEntry_list()[0].getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDsugarsoapPortGet_entries() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.Get_entry_result_version2 value = null;

		try {
			value = binding.get_entries(sessionId, LEAD_MODULE_NAME,
					new java.lang.String[]{leadId}, new java.lang.String[0],
					new com.sugarcrm.www.sugarcrm.Link_name_to_fields_array[0],
					true);

			assertNotNull(
					name.getMethodName() + " => Get Entry Result Version2 is null",
					value.getEntry_list());
			assertEquals(name.getMethodName() + " => Entry list size", 1, value.getEntry_list().length);
			logger.info("Get entries count: " + value.getEntry_list().length
					+ " for module " + LEAD_MODULE_NAME);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testEsugarsoapPortGet_entry_list() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.Get_entry_list_result_version2 value = null;

		try {
			value = binding.get_entry_list(sessionId, CONTACT_MODULE_NAME,
					new java.lang.String("contacts.primary_address_state='NY'"), new java.lang.String(), 1,
					new java.lang.String[0],
					new com.sugarcrm.www.sugarcrm.Link_name_to_fields_array[0], 15,
					0, true);

			assertNotNull(
					name.getMethodName() + " => Get Entry Result Version2 is null",
					value.getEntry_list());
			assertTrue(name.getMethodName() + " => Entry list size", value.getEntry_list().length > 0);
			logger.info("Get entries count: " + value.getEntry_list().length
					+ " for module " + CONTACT_MODULE_NAME);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testFsugarsoapPortSet_relationship() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.New_set_relationship_list_result value = null;
		
		try {
			value = binding.set_relationship(sessionId,
					CONTACT_MODULE_NAME, contactId,
					"leads", new java.lang.String[]{leadId},
					new com.sugarcrm.www.sugarcrm.Name_value[0], 0);

			assertNotNull(name.getMethodName() + " => New_set_relationship_list_result is null",
					value);
			
			logger.debug("Created " + value.getCreated());
			logger.debug("Deleted " + value.getDeleted());
			logger.debug("Failed " + value.getFailed());

			assertTrue(name.getMethodName() + " nothing relationship created ", value.getCreated() != 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGsugarsoapPortSet_relationships() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.New_set_relationship_list_result value = null;

		try {
			value = binding.set_relationships(new java.lang.String(),
					new java.lang.String[0], new java.lang.String[0],
					new java.lang.String[0], new java.lang.String[0][0],
					new com.sugarcrm.www.sugarcrm.Name_value[0][0], new int[0]);

			assertNotNull(
					"test7sugarsoapPortSet_relationships() => New_set_relationship_list_result is null",
					value.getCreated());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testHsugarsoapPortGet_relationships() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.Get_entry_result_version2 value = null;

		try {
			value = binding.get_relationships(new java.lang.String(),
					new java.lang.String(), new java.lang.String(),
					new java.lang.String(), new java.lang.String(),
					new java.lang.String[0],
					new com.sugarcrm.www.sugarcrm.Link_name_to_fields_array[0], 0,
					new java.lang.String(), 0, 0);

			assertNotNull(
					"test8sugarsoapPortGet_relationships() => Get_entry_result_version2 is null",
					value.getRelationship_list());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLsugarsoapPortSet_entries() throws Exception {
		// Test operation
		com.sugarcrm.www.sugarcrm.New_set_entries_result value = null;

		try {
			value = binding.set_entries(new java.lang.String(),
					new java.lang.String(),
					new com.sugarcrm.www.sugarcrm.Name_value[0][0]);

			assertNotNull(
					"test10sugarsoapPortSet_entries() => New_set_entries_result is null",
					value.getIds());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void test11sugarsoapPortGet_server_info() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.Get_server_info_result value = null;
		value = binding.get_server_info();
		// TBD - validate results
	}

	public void test12sugarsoapPortGet_user_id() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		java.lang.String value = null;
		value = binding.get_user_id(new java.lang.String());
		// TBD - validate results
	}

	public void test13sugarsoapPortGet_module_fields() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.New_module_fields value = null;
		value = binding.get_module_fields(new java.lang.String(),
				new java.lang.String(), new java.lang.String[0]);
		// TBD - validate results
	}

	public void test14sugarsoapPortSeamless_login() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		int value = -3;
		value = binding.seamless_login(new java.lang.String());
		// TBD - validate results
	}

	public void test15sugarsoapPortSet_note_attachment() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.New_set_entry_result value = null;
		value = binding.set_note_attachment(new java.lang.String(),
				new com.sugarcrm.www.sugarcrm.New_note_attachment());
		// TBD - validate results
	}

	public void test16sugarsoapPortGet_note_attachment() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.New_return_note_attachment value = null;
		value = binding.get_note_attachment(new java.lang.String(),
				new java.lang.String());
		// TBD - validate results
	}

	public void test17sugarsoapPortSet_document_revision() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.New_set_entry_result value = null;
		value = binding.set_document_revision(new java.lang.String(),
				new com.sugarcrm.www.sugarcrm.Document_revision());
		// TBD - validate results
	}

	public void test18sugarsoapPortGet_document_revision() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.New_return_document_revision value = null;
		value = binding.get_document_revision(new java.lang.String(),
				new java.lang.String());
		// TBD - validate results
	}

	public void test19sugarsoapPortSearch_by_module() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.Return_search_result value = null;
		value = binding.search_by_module(new java.lang.String(),
				new java.lang.String(), new java.lang.String[0], 0, 0,
				new java.lang.String(), new java.lang.String[0], true, true);
		// TBD - validate results
	}

	public void test20sugarsoapPortGet_available_modules() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.Module_list value = null;
		value = binding.get_available_modules(new java.lang.String(),
				new java.lang.String());
		// TBD - validate results
	}

	public void test21sugarsoapPortGet_user_team_id() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		java.lang.String value = null;
		value = binding.get_user_team_id(new java.lang.String());
		// TBD - validate results
	}

	public void test22sugarsoapPortSet_campaign_merge() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		binding.set_campaign_merge(new java.lang.String(),
				new java.lang.String[0], new java.lang.String());
		// TBD - validate results
	}

	public void test23sugarsoapPortGet_entries_count() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.Get_entries_count_result value = null;
		value = binding.get_entries_count(new java.lang.String(),
				new java.lang.String(), new java.lang.String(), 0);
		// TBD - validate results
	}

	public void test24sugarsoapPortGet_module_fields_md5() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		java.lang.String[] value = null;
		value = binding.get_module_fields_md5(new java.lang.String(),
				new java.lang.String[0]);
		// TBD - validate results
	}

	public void test25sugarsoapPortGet_last_viewed() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.Last_viewed_entry[] value = null;
		value = binding.get_last_viewed(new java.lang.String(),
				new java.lang.String[0]);
		// TBD - validate results
	}

	public void test26sugarsoapPortGet_upcoming_activities() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.Upcoming_activity_entry[] value = null;
		value = binding.get_upcoming_activities(new java.lang.String());
		// TBD - validate results
	}

	public void test27sugarsoapPortGet_modified_relationships()
			throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		com.sugarcrm.www.sugarcrm.Modified_relationship_result value = null;
		value = binding.get_modified_relationships(new java.lang.String(),
				new java.lang.String(), new java.lang.String(),
				new java.lang.String(), new java.lang.String(), 0, 0, 0,
				new java.lang.String(), new java.lang.String[0],
				new java.lang.String(), new java.lang.String());
		// TBD - validate results
	}

	public void test28sugarsoapPortLogout() throws Exception {
		com.sugarcrm.www.sugarcrm.SugarsoapBindingStub binding;
		try {
			binding = (com.sugarcrm.www.sugarcrm.SugarsoapBindingStub) new com.sugarcrm.www.sugarcrm.SugarsoapLocator()
					.getsugarsoapPort();
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null)
				jre.getLinkedCause().printStackTrace();
			throw new junit.framework.AssertionFailedError(
					"JAX-RPC ServiceException caught: " + jre);
		}
		assertNotNull("binding is null", binding);

		// Time out after a minute
		binding.setTimeout(60000);

		// Test operation
		binding.logout(sessionId);
	}
}
