/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prestosql.plugin.hive;

import alluxio.client.table.TableMasterClient;
import alluxio.conf.PropertyKey;
import io.prestosql.plugin.hive.metastore.alluxio.AlluxioHiveMetastore;
import io.prestosql.plugin.hive.metastore.alluxio.AlluxioHiveMetastoreConfig;
import io.prestosql.plugin.hive.metastore.alluxio.AlluxioMetastoreModule;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class TestHiveAlluxioMetastore
        extends AbstractTestHive
{
    private static final String SCHEMA = "default";

    private String alluxioAddress;
    private int hiveVersionMajor;

    @Parameters({
            "hive.hadoop2.alluxio.host",
            "hive.hadoop2.alluxio.port",
            "hive.hadoop2.hiveVersionMajor",
    })
    @BeforeClass
    public void setup(String host, String port, int hiveVersionMajor)
    {
        this.alluxioAddress = host + ":" + port;
        System.out.println(this.alluxioAddress);
        System.setProperty(PropertyKey.Name.SECURITY_LOGIN_USERNAME, "presto");
        System.setProperty(PropertyKey.Name.MASTER_HOSTNAME, host);

        AlluxioHiveMetastoreConfig alluxioConfig = new AlluxioHiveMetastoreConfig();
        alluxioConfig.setMasterAddress(this.alluxioAddress);
        TableMasterClient client = AlluxioMetastoreModule.createCatalogMasterClient(alluxioConfig);
        setup(SCHEMA, getHiveConfig(), new AlluxioHiveMetastore(client));

        checkArgument(hiveVersionMajor > 0, "Invalid hiveVersionMajor: %s", hiveVersionMajor);
        this.hiveVersionMajor = hiveVersionMajor;
    }

    @Override
    public void testBucketSortedTables()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testBucketedTableEvolution()
    {
        // Alluxio metastore does not support create/insert/update operations
    }

    @Override
    public void testEmptyOrcFile()
    {
        // Alluxio metastore does not support create operations
    }

    // specifically disable so that expected exception on the superclass don't fail this test
    @Override
    @Test(enabled = false)
    public void testEmptyRcBinaryFile()
    {
        // Alluxio metastore does not support create operations
    }

    // specifically disable so that expected exception on the superclass don't fail this test
    @Override
    @Test(enabled = false)
    public void testEmptyRcTextFile()
    {
        // Alluxio metastore does not support create operations
    }

    // specifically disable so that expected exception on the superclass don't fail this test
    @Override
    @Test(enabled = false)
    public void testEmptySequenceFile()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testEmptyTableCreation()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testEmptyTextFile()
    {
        // Alluxio metastore does not support create operations
    }

    private int getHiveVersionMajor()
    {
        checkState(hiveVersionMajor > 0, "hiveVersionMajor not set");
        return hiveVersionMajor;
    }

    @Override
    public void testGetPartitionSplitsTableOfflinePartition()
    {
        if (getHiveVersionMajor() >= 2) {
            throw new SkipException("ALTER TABLE .. ENABLE OFFLINE was removed in Hive 2.0 and this is a prerequisite for this test");
        }

        super.testGetPartitionSplitsTableOfflinePartition();
    }

    @Override
    public void testHiveViewsAreNotSupported()
    {
        // Alluxio metastore does not support insert/update operations
    }

    @Override
    public void testIllegalStorageFormatDuringTableScan()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testInsert()
    {
        // Alluxio metastore does not support insert/update operations
    }

    @Override
    public void testInsertIntoExistingPartition()
    {
        // Alluxio metastore does not support insert/update operations
    }

    @Override
    public void testInsertIntoExistingPartitionEmptyStatistics()
    {
        // Alluxio metastore does not support insert/update operations
    }

    @Override
    public void testInsertIntoNewPartition()
    {
        // Alluxio metastore does not support insert/update operations
    }

    @Override
    public void testInsertOverwriteUnpartitioned()
    {
        // Alluxio metastore does not support insert/update operations
    }

    @Override
    public void testInsertUnsupportedWriteType()
    {
        // Alluxio metastore does not support insert/update operations
    }

    @Override
    public void testMetadataDelete()
    {
        // Alluxio metastore does not support create/delete operations
    }

    @Override
    public void testMismatchSchemaTable()
    {
        // Alluxio metastore does not support create/delete operations
    }

    @Override
    public void testPartitionStatisticsSampling()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testStorePartitionWithStatistics()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testRenameTable()
    {
        // Alluxio metastore does not support update operations
    }

    @Override
    public void testTableCreation()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testTableCreationIgnoreExisting()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testTableCreationRollback()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testTransactionDeleteInsert()
    {
        // Alluxio metastore does not support insert/update/delete operations
    }

    @Override
    public void testUpdateBasicPartitionStatistics()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testUpdateBasicTableStatistics()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testUpdatePartitionColumnStatistics()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testUpdatePartitionColumnStatisticsEmptyOptionalFields()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testUpdateTableColumnStatistics()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testUpdateTableColumnStatisticsEmptyOptionalFields()
    {
        // Alluxio metastore does not support create operations
    }

    @Override
    public void testViewCreation()
    {
        // Alluxio metastore does not support create operations
    }
}
