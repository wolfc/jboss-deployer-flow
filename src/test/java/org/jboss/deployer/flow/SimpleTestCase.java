/*
 * JBoss, Home of Professional Open Source
 * Copyright (c) 2010, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.deployer.flow;

import org.jboss.deployers.plugins.deployers.DeployerWrapper;
import org.jboss.deployers.spi.deployer.Deployer;
import org.jboss.deployers.spi.deployer.DeploymentStages;
import org.jboss.deployers.spi.deployer.helpers.AbstractDeployer;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class SimpleTestCase
{
   private void addDeployer(Collection<Deployer> main, Deployer deployer)
   {
      main.add(new DeployerWrapper(deployer));
   }

   @Test
   public void test1() throws IOException
   {
      Collection<Deployer> main = new ArrayList<Deployer>();
      AbstractDeployer deployer;
      long start = System.currentTimeMillis();

      // PARSE

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.BeanDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.kernel.spi.deployment.KernelDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.annotations.ScanningMetaDataDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.deployers.spi.annotations.ScanningMetaData", "org.jboss.deployers.plugins.annotations.AbstractScanningMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.dependency.AliasesParserDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.deployers.vfs.plugins.dependency.DeploymentAliases" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.dependency.DependenciesParserDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.deployers.vfs.plugins.dependency.DependenciesMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.spi.deployer.SchemaResolverDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.hibernate.deployers.metadata.HibernateMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.spi.deployer.SchemaResolverDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.xnio.metadata.XnioMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.spi.deployer.SchemaResolverDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.security.microcontainer.beans.metadata.SecurityPolicyMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.spi.deployer.SchemaResolverDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.logging.metadata.LoggingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.spi.deployer.SchemaResolverDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.spi.deployer.SchemaResolverDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.threads.metadata.ThreadsMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.spi.deployer.SchemaResolverDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.aop.microcontainer.beans.metadata.AOPDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.AppParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.ear.spec.EarMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.JBossAppParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.ear.jboss.JBossAppMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.AppClientParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.client.spec.ApplicationClientMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.JBossClientParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setInputs( "org.jboss.metadata.client.spec.ApplicationClientMetaData" );
      deployer.setOutputs( "org.jboss.metadata.client.jboss.JBossClientMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.PersistenceUnitParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.jpa.spec.PersistenceMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.jpa.deployers.PersistenceParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.jpa.spec.PersistenceMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.resource.deployers.ManagedConnectionFactoryParserDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.resource.metadata.mcf.ManagedConnectionFactoryDeploymentGroup" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.resource.deployers.RARParserDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.resource.metadata.RARDeploymentMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.security.deployers.AclConfigParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.security.acl.config.ACLConfiguration" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.security.deployers.XacmlConfigParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "javax.xml.bind.JAXBElement" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.system.deployers.SARDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.system.metadata.ServiceDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.varia.deployment.LegacyBeanShellDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.varia.deployment.BeanShellScript" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDescriptorDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.wsf.spi.metadata.webservices.WebservicesMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.aop.asintegration.jboss5.AOPAnnotationMetaDataParserDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.aop.microcontainer.beans.metadata.AOPDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.EARContentsDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.ear.jboss.JBossAppMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.WebAppParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.web.spec.WebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.WebAppFragmentParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.web.spec.WebFragmentMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.TldParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.web.spec.TldMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.JBossWebAppParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setInputs( "org.jboss.metadata.web.spec.WebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.EjbParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setOutputs( "org.jboss.metadata.ejb.spec.EjbJarMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.JBossEjbParsingDeployer" );
      deployer.setStage(DeploymentStages.PARSE);
      deployer.setInputs( "org.jboss.metadata.ejb.spec.EjbJarMetaData" );
      deployer.setOutputs( "standardjboss.xml", "org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.ApplicationMetaData" );
      addDeployer(main, deployer);

      // POST_PARSE

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.dependency.DependenciesMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.deployers.vfs.plugins.dependency.DependenciesMetaData" );
      deployer.setOutputs( "org.jboss.deployers.vfs.plugins.dependency.DeploymentDependencies" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.dependency.DeploymentAliasesDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.deployers.vfs.plugins.dependency.DeploymentAliases" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.dependency.DeploymentDependencyDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.deployers.vfs.plugins.dependency.DeploymentDependencies" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.EarClassLoaderDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.metadata.ear.jboss.JBossAppMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.EjbClassLoaderDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.LegacyWebXmlLessDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.metadata.web.spec.WebMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.resource.deployers.ManagedConnectionFactoryClassLoaderDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.resource.metadata.mcf.ManagedConnectionFactoryDeploymentGroup" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.system.deployers.ServiceClassLoaderDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.system.metadata.ServiceDeployment" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.web.tomcat.service.deployers.ClusteringDefaultsDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.web.tomcat.service.deployers.WarClassLoaderDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.metadata.PostJBossMetadataDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.metadata.PostJBossWebMetadataDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.metadata.WeldFilesDeployer" );
      deployer.setStage(DeploymentStages.POST_PARSE);
      deployer.setInputs( "org.jboss.weld.integration.deployer.ext.JBossWeldMetaData" );
      deployer.setOutputs( "WELD_FILES", "WELD_CLASSPATH" );
      addDeployer(main, deployer);

      // PRE_DESCRIBE

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.plugins.classloading.ClassLoadingDefaultDeployer" );
      deployer.setStage(DeploymentStages.PRE_DESCRIBE);
      deployer.setInputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      // DESCRIBE

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.classloader.InMemoryClassesDeployer" );
      deployer.setStage(DeploymentStages.DESCRIBE);
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.seam.integration.microcontainer.deployers.SeamWebUrlIntegrationDeployer" );
      deployer.setStage(DeploymentStages.DESCRIBE);
      deployer.setInputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.cl.WeldFacesIntegrationDeployer" );
      deployer.setStage(DeploymentStages.DESCRIBE);
      deployer.setInputs( "WELD_FILES" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.classloader.VFSClassLoaderClassPathDeployer" );
      deployer.setStage(DeploymentStages.DESCRIBE);
      deployer.setInputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.classloader.VFSClassLoaderDescribeDeployer" );
      deployer.setStage(DeploymentStages.DESCRIBE);
      deployer.setInputs( "org.jboss.classloading.spi.metadata.ClassLoadingMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.dependency.Module" );
      addDeployer(main, deployer);

      // CLASSLOADER

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.plugins.classloading.AbstractLevelClassLoaderSystemDeployer" );
      deployer.setStage(DeploymentStages.CLASSLOADER);
      deployer.setInputs( "org.jboss.deployers.structure.spi.ClassLoaderFactory" );
      deployer.setOutputs( "java.lang.ClassLoader" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.aop.asintegration.jboss5.AOPClassLoaderDeployer" );
      deployer.setStage(DeploymentStages.CLASSLOADER);
      deployer.setInputs( "java.lang.ClassLoader" );
      addDeployer(main, deployer);

      // POST_CLASSLOADER

      deployer = new DummyDeployerAdapter( "org.jboss.aop.asintegration.jboss5.AOPDeploymentAopMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.aop.microcontainer.beans.metadata.AOPDeployment" );
      deployer.setOutputs( "org.jboss.aop.asintegration.jboss5.AopMetaDataDeployerOutput" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.aop.asintegration.jboss5.BeansDeploymentAopMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.kernel.spi.deployment.KernelDeployment" );
      deployer.setOutputs( "org.jboss.aop.asintegration.jboss5.AopMetaDataDeployerOutput" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.EarLibExcludeDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.ear.jboss.JBossAppMetaData" );
      deployer.setOutputs( "org.jboss.classloading.spi.visitor.ResourceFilter.recurse" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.plugins.annotations.FilteredAnnotationEnvironmentDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.deployers.spi.annotations.ScanningMetaData", "org.jboss.classloading.spi.visitor.ResourceFilter.resource", "org.jboss.classloading.spi.dependency.Module", "org.jboss.classloading.spi.visitor.ResourceFilter.recurse" );
      deployer.setOutputs( "org.jboss.deployers.spi.annotations.AnnotationEnvironment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.EarSecurityDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.ear.jboss.JBossAppMetaData" );
      deployer.setOutputs( "jboss.jacc", "org.jboss.system.metadata.ServiceMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.OptAnnotationMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.web.spec.WebMetaData", "org.jboss.metadata.client.spec.ApplicationClientMetaData", "org.jboss.deployers.spi.annotations.AnnotationEnvironment", "org.jboss.metadata.ejb.spec.EjbJarMetaData" );
      deployer.setOutputs( "annotated.org.jboss.metadata.web.spec.WebMetaData", "annotated.org.jboss.metadata.ejb.spec.EjbJarMetaData", "annotated.org.jboss.metadata.client.spec.ApplicationClientMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb.deployers.MergedJBossMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "annotated.org.jboss.metadata.ejb.spec.EjbJarMetaData", "org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.ejb.spec.EjbJarMetaData" );
      deployer.setOutputs( "merged.org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.MergedJBossClientMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.client.jboss.JBossClientMetaData", "annotated.org.jboss.metadata.client.spec.ApplicationClientMetaData", "org.jboss.metadata.client.spec.ApplicationClientMetaData" );
      deployer.setOutputs( "org.jboss.metadata.client.jboss.JBossClientMetaData", "merged.org.jboss.metadata.client.jboss.JBossClientMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ha.framework.server.deployers.Ejb2HAPartitionDependencyDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "merged.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "merged.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ha.framework.server.deployers.Ejb3HAPartitionDependencyDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "merged.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "merged.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb.deployers.StandardJBossMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "standardjboss.xml", "merged.org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "raw.org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.Ejb3MetadataProcessingDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "merged.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "processed.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.EjbMetadataJndiPolicyDecoratorDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "processed.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "EjbMetadataJndiPolicyDecoratorDeployer" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.metadata.PostWebMetadataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "merged.org.jboss.metadata.web.jboss.JBossWebMetaData", "WELD_FILES", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.metadata.WeldEjbInterceptorMetadataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "WELD_FILES", "merged.org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb.deployers.EjbSecurityDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "merged.org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "jboss.jacc", "org.jboss.system.metadata.ServiceMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.web.deployers.WarAnnotationMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.web.spec.WebMetaData" );
      deployer.setOutputs( "annotated.org.jboss.metadata.web.spec.WebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.web.deployers.MergedJBossWebMetaDataDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "annotated.org.jboss.metadata.web.spec.WebMetaData", "org.jboss.metadata.web.spec.WebMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "overlays.org.jboss.metadata.web.spec.WebMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData", "order.org.jboss.metadata.web.spec.WebMetaData", "localscis.org.jboss.metadata.web.spec.WebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployment.MappedReferenceMetaDataResolverDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.client.jboss.JBossClientMetaData", "org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.deployment.spi.DeploymentEndpointResolver" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.web.deployers.ServletContainerInitializerDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "order.org.jboss.metadata.web.spec.WebMetaData", "localscis.org.jboss.metadata.web.spec.WebMetaData" );
      deployer.setOutputs( "sci.org.jboss.metadata.web.spec.WebMetaData", "sci.handlestypes.org.jboss.metadata.web.spec.WebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.web.deployers.WarSecurityDeployer" );
      deployer.setStage(DeploymentStages.POST_CLASSLOADER);
      deployer.setInputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jboss.jacc", "org.jboss.system.metadata.ServiceMetaData" );
      addDeployer(main, deployer);

      // PRE_REAL

      deployer = new DummyDeployerAdapter( "org.jboss.beanvalidation.deployers.ValidatorFactoryDeployer" );
      deployer.setStage(DeploymentStages.PRE_REAL);
      deployer.setOutputs( "javax.validation.ValidatorFactory" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.Ejb3DependenciesDeployer" );
      deployer.setStage(DeploymentStages.PRE_REAL);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "org.jboss.deployers.vfs.plugins.dependency.DependenciesMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.system.server.profileservice.persistence.deployer.ProfileServicePersistenceDeployer" );
      deployer.setStage(DeploymentStages.PRE_REAL);
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.env.EjbServicesDeployer" );
      deployer.setStage(DeploymentStages.PRE_REAL);
      deployer.setInputs( "WELD_FILES", "org.jboss.weld.integration.deployer.env.BootstrapInfo" );
      deployer.setOutputs( "BootstrapInfoEJB_SERVICES", "org.jboss.weld.integration.deployer.env.BootstrapInfo" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.env.WeldDiscoveryDeployer" );
      deployer.setStage(DeploymentStages.PRE_REAL);
      deployer.setInputs( "org.jboss.weld.integration.deployer.ext.JBossWeldMetaData", "WELD_FILES", "WELD_CLASSPATH" );
      deployer.setOutputs( "org.jboss.weld.integration.deployer.env.WeldDiscoveryEnvironment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.env.FlatDeploymentDeployer" );
      deployer.setStage(DeploymentStages.PRE_REAL);
      deployer.setInputs( "WELD_FILES", "org.jboss.weld.integration.deployer.env.BootstrapInfo", "org.jboss.weld.integration.deployer.env.WeldDiscoveryEnvironment", "BootstrapInfoEJB_SERVICES" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData", "org.jboss.weld.integration.deployer.env.BootstrapInfo", "BootstrapInfoDEPLOYMENT" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.weld.integration.deployer.env.WeldBootstrapDeployer" );
      deployer.setStage(DeploymentStages.PRE_REAL);
      deployer.setInputs( "javax.validation.ValidatorFactory", "WELD_FILES", "org.jboss.weld.integration.deployer.env.BootstrapInfo" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      // REAL

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.BeanMetaDataFactoryDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.threads.metadata.ThreadsMetaData" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.BeanMetaDataFactoryDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.logging.metadata.LoggingMetaData" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.BeanMetaDataFactoryDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.xnio.metadata.XnioMetaData" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb.deployers.CreateDestinationDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.system.metadata.ServiceMetaData", "org.jboss.kernel.spi.deployment.KernelDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb.deployers.EjbDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "org.jboss.ejb.deployers.EjbDeployment", "org.jboss.system.metadata.ServiceMetaData", "org.jboss.kernel.spi.deployment.KernelDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.Ejb3ClientDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.client.jboss.JBossClientMetaData" );
      deployer.setOutputs( "org.jboss.ejb3.clientmodule.ClientENCInjectionContainer", "org.jboss.kernel.spi.deployment.KernelDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.deployers.Ejb3Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData", "processed.org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "org.jboss.ejb3.Ejb3Deployment", "org.jboss.kernel.spi.deployment.KernelDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.AliasDeploymentDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.kernel.spi.deployment.KernelDeployment", "org.jboss.beans.metadata.spi.NamedAliasMetaData" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.NamedAliasMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.endpoint.deployers.EJB3EndpointDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.ejb3.metrics.deployer.Ejb3MetricsDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.ejb3.Ejb3Deployment" );
      deployer.setOutputs( "org.jboss.ejb3.metrics.deployer.Ejb3MetricsDeployer" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.hibernate.deployers.HibernateDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.hibernate.deployers.metadata.HibernateMetaData" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.jpa.deployers.PersistenceDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.jpa.spec.PersistenceMetaData" );
      deployer.setOutputs( "org.jboss.metadata.jpa.spec.PersistenceUnitMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.jpa.deployers.PersistenceUnitDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.jpa.spec.PersistenceUnitMetaData" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.KernelDeploymentDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.beans.metadata.spi.BeanMetaData", "org.jboss.kernel.spi.deployment.KernelDeployment" );
      deployer.setOutputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.BeanMetaDataDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.beans.metadata.spi.BeanMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.deployers.vfs.deployer.kernel.DeploymentAliasMetaDataDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.beans.metadata.spi.BeanMetaData", "org.jboss.beans.metadata.spi.NamedAliasMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.management.j2ee.deployers.EarModuleJSR77Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.ear.jboss.JBossAppMetaData" );
      deployer.setOutputs( "javax.management.ObjectName" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.management.j2ee.deployers.EjbModuleJSR77Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.ejb.jboss.JBossMetaData" );
      deployer.setOutputs( "javax.management.ObjectName" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.management.j2ee.deployers.JCAResourceJSR77Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.resource.metadata.mcf.ManagedConnectionFactoryDeploymentGroup" );
      deployer.setOutputs( "javax.management.ObjectName" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.management.j2ee.deployers.RarModuleJSR77Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.resource.metadata.RARDeploymentMetaData" );
      deployer.setOutputs( "javax.management.ObjectName" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.resource.deployers.ManagedConnectionFactoryDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.resource.metadata.mcf.ManagedConnectionFactoryDeploymentGroup" );
      deployer.setOutputs( "org.jboss.system.metadata.ServiceDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.management.j2ee.deployers.ServiceModuleJSR77Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.system.metadata.ServiceDeployment" );
      deployer.setOutputs( "javax.management.ObjectName" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.resource.deployers.RARDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.resource.metadata.RARDeploymentMetaData" );
      deployer.setOutputs( "org.jboss.system.metadata.ServiceMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.varia.deployment.LegacyBeanShellScriptDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.varia.deployment.BeanShellScript" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSEJBAdapterDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.ejb3.Ejb3Deployment", "merged.org.jboss.metadata.ejb.jboss.JBossMetaData", "org.jboss.ejb.deployers.EjbDeployment", "org.jboss.wsf.spi.metadata.webservices.WebservicesMetaData" );
      deployer.setOutputs( "org.jboss.wsf.spi.deployment.integration.WebServiceDeployment" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSTypeDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.integration.WebServiceDeployment", "org.jboss.wsf.spi.metadata.webservices.WebservicesMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "org.jboss.wsf.spi.deployment.Deployment$DeploymentType" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "org.jboss.wsf.spi.deployment.Deployment$DeploymentType" );
      deployer.setOutputs( "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.EndpointMetrics", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.VFSRoot", "jbossws.ContainerMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "jbossws.ContainerMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata", "jbossws.VirtualHosts" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "jbossws.ContainerMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.ContextRoot", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.ContextRoot", "org.jboss.wsf.spi.deployment.Deployment", "jbossws.ContainerMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.URLPattern", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.URLPattern", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.EndpointAddress", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.URLPattern", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.EndpointName", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.EndpointName", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.RegisteredEndpoint", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.URLPattern", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.VirtualHosts" );
      deployer.setOutputs( "jbossws.WebMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "jbossws.ContainerMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.StackEndpointHandler", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.JAXBIntros", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.JAXBIntros", "jbossws.VFSRoot", "jbossws.URLPattern", "org.jboss.wsf.spi.deployment.Deployment", "jbossws.ContainerMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.UnifiedMetaDataModel", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.UnifiedMetaDataModel", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.JAXBIntros", "jbossws.UnifiedMetaDataModel", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.PublishedContract", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.UnifiedMetaDataModel", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.InitializedMetaDataModel", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.UnifiedMetaDataModel", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.StackEndpointHandler", "jbossws.UnifiedMetaDataModel", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.StackDescriptor", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.ContextProperties", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.WebMetaData", "org.jboss.wsf.spi.deployment.Deployment", "jbossws.StackDescriptor", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.ContextProperties" );
      deployer.setOutputs( "jbossws.WebMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.WebMetaData", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.JACCPermisions", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.WebMetaData", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.InjectionMetaData", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "jbossws.RegisteredEndpoint", "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "jbossws.EndpointRecordProcessors", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.webservices.integration.deployers.WSDeploymentAspectDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.wsf.spi.deployment.Deployment", "org.jboss.metadata.web.jboss.JBossWebMetaData", "jbossws.metadata" );
      deployer.setOutputs( "jbossws.LifecycleHandler", "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.management.j2ee.deployers.WebModuleJSR77Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "javax.management.ObjectName" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.web.tomcat.service.deployers.TomcatDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.metadata.web.jboss.JBossWebMetaData" );
      deployer.setOutputs( "org.jboss.web.deployers.WarDeployment", "org.jboss.system.metadata.ServiceMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.system.deployers.ServiceDeploymentDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.system.metadata.ServiceDeployment", "org.jboss.system.metadata.ServiceMetaData" );
      deployer.setOutputs( "org.jboss.system.metadata.ServiceMetaData" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.management.j2ee.deployers.JMSResourceJSR77Deployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.system.metadata.ServiceMetaData" );
      deployer.setOutputs( "javax.management.ObjectName" );
      addDeployer(main, deployer);

      deployer = new DummyDeployerAdapter( "org.jboss.system.deployers.ServiceDeployer" );
      deployer.setStage(DeploymentStages.REAL);
      deployer.setInputs( "org.jboss.system.metadata.ServiceMetaData" );
      addDeployer(main, deployer);

      long end = System.currentTimeMillis();

      FlowGeneratorImpl generator = new FlowGeneratorImpl();
      generator.generate(main, "/tmp/x.dot");
   }
}
