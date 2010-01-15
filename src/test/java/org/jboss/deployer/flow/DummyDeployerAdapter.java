/**
 *
 */
package org.jboss.deployer.flow;

import org.jboss.deployers.spi.DeploymentException;
import org.jboss.deployers.spi.deployer.helpers.AbstractDeployer;
import org.jboss.deployers.structure.spi.DeploymentUnit;

/**
 * Deployer adapter.
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public final class DummyDeployerAdapter extends AbstractDeployer
{

   final String name;

   public DummyDeployerAdapter( final String name )
   {
      super();
      this.name = name;
   }

   public void deploy(DeploymentUnit unit) throws DeploymentException
   {
      // NOOP
   }

   @Override
   public String toString()
   {
      return this.name + "@" + hashCode();
   }

}
