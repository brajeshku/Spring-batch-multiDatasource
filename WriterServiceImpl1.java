@Service
public class EapService {

    @Autowired
    private EapIntRepository eapIntRepository;

    @Autowired
    private EapInFileTypeRepository eapInFileTypeRepository;

    public void insertData(Map<String, Object> data) {
        // Read eapConfig to determine table names
        String tableName = "eapInt"; // Example: Replace with the actual table name from eapConfig

        if (tableName.equals("eapInt")) {
            EapInt eapInt = new EapInt();
            // Set other fields in eapInt based on the data map

            List<EapInFileType> fileTypes = new ArrayList<>();
            // Add logic to populate eapInFileType based on eapConfigParam
            fileTypes.add(new EapInFileType(/* parameters */));

            eapInt.setEapInFileTypes(fileTypes);

            eapIntRepository.save(eapInt);
        }
    }
}



@Service
public class DynamicTableService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EapConfigRepository eapConfigRepository;

    @Autowired
    private EapConfigParamRepository eapConfigParamRepository;

    @Transactional
    public void processAndInsertData() throws Exception {
        List<EapConfig> configs = eapConfigRepository.findAll();

        for (EapConfig config : configs) {
            String tableName = config.getTableName();

            // Read the corresponding entity class using the table name
            Class<?> entityClass = getEntityClassFromTableName(tableName);

            // Create a new instance of the entity
            Object entityInstance = entityClass.getDeclaredConstructor().newInstance();

            // Read the column configurations from eapConfigParam
            List<EapConfigParam> params = eapConfigParamRepository.findByEapConfig(config);

            // Set values to the entity's fields dynamically
            for (EapConfigParam param : params) {
                String columnName = param.getColumnName();
                String value = getColumnValueFromSource(); // Implement this method to fetch data from the source

                // Use reflection to set the field value
                setFieldValue(entityInstance, columnName, value);
            }

            // Persist the entity
            entityManager.persist(entityInstance);
        }
    }

    private Class<?> getEntityClassFromTableName(String tableName) throws ClassNotFoundException {
        // Assuming entity classes follow a naming convention, e.g., "com.example.demo.entity." + tableName
        String className = "com.example.demo.entity." + tableName;
        return Class.forName(className);
    }

    private void setFieldValue(Object entity, String fieldName, Object value) throws Exception {
        Field field = entity.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(entity, value);
    }

    private String getColumnValueFromSource() {
        // Implement this method to fetch the actual data value that corresponds to the column
        return "some value"; // Placeholder
    }
}
