package com.hitzd.springBeanManager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 
 * @author liujc
 * ����Ѿ����غõ�bean.
 *
 */
@Service
public class SpringBeanUtil implements ApplicationContextAware
{
    private static  ApplicationContext applicationContext; 
    
    
    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext _applicationContext) throws BeansException
    {
        this.applicationContext = _applicationContext;
    }
    
    /**
     * ���spring ������
     * @return ApplicationContext
     */
     public static ApplicationContext getApplicationContext() {
       return applicationContext;
     }
    
     /**
     * ��ȡ����   
     * @param name
     * @return Object 
     * @throws BeansException
     */
     public static Object getBean(String name) throws BeansException {
       return applicationContext.getBean(name);
     }
    
     /**
     * ��ȡ����ΪrequiredType�Ķ���
     * ���bean���ܱ�����ת������Ӧ���쳣���ᱻ�׳���BeanNotOfRequiredTypeException��
     * @param name       beanע����
     * @param requiredType ���ض�������
     * @return Object ����requiredType���Ͷ���
     * @throws BeansException
     */
     public static Object getBean(String name, Class requiredType) throws BeansException {
       return applicationContext.getBean(name, requiredType);
     }
    
     /**
     * ���BeanFactory����һ������������ƥ���bean���壬�򷵻�true 
     * @param name
     * @return boolean
     */
     public static boolean containsBean(String name) {
       return applicationContext.containsBean(name);
     }
    
     /**
     * �ж��Ը�������ע���bean������һ��singleton����һ��prototype��
     * @param name
     * @return boolean
     * @throws NoSuchBeanDefinitionException
     */
     public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
       return applicationContext.isSingleton(name);
     }
    
     /**
     * @param name
     * @return Class ע����������
     * @throws NoSuchBeanDefinitionException
     */
     public static Class getType(String name) throws NoSuchBeanDefinitionException {
       return applicationContext.getType(name);
     }
    
     /**
     * ���������bean������bean�������б������򷵻���Щ����   
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
     public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
       return applicationContext.getAliases(name);
     }

}
