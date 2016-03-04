package com.spring.dev.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spring.dev.model.UserProfile;
import com.spring.dev.service.UserProfileService;

/*

This is the heart of this post. It will take care of mapping the individual userProfile id’s to actual 
UserProfile Entities in database.

*/

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements
		Converter<Object, UserProfile>
{

	@Autowired
	UserProfileService userProfileService;

	/**
	 * Gets UserProfile by Id
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public UserProfile convert(Object element)
	{
		Integer id = Integer.parseInt((String) element);
		UserProfile profile = userProfileService.findById(id);
		System.out.println("Profile : " + profile);
		return profile;
	}

}


/*

Above Converter setup in XML configuration will be:

<mvc:annotation-driven conversion-service="conversionService"/>
 
<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
     <property name="converters">
        <list>
            <bean id="roleToUserProfile" class="com.websystique.springsecurity.configuration.RoleToUserProfileConverter" />
        </list>
     </property>
</bean>


*/
