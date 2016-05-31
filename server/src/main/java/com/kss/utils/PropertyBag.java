package com.kss.utils;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * PropertyBag bean holds all the properties(provided by property files or
 * system properties) within the application. This class is extending
 * <code>PropertySourcesPlaceholderConfigurer</code> so all spring based
 * property injection can be used in the application.
 * </p>
 */
@Component
public class PropertyBag extends PropertySourcesPlaceholderConfigurer {

	private static final String KSS_PROPERTIES = "kss.properties";

	private ConfigurablePropertyResolver propertyResolver;

	/**
	 * <p>
	 * Constructor to create propertyBag instance . It reads properties from
	 * file
	 * </p>
	 * 
	 * @throws RuntimeException
	 *             if property file not found
	 */
	public PropertyBag() {
		registerResources();
	}

	private void registerResources() {

		Properties[] props = new Properties[2];

		try {
			Resource resource = new ClassPathResource(File.separator + "properties" + File.separator + KSS_PROPERTIES);
			Properties properties = new Properties();
			properties.load(resource.getInputStream());

			props[0] = properties;

		} catch (Exception e) {

			throw new RuntimeException("Error on loading  properties");
		}

		this.setPropertiesArray(props);

	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
			final ConfigurablePropertyResolver propertyResolver) {
		super.processProperties(beanFactoryToProcess, propertyResolver);

		this.propertyResolver = propertyResolver;
	}

	/**
	 * This method can be used to retrieve any property value without using
	 * spring injection
	 * 
	 * @param name
	 * @return
	 */
	public String getProperty(String name) {
		return propertyResolver.getProperty(name);
	}

}
