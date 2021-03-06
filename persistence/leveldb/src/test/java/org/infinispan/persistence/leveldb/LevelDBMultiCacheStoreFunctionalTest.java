package org.infinispan.persistence.leveldb;

import java.io.File;

import org.infinispan.commons.util.Util;
import org.infinispan.configuration.cache.PersistenceConfigurationBuilder;
import org.infinispan.persistence.MultiStoresFunctionalTest;
import org.infinispan.persistence.leveldb.configuration.LevelDBStoreConfiguration;
import org.infinispan.persistence.leveldb.configuration.LevelDBStoreConfigurationBuilder;
import org.infinispan.test.TestingUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = "unit", testName = "persistence.leveldb.LevelDBMultiCacheStoreFunctionalTest")
public class LevelDBMultiCacheStoreFunctionalTest extends MultiStoresFunctionalTest<LevelDBStoreConfigurationBuilder> {

   private File tmpDir = new File(TestingUtil.tmpDirectory(this.getClass()));

   @BeforeMethod
   protected void cleanDataFiles() {
      if (tmpDir.exists()) {
         Util.recursiveFileRemove(tmpDir);
      }
   }

   @Override
   protected LevelDBStoreConfigurationBuilder buildCacheStoreConfig(PersistenceConfigurationBuilder p, String discriminator) throws Exception {
      LevelDBStoreConfigurationBuilder store = p.addStore(LevelDBStoreConfigurationBuilder.class);
      store.location(tmpDir.getAbsolutePath() + File.separator + "rocksdb" + File.separator + "data-" + discriminator);
      store.expiredLocation(tmpDir.getAbsolutePath() + File.separator + "rocksdb" + File.separator + "expired-data-" + discriminator);
      store.implementationType(LevelDBStoreConfiguration.ImplementationType.JAVA);
      return store;
   }
}
