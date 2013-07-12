package jpa.namingsupport;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.ManyToOneMapping;
import org.eclipse.persistence.sessions.Session;
import org.springframework.util.StringUtils;

import java.util.Map;

public class AliasCustomizer implements SessionCustomizer {

    @Override
    public void customize(Session session) throws Exception {
        Map<Class, ClassDescriptor> entityClasses = session.getDescriptors();
        for (Class entityClass : entityClasses.keySet()) {
            if (aliasNameForClass(entityClass) != null) {
                customizeEntity(aliasNameForClass(entityClass), entityClasses.get(entityClass));
            }
        }
    }

    private String aliasNameForClass(Class entityClass) {
        Alias aliasAnnotation = (Alias) entityClass.getAnnotation(Alias.class);
        return aliasAnnotation != null ? aliasAnnotation.name() : null;
    }

    private void customizeEntity(String alias, ClassDescriptor classDescriptor) {
        for (DatabaseMapping databaseMapping : classDescriptor.getMappings()) {
            updateFieldName(alias, databaseMapping);
            updateJoinColumnNames(alias, databaseMapping);
        }
    }

    private void updateJoinColumnNames(String alias, DatabaseMapping databaseMapping) {
        // TODO What mappings do we have to also care about?
        if (databaseMapping instanceof ManyToOneMapping) {
            ManyToOneMapping manyToOneMapping = (ManyToOneMapping) databaseMapping;
            Map<DatabaseField, DatabaseField> sourceToTargetKeyFields = manyToOneMapping.getSourceToTargetKeyFields();
            for (DatabaseField databaseField : sourceToTargetKeyFields.keySet()) {
                // TODO Do we want to require the referenced class to be annotated with alias, too?
                databaseField.setName(underscores(alias, aliasNameForClass(manyToOneMapping.getReferenceClass()), "ID"));
            }
        }
    }

    private void updateFieldName(String alias, DatabaseMapping databaseMapping) {
        if (databaseMapping.getField() != null) {
            DatabaseField databaseField = databaseMapping.getField();
            databaseField.setName(underscores(alias, databaseField.getName()));
        }
    }

    private String underscores(String... parts) {
        return StringUtils.arrayToDelimitedString(parts, "_");
    }

}
