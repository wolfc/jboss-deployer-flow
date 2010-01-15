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

import org.jboss.aop.microcontainer.aspects.jmx.JMX;
import org.jboss.beans.metadata.api.annotations.Inject;
import org.jboss.deployers.plugins.deployers.DeployerWrapper;
import org.jboss.deployers.plugins.deployers.DeployersImpl;
import org.jboss.deployers.plugins.main.MainDeployerImpl;
import org.jboss.deployers.spi.deployer.Deployer;

import java.io.*;
import java.util.Collection;
import java.util.Set;

/**
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
@JMX(exposedInterface = FlowGenerator.class)
public class FlowGeneratorImpl implements FlowGenerator
{
   private MainDeployerImpl mainDeployer;

   private KahnSorterHelper helper = new KahnSorterHelper();

   public void generate(String outputFileName) throws IOException
   {
      // Ales is going to love this
      DeployersImpl deployersHolder = (DeployersImpl) mainDeployer.getDeployers();
      Set<DeployerWrapper> deployers = deployersHolder.getDeployerWrappers();

      generate(deployers, outputFileName);
   }

   protected void generate(Collection<? extends Deployer> deployers, String outputFileName) throws IOException
   {
      KahnSorterHelper.Graph graph = helper.create(deployers);

      File file = new File(outputFileName);
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
      try
      {
         // a nameless directed graph
         out.println("digraph {");
         out.println("\tsize=\"128,128\";");
         for(KahnSorterHelper.Node node : graph.nodes.values())
         {
            out.println("\t" + name(node.getName())  + ";");
         }
         for(KahnSorterHelper.Edge edge : graph.edges)
         {
            out.println("\t" + name(edge.getFrom()) + " -> " + name(edge.getTo()) + "[ label = \"" + name(edge.getLabel()) + "\" ];");
         }
         out.println("}");
         out.flush();
      }
      finally
      {
         out.close();
      }
   }

   private String name(String s)
   {
      int i = s.lastIndexOf(".");
      if(i > 0)
         return s.substring(i + 1).replace("@", "_");
      return s.replace("@", "_");
   }

   @Inject
   public void setMainDeployer(MainDeployerImpl mainDeployer)
   {
      this.mainDeployer = mainDeployer;
   }
}
