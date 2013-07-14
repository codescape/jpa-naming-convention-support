package jpa.namingsupport;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectToFieldMapping;
import org.eclipse.persistence.mappings.ManyToOneMapping;
import org.eclipse.persistence.sessions.Session;
import org.springframework.util.StringUtils;

import java.util.Map;

public class AliasCustomizer implements SessionCustomizer {

    @Override
    public void customize(Session session) throws Exception {
        Map<Class, ClassDescriptor> entities = session.getDescriptors();
        for (Class entity : entities.keySet()) {
            customizeEntity(aliasNameFor(entity), entities.get(entity));
        }
    }

    private String aliasNameFor(Class entity) {
        Alias alias = (Alias) entity.getAnnotation(Alias.class);
        return alias != null ? alias.name() : null;
    }

    private void customizeEntity(String alias, ClassDescriptor classDescriptor) {
        for (DatabaseMapping databaseMapping : classDescriptor.getMappings()) {
            customizeDirectToFieldMapping(alias, databaseMapping);
            customizeManyToOneMapping(alias, databaseMapping);
            // TODO add other mappings that need to be customized accordingly
        }
    }

    private void customizeDirectToFieldMapping(String alias, DatabaseMapping databaseMapping) {
        if (databaseMapping instanceof DirectToFieldMapping) {
            DatabaseField databaseField = databaseMapping.getField();
            databaseField.setName(underscores(alias, databaseField.getName()));
        }
    }

    private void customizeManyToOneMapping(String alias, DatabaseMapping databaseMapping) {
        if (databaseMapping instanceof ManyToOneMapping) {
            ManyToOneMapping mapping = (ManyToOneMapping) databaseMapping;
            for (DatabaseField databaseField : mapping.getSourceToTargetKeyFields().keySet()) {
                databaseField.setName(underscores(alias, aliasNameFor(mapping.getReferenceClass()), "ID"));
            }
        }
    }

    private String underscores(String... parts) {
        return StringUtils.arrayToDelimitedString(parts, "_");
    }

}
