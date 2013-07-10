package jpa.namingsupport;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.sessions.Session;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AliasCustomizer implements SessionCustomizer {

    @Override
    public void customize(Session session) throws Exception {
        Map<Class, ClassDescriptor> entityClasses = session.getDescriptors();

        for (Class entityClass : entityClasses.keySet()) {
            System.out.println("EntityClass: " + entityClass.getName());

            Annotation aliasAnnotation = entityClass.getAnnotation(Alias.class);
            if (aliasAnnotation != null) {
                String alias = ((Alias) aliasAnnotation).name();
                System.out.println("AliasAnnotation name: " + alias);


                ClassDescriptor classDescriptor = entityClasses.get(entityClass);

                List<DatabaseField> entityFields = classDescriptor.getPrimaryKeyFields();
                for (DatabaseField entityField : entityFields) {
                    System.out.println("EntityField name: " + entityField.getName());
                    entityField.setName(alias + "_" + entityField.getName());
                }
            }
        }
    }

}
