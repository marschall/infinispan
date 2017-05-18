package org.infinispan.factories;

import org.infinispan.distribution.DistributionManager;
import org.infinispan.distribution.impl.DistributionManagerImpl;
import org.infinispan.factories.annotations.DefaultFactoryFor;

@DefaultFactoryFor(classes = DistributionManager.class)
public class DistributionManagerFactory extends AbstractNamedCacheComponentFactory implements AutoInstantiableFactory {
   @Override
   @SuppressWarnings("unchecked")
   public <T> T construct(Class<T> componentType) {
      // Grouping interceptor consumes DistributionManager
      if (configuration.clustering().cacheMode().isClustered() || configuration.clustering().hash().groups().enabled())
         return (T) new DistributionManagerImpl();
      else
         return null;
   }
}
