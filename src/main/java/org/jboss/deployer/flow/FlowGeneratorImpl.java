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
import org.jboss.deployers.spi.deployer.DeploymentStage;
import org.jboss.deployers.spi.deployer.DeploymentStages;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
      DeployersImpl deployersImpl = (DeployersImpl) mainDeployer.getDeployers();
      Set<DeployerWrapper> deployers = deployersImpl.getDeployerWrappers();

      generate(deployersImpl, outputFileName);
   }

   protected String findDeployerWithOutput(List<Deployer> deployers, String output)
   {
      for(int i = (deployers.size() - 1); i >= 0; i--)
      {
         Deployer deployer = deployers.get(i);
         if(deployer.getOutputs().contains(output))
            return deployer.toString();
      }
      return null;
   }

   protected void generate(DeployersImpl deployersImpl, String outputFileName) throws IOException
   {
      File file = new File(outputFileName);
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
      try
      {
         // a nameless directed graph
         out.println("digraph {");
         out.println("\tsize=\"256,128\";");
         // allow edges between clusters
         out.println("\tcompound=\"true\";");

         out.println("\trankdir=\"TB\";");
         process(deployersImpl, DeploymentStages.INSTALLED, out, 0);
         out.println("}");
         out.flush();
      }
      finally
      {
         out.close();
      }
   }

   protected List<Deployer> getDeployersList(DeployersImpl obj, String stageName)
   {
      try
      {
         Method method = DeployersImpl.class.getDeclaredMethod("getDeployersList", String.class);
         method.setAccessible(true);
         return (List<Deployer>) method.invoke(obj, stageName);
      }
      catch(NoSuchMethodException e)
      {
         throw new RuntimeException(e);
      }
      catch(InvocationTargetException e)
      {
         throw new RuntimeException(e);
      }
      catch(IllegalAccessException e)
      {
         throw new RuntimeException(e);
      }
   }

   protected DeploymentStage getDeploymentStage(DeployersImpl deployersImpl, String stageName)
   {
      try
      {
         Field field = DeployersImpl.class.getDeclaredField("stages");
         field.setAccessible(true);
         Map<String, DeploymentStage> stages = (Map<String, DeploymentStage>) field.get(deployersImpl);
         return stages.get(stageName);
      }
      catch(NoSuchFieldException e)
      {
         throw new RuntimeException(e);
      }
      catch(IllegalAccessException e)
      {
         throw new RuntimeException(e);
      }
   }

   private String name(String s)
   {
      int i = s.lastIndexOf(".");
      if(i > 0)
         return s.substring(i + 1).replace("@", "_");
      return s.replace("@", "_");
   }

   private void process(DeployersImpl deployersImpl, DeploymentStage stage, PrintWriter out, int clusterNum)
   {
      String after = stage.getAfter();
      if(after != null)
      {
         process(deployersImpl, getDeploymentStage(deployersImpl, after), out, clusterNum + 1);
      }
      out.println("\tsubgraph cluster" + clusterNum + " {");
      out.println("\t\tlabel=\"" + stage.getName() + "\";");
      out.println("\t\trankdir=\"LR\";");
//      out.println("\t\tlayout=\"dot\";");
      out.println("\t\tclusterX" + clusterNum + " [style=\"invis\"]");

      Set<String> connectedInputs = new HashSet<String>();
      List<String> globalEdges = new ArrayList<String>();
      
      List<Deployer> deployers = getDeployersList(deployersImpl, stage.getName());
      for(int i = 0; i < deployers.size(); i++)
      {
         Deployer deployer = deployers.get(i);
         String deployerName = name(deployer.toString());
         out.println("\t\t" + deployerName + ";");

         Set<String> outputs = deployer.getOutputs();
         for(String output : outputs)
         {
            for(int j = i + 1; j < deployers.size(); j++)
            {
               Deployer other = deployers.get(j);
               if(other.getInputs().contains(output))
               {
                  out.println("\t\t" + deployerName + " -> " + name(other.toString()) + " [ label = \"" + name(output) + "\" ];");
                  // if the other deployer has the same output, we'll continue flow from there
                  if(other.getOutputs().contains(output))
                     j = deployers.size();
               }
            }
         }

         /* TODO: hook up the outputs of previous stages
         Set<String> inputs = deployer.getInputs();
         for(String input : inputs)
         {
            if(!connectedInputs.contains(input))
            {
               connectedInputs.add(input);
               if(after != null)
               {
                  String previousDeployerName = findDeployerWithOutput(getDeployersList(deployersImpl, after), input);
                  if(previousDeployerName != null)
                     globalEdges.add("\t" + name(previousDeployerName) + " -> " + deployerName + ";");
               }
            }
         }
         */
      }
      out.println("\t};");
      for(String s : globalEdges)
         out.println(s);
      if(after != null)
         out.println("\tclusterX" + (clusterNum + 1) + " -> clusterX" + clusterNum + " [ltail=cluster" + (clusterNum + 1) + ",lhead=cluster" + clusterNum + "];");
   }

   @Inject
   public void setMainDeployer(MainDeployerImpl mainDeployer)
   {
      this.mainDeployer = mainDeployer;
   }
}
