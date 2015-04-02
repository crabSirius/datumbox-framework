/**
 * Copyright (C) 2013-2015 Vasilis Vryniotis <bbriniotis at datumbox.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.datumbox.configuration;

import com.datumbox.common.persistentstorage.mapdb.MapDBConfiguration;
import com.datumbox.common.persistentstorage.inmemory.InMemoryConfiguration;
import com.datumbox.common.persistentstorage.interfaces.DatabaseConfiguration;

/**
 *
 * @author Vasilis Vryniotis <bbriniotis at datumbox.com>
 */
public class TestConfiguration {
    public static double DOUBLE_ACCURACY_HIGH=0.000001;
    public static double DOUBLE_ACCURACY_MEDIUM=0.01;
    public static double DOUBLE_ACCURACY_LOW=0.5;
    
    public static final Class<? extends DatabaseConfiguration> PERMANENT_STORAGE = InMemoryConfiguration.class; 
    //public static final Class<? extends DatabaseConfiguration> PERMANENT_STORAGE = MapDBConfiguration.class; 
    
    public static DatabaseConfiguration getDBConfig() {
        if (PERMANENT_STORAGE.equals(InMemoryConfiguration.class)) {
            return new InMemoryConfiguration();
        }
        else if (PERMANENT_STORAGE.equals(MapDBConfiguration.class)) {
            return new MapDBConfiguration();
        }
        
        return null;
    }
    
}