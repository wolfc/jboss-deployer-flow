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

import org.jboss.deployers.plugins.sort.KahnDeployerSorter;
import org.jboss.deployers.spi.deployer.Deployer;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
class KahnSorterHelper extends KahnDeployerSorter
{
   KahnDeployerSorter sorter = new KahnDeployerSorter();

   protected static class Edge
   {
      private String from;
      private String to;
      private String label;

      protected Edge(String from, String to, String label)
      {
         this.from = from;
         this.to = to;
         this.label = label;
      }

      public String getFrom()
      {
         return from;
      }

      public String getLabel()
      {
         return label;
      }

      public String getTo()
      {
         return to;
      }
   }

   protected static class Node
   {
      private String name;

      protected Node(String name)
      {
         this.name = name;
      }

      public String getName()
      {
         return name;
      }
   }

   protected static class Graph
   {
      Map<String, Node> nodes = new HashMap<String, Node>();
      Collection<Edge> edges = new ArrayList<Edge>();

      protected void addEdge(String from, String to, String label)
      {
         edges.add(new Edge(from, to, label));
      }
      
      protected void addNode(String name)
      {
         nodes.put(name, new Node(name));
      }
   }

   Graph create(Collection<? extends Deployer> deployers)
   {
      // S ← Set of all nodes with no incoming edges
      List<Deployer> s = new ArrayList<Deployer>();
      Map<String, Collection<Deployer>> inputCache = new HashMap<String, Collection<Deployer>>();
      //Map<Deployer, Set<Edge>> edgeCache = new IdentityHashMap<Deployer, Set<Edge>>();
      Map edgeCache = new IdentityHashMap<Deployer, Set<?>>();
      Set<String> outputs = new HashSet<String>();
      Map<String, Collection<Deployer>> outputCache = new HashMap<String, Collection<Deployer>>();
      for(Deployer deployer : deployers)
      {
         process(deployer, s, inputCache, edgeCache, outputs, outputCache);
      }

      processTransientDeployers(s, inputCache, outputCache, edgeCache);

      Graph graph = new Graph();
      for(Deployer d : deployers)
      {
         graph.addNode(d.toString());
      }

      for(Map.Entry entry : (Set<Map.Entry>) edgeCache.entrySet())
      {
         System.out.println(entry);
         Set<Object> edges = (Set<Object>) entry.getValue();
         for(Object edge : edges)
         {
            String fromNode = fromNode(edge);
            String edgeLabel = edgeLabel(edge);
            String toNode = toNode(edge);
            graph.addEdge(fromNode, toNode, edgeLabel);
         }
      }
      System.out.println(graph.edges.size());
      return graph;
   }

   private String edgeLabel(Object obj)
   {
      return field(obj, "input");
   }

   private String field(Object obj, String name)
   {
      try
      {
         Field field = obj.getClass().getDeclaredField(name);
         field.setAccessible(true);
         return field.get(obj).toString();
      }
      catch(IllegalAccessException e)
      {
         throw new RuntimeException(e);
      }
      catch(NoSuchFieldException e)
      {
         throw new RuntimeException("can't find " + name + " on " + obj.getClass(), e);
      }
   }

   private String fromNode(Object obj)
   {
      return field(obj, "from");
   }

   private String toNode(Object obj)
   {
      return field(obj, "to");
   }
}
