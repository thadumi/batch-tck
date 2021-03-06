/**
 * Copyright 2012 International Business Machines Corp.
 * <p>
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * SPDX-License-Identifier: Apache-2.0
 */
package com.ibm.jbatch.tck.artifacts.chunkartifacts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.batch.api.chunk.AbstractItemReader;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ibm.jbatch.tck.artifacts.chunktypes.InventoryCheckpointData;
import com.ibm.jbatch.tck.artifacts.chunktypes.InventoryRecord;

@jakarta.inject.Named("inventoryInitReader")
public class InventoryInitReader extends AbstractItemReader {

    protected DataSource dataSource = null;

    private int count = 0;

    public void open(Serializable cpd) throws NamingException {

        InitialContext ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup(ConnectionHelper.jndiName);

    }

    @Override
    public InventoryRecord readItem() throws SQLException {
        if (count > 0) {
            return null;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = ConnectionHelper.getConnection(dataSource);

            statement = connection.prepareStatement(ConnectionHelper.SELECT_INVENTORY);
            statement.setInt(1, 1);
            rs = statement.executeQuery();

            int quantity = -1;
            while (rs.next()) {
                quantity = rs.getInt("quantity");
                count++;
            }

            return new InventoryRecord(1, quantity);

        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionHelper.cleanupConnection(connection, rs, statement);
        }

    }


    @Override
    public Serializable checkpointInfo() throws Exception {
        InventoryCheckpointData chkpData = new InventoryCheckpointData();
        chkpData.setInventoryCount(count);
        return chkpData;
    }
}
