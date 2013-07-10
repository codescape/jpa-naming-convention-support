package jpa.namingsupport;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.sessions.Session;

import java.util.Map;

public class AliasCustomizer implements SessionCustomizer {

    @Override
    public void customize(Session session) throws Exception {
        Map<Class, ClassDescriptor> entityClasses = session.getDescriptors();
        for (Class entityClass : entityClasses.keySet()) {
            Alias aliasAnnotation = (Alias) entityClass.getAnnotation(Alias.class);
            if (aliasAnnotation != null) {
                rewriteEntity(aliasAnnotation.name(), entityClasses.get(entityClass));
            }
        }
    }

    private void rewriteEntity(String alias, ClassDescriptor classDescriptor) {
        for (DatabaseMapping fieldMapping : classDescriptor.getMappings()) {
            updateFieldName(alias, fieldMapping);
        }
    }

    private void updateFieldName(String alias, DatabaseMapping fieldMapping) {
        DatabaseField field = fieldMapping.getField();
        field.setName(alias + "_" + field.getName());
    }

}
