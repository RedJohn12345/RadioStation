package korchagin.reflection;

import javassist.ClassPath;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApplicationContext {
    
    private static final Map<Class<?>, Object> instances = new HashMap<>();

    public static void injectDependencies() throws Exception {
        makeInstances();
        inject();

    }

    private static void makeInstances() throws
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        instances.clear();
        Reflections reflections = new Reflections("korchagin");
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> c : components) {
            instances.put(c, c.getDeclaredConstructor().newInstance());
        }
    }

    private static void inject() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("korchagin"))
                .setScanners(new FieldAnnotationsScanner()));
        Set<Field> fields = reflections.getFieldsAnnotatedWith(DependencyInjection.class);
        for (Field field: fields) {
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);

            try {
                Object object = instances.get(field.getType());

                field.set(field.getDeclaringClass().newInstance(), object);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }

            field.setAccessible(isAccessible);

        }

    }

}
