package com.marklogic.cf.servicebroker.service;

import com.marklogic.cf.servicebroker.IntegrationTestBase;
import org.junit.Test;

public class ServiceInstanceServiceTest extends IntegrationTestBase {

    @Test
    public void testIt() {
        return;
    }

//	private static final String SVC_DEF_ID = "serviceDefinitionId";
//	private static final String SVC_PLAN_ID = "servicePlanId";
//
//	@Autowired
//	private MongoClient client;
//
//	@Mock
//	private MarkLogicAdminService mongo;
//
//	@Mock
//	private MongoServiceInstanceRepository repository;
//
//	@Mock
//	private DB db;
//
//	@Mock
//	private ServiceDefinition serviceDefinition;
//
//	private MarkLogicServiceInstanceService service;
//
//	@Before
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//
//		service = new MarkLogicServiceInstanceService(mongo, repository);
//	}
//
//	@After
//	public void cleanup() {
//		client.dropDatabase(DB_NAME);
//	}
//
//	@Test
//	public void newServiceInstanceCreatedSuccessfully() throws Exception {
//
//		when(repository.findOne(any(String.class))).thenReturn(null);
//		when(mongo.databaseExists(any(String.class))).thenReturn(false);
//		when(mongo.createDatabase(any(String.class))).thenReturn(db);
//
//		CreateServiceInstanceResponse response = service.createServiceInstance(buildCreateRequest());
//
//		assertNotNull(response);
//		assertNull(response.getDashboardUrl());
//		assertFalse(response.isAsync());
//
//		verify(repository).save(isA(ServiceInstance.class));
//	}
//
//	@Test
//	public void newServiceInstanceCreatedSuccessfullyWithExistingDB() throws Exception {
//
//		when(repository.findOne(any(String.class))).thenReturn(null);
//		when(mongo.databaseExists(any(String.class))).thenReturn(true);
//		when(mongo.createDatabase(any(String.class))).thenReturn(db);
//
//		CreateServiceInstanceRequest request = buildCreateRequest();
//		CreateServiceInstanceResponse response = service.createServiceInstance(request);
//
//		assertNotNull(response);
//		assertNull(response.getDashboardUrl());
//		assertFalse(response.isAsync());
//
//		verify(mongo).deleteDatabase(request.getServiceInstanceId());
//		verify(repository).save(isA(ServiceInstance.class));
//	}
//
//	@Test(expected=ServiceInstanceExistsException.class)
//	public void serviceInstanceCreationFailsWithExistingInstance() throws Exception {
//		when(repository.findOne(any(String.class))).thenReturn(ServiceInstanceFixture.getServiceInstance());
//
//		service.createServiceInstance(buildCreateRequest());
//	}
//
//	@Test(expected=ServiceBrokerException.class)
//	public void serviceInstanceCreationFailsWithDBCreationFailure() throws Exception {
//		when(repository.findOne(any(String.class))).thenReturn(null);
//		when(mongo.databaseExists(any(String.class))).thenReturn(false);
//		when(mongo.createDatabase(any(String.class))).thenReturn(null);
//
//		service.createServiceInstance(buildCreateRequest());
//	}
//
//	@Test
//	public void successfullyRetrieveServiceInstance() {
//		when(repository.findOne(any(String.class))).thenReturn(ServiceInstanceFixture.getServiceInstance());
//		String id = ServiceInstanceFixture.getServiceInstance().getServiceInstanceId();
//		assertEquals(id, service.getServiceInstance(id).getServiceInstanceId());
//	}
//
//	@Test
//	public void serviceInstanceDeletedSuccessfully() throws Exception {
//		ServiceInstance instance = ServiceInstanceFixture.getServiceInstance();
//		when(repository.findOne(any(String.class))).thenReturn(instance);
//		String id = instance.getServiceInstanceId();
//
//		DeleteServiceInstanceResponse response = service.deleteServiceInstance(buildDeleteRequest());
//
//		assertNotNull(response);
//		assertFalse(response.isAsync());
//
//		verify(mongo).deleteDatabase(id);
//		verify(repository).delete(id);
//	}
//
//	@Test(expected = ServiceInstanceDoesNotExistException.class)
//	public void unknownServiceInstanceDeleteCallSuccessful() throws Exception {
//		when(repository.findOne(any(String.class))).thenReturn(null);
//
//		DeleteServiceInstanceRequest request = buildDeleteRequest();
//
//		DeleteServiceInstanceResponse response = service.deleteServiceInstance(request);
//
//		assertNotNull(response);
//		assertFalse(response.isAsync());
//
//		verify(mongo).deleteDatabase(request.getServiceInstanceId());
//		verify(repository).delete(request.getServiceInstanceId());
//	}
//
//	private CreateServiceInstanceRequest buildCreateRequest() {
//		return new CreateServiceInstanceRequest(SVC_DEF_ID, SVC_PLAN_ID, "organizationGuid", "spaceGuid")
//				.withServiceInstanceId(ServiceInstanceFixture.getServiceInstance().getServiceInstanceId());
//	}
//
//	private DeleteServiceInstanceRequest buildDeleteRequest() {
//		return new DeleteServiceInstanceRequest(ServiceInstanceFixture.getServiceInstance().getServiceInstanceId(),
//				SVC_DEF_ID, SVC_PLAN_ID, serviceDefinition);
//	}
}